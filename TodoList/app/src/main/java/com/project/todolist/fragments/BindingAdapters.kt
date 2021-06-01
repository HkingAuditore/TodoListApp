package com.project.todolist.fragments

import android.view.View
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.fragment.app.findFragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.todolist.R
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskType
import com.project.todolist.fragments.noteList.NoteListFragment

class BindingAdapters {

	companion object{

		@BindingAdapter("android:navigateToAddFragment")
		@JvmStatic
		fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
			view.setOnClickListener {
				if(navigate){
					view.findFragment<NoteListFragment>().animateNavigate()
//					view.findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
				}
			}
		}

		@BindingAdapter("android:emptyDatabase")
		@JvmStatic
		fun emptyDatabase(view : View, emptyDatabase: MutableLiveData<Boolean>){
			when(emptyDatabase.value) {
				true -> view.visibility = View.VISIBLE
				false -> view.visibility = View.INVISIBLE
			}
		}

		@BindingAdapter("android:parsePriority")
		@JvmStatic
		fun parsePriority(view: Spinner, priority: Priority){
			view.setSelection(SharedViewModel.parsePriority(priority))
		}

		@BindingAdapter("android:parseTaskType")
		@JvmStatic
		fun parseTaskType(view: Spinner, taskType: TaskType){
			view.setSelection(SharedViewModel.parseTaskType(taskType))
		}
	}
}