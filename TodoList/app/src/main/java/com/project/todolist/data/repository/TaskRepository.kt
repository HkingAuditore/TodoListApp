package com.project.todolist.data.repository

import androidx.lifecycle.LiveData
import com.project.todolist.data.TaskDao
import com.project.todolist.data.models.TaskData

class TaskRepository(private val taskDao : TaskDao) {
	val getAllData: LiveData<List<TaskData>> = taskDao.getAllData()

	suspend fun insertData(taskData: TaskData){
		taskDao.insertData(taskData)
	}

	suspend fun updateData(taskData: TaskData){
		taskDao.updateData(taskData)
	}

	suspend fun deleteItem(taskData: TaskData){
		taskDao.deleteItem(taskData)
	}

	suspend fun deleteAll(){
		taskDao.deleteAll()
	}
}