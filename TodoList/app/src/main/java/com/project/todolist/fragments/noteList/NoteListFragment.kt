package com.project.todolist.fragments.noteList

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.project.todolist.R
import com.project.todolist.animation.startAnimation
import com.project.todolist.data.models.TaskData
import com.project.todolist.data.viewModel.TaskViewModel
import com.project.todolist.databinding.FragmentNoteListBinding
import com.project.todolist.fragments.SharedViewModel
import com.project.todolist.fragments.noteList.adapter.ListAdapter
import java.text.FieldPosition

class NoteListFragment : Fragment() {
	private var _binding: FragmentNoteListBinding? = null
	private var binding: FragmentNoteListBinding
		get() = _binding!!
		set(value) {
			_binding = value
		}
	private val adapter : ListAdapter by lazy{ ListAdapter() }
	private val mTaskViewModel : TaskViewModel by viewModels()
	private val mSharedViewModel: SharedViewModel by viewModels()


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
							 ): View? {

		//Data Binding
		binding = FragmentNoteListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this
		binding.mSharedViewModel = mSharedViewModel
		val view = binding.root

		//设置Recycle View
		setupRecyclerView()

		mTaskViewModel.getAllData.observe(viewLifecycleOwner, Observer {data ->
			mSharedViewModel.checkIfDatabaseEmpty(data)
			adapter.setData(data)
		})

		//使用dataBinding取代
//		mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
//			showEmptyDatabaseView(it)
//		})



//		binding.floatingActionButton.setOnClickListener{
//			animateNavigate()
//		}

		setHasOptionsMenu(true)
		return view
	}

	private fun setupRecyclerView() {
		val recyclerView = binding.recycleView
		recyclerView.adapter = adapter
		recyclerView.layoutManager = LinearLayoutManager(requireActivity())

		swipeToDelete(recyclerView)
	}

	private fun swipeToDelete(recyclerView: RecyclerView){
		ItemTouchHelper(object : SwipeToDelete(){
			override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
				val itemToDelete = adapter.dataList[viewHolder.adapterPosition]
				mTaskViewModel.deleteData(itemToDelete)
				adapter.notifyItemChanged(viewHolder.adapterPosition)
//				Toast.makeText(requireContext(), """${
//					getString(
//							R.string.successfully_delete)
//				}${itemToDelete.title}""", Toast.LENGTH_SHORT).show()

				//撤销
				restoreDeletedData(viewHolder.itemView, itemToDelete,viewHolder.adapterPosition)
			}
		}).attachToRecyclerView(recyclerView)
	}

	private fun restoreDeletedData(view: View, deleteItem: TaskData, position: Int){
		Snackbar.make(view, "删除${deleteItem.title}",Snackbar.LENGTH_LONG)
			.setAction(getString(R.string.item_restoure))
			{
				mTaskViewModel.insertData(deleteItem)
				adapter.notifyItemChanged(position)
			}
			.show()

	}

	fun animateNavigate(){
		//圆圈过渡动画
		val animation = AnimationUtils.loadAnimation(this.context,R.anim.circle_explosion_anim).apply {
			duration = 500
			interpolator = AccelerateDecelerateInterpolator()

		}
		binding.floatingActionButton.isVisible = false
		binding.circle.isVisible = true
		binding.circle.startAnimation(animation){

			binding?.root?.setBackgroundColor(ContextCompat.getColor(this.requireContext(),R.color.colorPrimary))
			binding?.circle?.isVisible = false
			findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
		}

	}

	private fun showEmptyDatabaseView(emptyDatabase: Boolean) {
		if(emptyDatabase){
			binding.noDataImage.visibility = View.VISIBLE
			binding.noDataTextView.visibility = View.VISIBLE
		}else{
			binding.noDataImage.visibility = View.INVISIBLE
			binding.noDataTextView.visibility = View.INVISIBLE

		}
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.note_list_fragment_menu,menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when(item.itemId){
			R.id.menu_delete_all -> {
				AlertDialog.Builder(requireContext()).let {
					it.setPositiveButton(getString(R.string.confirm_delete)){ _, _ ->
						mTaskViewModel.deleteAll()
						Toast.makeText(requireContext(),
						               getString(R.string.successfully_delete_all),
						               Toast.LENGTH_SHORT).show()
					}
					it.setNegativeButton(getString(R.string.cancel_delete)){ _, _ -> }
					it.setTitle(getString(R.string.ask_delete_all))
					it.setMessage(getString(R.string.sure_delete_all_msg))
					it.create().show()
				}

			}
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}