package com.project.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

	@Query("SELECT * FROM task_table ORDER BY id ASC")
	fun getAllData(): LiveData<List<TaskData>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insertData(taskData: TaskData)



}