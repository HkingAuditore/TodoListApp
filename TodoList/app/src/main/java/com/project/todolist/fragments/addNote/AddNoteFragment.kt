package com.project.todolist.fragments.addNote

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.todolist.R
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskData
import com.project.todolist.data.models.TaskType
import com.project.todolist.data.viewModel.TaskViewModel
import com.project.todolist.databinding.FragmentAddNoteBinding
import com.project.todolist.fragments.SharedViewModel


class AddNoteFragment : Fragment() {
	private lateinit var binding: FragmentAddNoteBinding
	private val mTaskViewModel : TaskViewModel by viewModels()
	private val mSharedViewModel : SharedViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
							 ): View? {
		binding = FragmentAddNoteBinding.inflate(inflater, container, false)
		val view = binding.root
		setHasOptionsMenu(true)

		binding.prioritySpinner.onClick = mSharedViewModel.listener

//		//spinner
//		val priority = resources.getStringArray(R.array.priority)
//		val priorityArrayAdapter = ArrayAdapter(requireContext(),R.layout.custom_deopdown_item,priority)
//		binding.prioritySpinner.adapter = priorityArrayAdapter
//
//		val taskType = resources.getStringArray(R.array.taskType)
//		val taskTypeArrayAdapter = ArrayAdapter(requireContext(),R.layout.custom_deopdown_item,taskType)
//		binding.taskTypeSpinner.adapter = taskTypeArrayAdapter


		return view
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.add_fragment_menu,menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if(item.itemId == R.id.menu_add){
			insertDataToDataBase()
		}
		return super.onOptionsItemSelected(item)
	}

	private fun insertDataToDataBase() {
		val mTitle = binding.titleEditText.text.toString()
		val mPriority = binding.prioritySpinner.selectedItem.toString()
		val mTaskType = binding.taskTypeSpinner.selectedItem.toString()
		Log.d("SQL", mPriority)
		Log.d("SQL", mTaskType)
		val mDescription = binding.filledTextField.editText?.text.toString()

		if (SharedViewModel.verifyDataFromUser(mTitle,mDescription)){
			val newData = TaskData(
				0,
				mTitle,
				SharedViewModel.parsePriority(mPriority,resources),
				SharedViewModel.parseTaskType(mTaskType,resources),
				mDescription
								  )

			mTaskViewModel.insertData(newData)
			Toast.makeText(requireContext(),"录入成功！",Toast.LENGTH_SHORT).show()
			findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)
		}else{
			Toast.makeText(requireContext(),"录入失败，请检查输入！",Toast.LENGTH_SHORT).show()
		}


	}


}