package com.project.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.todolist.data.models.TaskData

@Dao
interface TaskDao {


	@Query("SELECT * FROM task_table ORDER BY id ASC")
	/**
	* 获取所有Task数据
	*/
	fun getAllData(): LiveData<List<TaskData>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	/**
	 * 插入数据
	 */
	suspend fun insertData(taskData: TaskData)

	@Update
	/**
	 * 更新数据
	 */
	suspend fun updateData(taskData: TaskData)

	@Delete
	/**
	 * 删除数据
	 */
	suspend fun deleteItem(taskData: TaskData)

	@Query("DELETE FROM task_table")
	/**
	 * 删除所有数据
	 */
	suspend fun deleteAll()

	/**
	 * 根据关键词查找
	 */
	@Query("SELECT * FROM task_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
	fun searchDatabase(searchQuery: String): LiveData<List<TaskData>>

	/**
	 * 根据优先级排序
	 */
	@Query("SELECT * FROM task_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 0 WHEN priority LIKE 'M%' THEN 1 WHEN priority LIKE 'L%' THEN 2 END")
	fun sortByHighPriority(): LiveData<List<TaskData>>

	/**
	 * 根据事务类型选择
	 */
	@Query("SELECT * FROM task_table WHERE taskType = :taskType")
	fun filterTaskType(taskType: String): LiveData<List<TaskData>>

}