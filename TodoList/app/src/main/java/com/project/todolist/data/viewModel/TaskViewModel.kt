package com.project.todolist.data.viewModel

import android.app.Application
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
	private val getAllData: LiveData<List<TaskData>>

	init {
		repository = TaskRepository(taskDao)
		getAllData = repository.getAllData
	}

	fun insertData(taskData: TaskData) {
		viewModelScope.launch(Dispatchers.IO) {
			repository.insertData(taskData)
		}
	}

}