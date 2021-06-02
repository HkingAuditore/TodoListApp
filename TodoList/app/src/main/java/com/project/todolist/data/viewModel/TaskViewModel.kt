package com.project.todolist.data.viewModel

import android.app.Application
import android.app.DownloadManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.project.todolist.data.TaskDatabase
import com.project.todolist.data.models.TaskData
import com.project.todolist.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
	private val taskDao = TaskDatabase.getDatabase(application).taskDao()
	private val repository: TaskRepository

	val getAllData: LiveData<List<TaskData>>
	val sortByHighPriority: LiveData<List<TaskData>>


	init {
		repository = TaskRepository(taskDao)
		getAllData = repository.getAllData
		sortByHighPriority = repository.getSortByHighPriority

	}

	fun insertData(taskData: TaskData) {
		viewModelScope.launch(Dispatchers.IO) {
			repository.insertData(taskData)
		}
	}

	fun updateData(taskData: TaskData){
		viewModelScope.launch ( Dispatchers.IO ){
			repository.updateData(taskData)
		}
	}

	fun deleteData(taskData: TaskData){
		viewModelScope.launch ( Dispatchers.IO ){
			repository.deleteItem(taskData)
		}
	}

	fun deleteAll(){
		viewModelScope.launch ( Dispatchers.IO ){
			repository.deleteAll()
		}
	}

	fun searchDatabase(searchQuery: String): LiveData<List<TaskData>>{
		return repository.searchDatabase(searchQuery)
	}

	fun filterTaskTypeDatabase(taskType :String): LiveData<List<TaskData>>{
		return repository.filterTaskTypeDatabase(taskType)
	}


}