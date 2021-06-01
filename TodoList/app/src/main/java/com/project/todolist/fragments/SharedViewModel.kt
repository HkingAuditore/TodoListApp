package com.project.todolist.fragments

import android.app.Application
import android.content.res.Resources
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.project.todolist.R
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskData
import com.project.todolist.data.models.TaskType
import com.project.todolist.view.CustomSpinner
import com.project.todolist.view.ISpinnerSelectedDelegate

class SharedViewModel(application: Application) : AndroidViewModel(application) {

	val emptyDatabase : MutableLiveData<Boolean> = MutableLiveData(true)

	fun checkIfDatabaseEmpty(taskData: List<TaskData>){
		emptyDatabase.value = taskData.isEmpty()
	}


	val listener : ISpinnerSelectedDelegate = object : ISpinnerSelectedDelegate{
		override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long,customSpinner: CustomSpinner) {
			Log.d("TO_DO_SPINNER",parent.parent.toString())
//			when(position){
//				0 -> {
//					customSpinner.textView?.setTextColor(ContextCompat.getColor(application,
//																				R.color.LightRed
//					 														   ))
//				}
//				1->{
//					customSpinner.textView?.setTextColor(ContextCompat.getColor(application,R.color.white))
//				}
//				2 -> {
//					customSpinner.textView?.setTextColor(ContextCompat.getColor(application,R.color.white))
//				}
//			}
		}

	}




	companion object{
		@JvmStatic
		fun parsePriority(priority: String,resources : Resources): Priority {
			return when(priority){
				resources.getStringArray(R.array.priority)[0]-> {
					Priority.HIGH}
				resources.getStringArray(R.array.priority)[1] -> {
					Priority.MEDIUM}
				resources.getStringArray(R.array.priority)[2] -> {
					Priority.LOW}
				else -> {
					Priority.LOW}
			}
		}

		@JvmStatic
		fun parseTaskType(taskType: String,resources : Resources): TaskType {
			return when(taskType){
				resources.getStringArray(R.array.taskType)[0]-> {
					TaskType.WORK}
				resources.getStringArray(R.array.taskType)[1]-> {
					TaskType.STUDY}
				resources.getStringArray(R.array.taskType)[2]-> {
					TaskType.SOCIAL}
				resources.getStringArray(R.array.taskType)[3]-> {
					TaskType.ENTERTAIN}
				resources.getStringArray(R.array.taskType)[4]-> {
					TaskType.OTHERS}

				else -> {
					TaskType.OTHERS}
			}
		}

		@JvmStatic
		fun parsePriority(priority: Priority): Int{
			return when(priority){
				Priority.HIGH -> 0
				Priority.MEDIUM -> 1
				Priority.LOW -> 2
			}
		}

		@JvmStatic
		fun parseTaskType(taskType: TaskType): Int{
			return when(taskType){
				TaskType.WORK -> 0
				TaskType.SOCIAL -> 1
				TaskType.STUDY -> 2
				TaskType.ENTERTAIN -> 3
				TaskType.OTHERS -> 4
			}
		}

		@JvmStatic
		fun verifyDataFromUser(title: String, description: String): Boolean{
			return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
				false
			}else !(title.isEmpty() || description.isEmpty())
		}

	}


}