package com.project.todolist.data.repository

import androidx.lifecycle.LiveData
import com.project.todolist.data.TaskDao
import com.project.todolist.data.models.TaskData

class TaskRepository(private val taskDao : TaskDao) {

	/**
	 * 获取所有待办
	 */
	val getAllData: LiveData<List<TaskData>> = taskDao.getAllData()

	/**
	 * 获取顺列待办
	 */
	val getSortByHighPriority: LiveData<List<TaskData>> = taskDao.sortByHighPriority()
	val getSortByLowPriority: LiveData<List<TaskData>> = taskDao.sortByHighPriority().apply {


		this.value?.reversed()
	}

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

	/**
	 * 根据关键词检索待办
	 */
	fun searchDatabase(searchQuery :String): LiveData<List<TaskData>>{
		return taskDao.searchDatabase(searchQuery)
	}

	/**
	 * 根据事务类型检索待办
	 */
	fun filterTaskTypeDatabase(taskType :String): LiveData<List<TaskData>>{
		return taskDao.filterTaskType(taskType)
	}
}