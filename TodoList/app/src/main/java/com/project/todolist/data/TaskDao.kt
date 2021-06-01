package com.project.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.todolist.data.models.TaskData

@Dao
interface TaskDao {

	@Query("SELECT * FROM task_table ORDER BY id ASC")
	fun getAllData(): LiveData<List<TaskData>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insertData(taskData: TaskData)

	@Update
	suspend fun updateData(taskData: TaskData)

	@Delete
	suspend fun deleteItem(taskData: TaskData)

	@Query("DELETE FROM task_table")
	suspend fun deleteAll()

}