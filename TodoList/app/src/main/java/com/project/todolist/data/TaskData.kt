package com.project.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskData(
	@PrimaryKey(autoGenerate = true)
	var id: Int,
	var title: String,
	var priority: Priority,
	var taskType: TaskType,
	var description: String
)