package com.project.todolist.fragments.updateNote

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.project.todolist.R
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskData
import com.project.todolist.data.viewModel.TaskViewModel
import com.project.todolist.databinding.FragmentNoteListBinding
import com.project.todolist.databinding.FragmentUpdateBinding
import com.project.todolist.fragments.SharedViewModel

class UpdateFragment : Fragment() {

	private var _binding: FragmentUpdateBinding? = null
	private var binding: FragmentUpdateBinding
		get() = _binding!!
		set(value) {
			_binding = value
		}
	private val args by navArgs<UpdateFragmentArgs>()
	private val mTaskViewModel: TaskViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
							 ): View? {
		binding = FragmentUpdateBinding.inflate(inflater, container, false)
		binding.args = args

		setHasOptionsMenu(true)
		// Inflate the layout for this fragment
//		binding.currentTitleEdittext.setText(args.currentItem.title)
//		binding.currentDescriptionEdittext.setText(args.currentItem.description)
//		binding.currentPrioritySpinner.setSelection(SharedViewModel.parsePriority(args.currentItem.priority))
		binding.currentPrioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
			override fun onItemSelected(parent: AdapterView<*>?,
			                            view: View?,
			                            position: Int,
			                            id: Long
			) {
				when(position){
					0 -> {
						(parent?.getChildAt(0) as TextView).setTextColor(
								ContextCompat.getColor(requireContext(),R.color.LightRed))

					}
					1->{
						(parent?.getChildAt(0) as TextView).setTextColor(
								ContextCompat.getColor(requireContext(),R.color.yellow))
					}
					2 -> {
						(parent?.getChildAt(0) as TextView).setTextColor(
								ContextCompat.getColor(requireContext(),R.color.green))
					}
				}
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {
				TODO("Not yet implemented")
			}

		}

		return binding.root

	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.update_fragment_menu,menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.menu_save -> {
				updateItem()
			}
			R.id.menu_delete -> {
				confirmItemRemoval()
			}
		}
		return super.onOptionsItemSelected(item)
	}

	private fun confirmItemRemoval() {
		AlertDialog.Builder(requireContext()).let {
			it.setPositiveButton(getString(R.string.confirm_delete)){ _, _ ->
				mTaskViewModel.deleteData(args.currentItem)
				Toast.makeText(requireContext(), getString(
										R.string.successfully_delete) + args.currentItem.title, Toast.LENGTH_SHORT).show()
				findNavController().navigate(R.id.action_updateFragment_to_noteListFragment)
			}
			it.setNegativeButton(getString(R.string.cancel_delete)){ _, _ -> }
			it.setTitle(getString(R.string.ask_for_confirm_delete))
			it.setMessage("${getString(R.string.confirm_delete_sentence)}${args.currentItem.title}？")
			it.create().show()
		}
	}

	private fun updateItem() {
		val title = binding.currentTitleEdittext.text.toString()
		val description = binding.currentDescriptionEdittext.text.toString()
		val priority = binding.currentPrioritySpinner.selectedItem.toString()
		val taskType = binding.currentTaskTypeSpinner.selectedItem.toString()

		if(SharedViewModel.verifyDataFromUser(title,description)){
			val updatedItem = TaskData(
					args.currentItem.id,
					title,
					SharedViewModel.parsePriority(priority,resources),
					SharedViewModel.parseTaskType(taskType,resources),
					description
			)
			mTaskViewModel.updateData(updatedItem)
			Toast.makeText(requireContext(),"修改成功！",Toast.LENGTH_SHORT).show()
			findNavController().navigate(R.id.action_updateFragment_to_noteListFragment)
		}else{
			Toast.makeText(requireContext(),"修改失败！请检查输入",Toast.LENGTH_SHORT).show()

		}

	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}


}