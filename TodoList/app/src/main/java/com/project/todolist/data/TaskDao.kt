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

	@Query("SELECT * FROM task_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
	fun searchDatabase(searchQuery: String): LiveData<List<TaskData>>

	@Query("SELECT * FROM task_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 0 WHEN priority LIKE 'M%' THEN 1 WHEN priority LIKE 'L%' THEN 2 END")
	fun sortByHighPriority(): LiveData<List<TaskData>>

	@Query("SELECT * FROM task_table WHERE taskType = :taskType")
	fun filterTaskType(taskType: String): LiveData<List<TaskData>>

//	@Query("SELECT * FROM task_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 0 WHEN priority LIKE 'M%' THEN 1 WHEN priority LIKE 'L%' THEN 2 END DESC")
//	fun sortByLowPriority(): LiveData<List<TaskData>>
}