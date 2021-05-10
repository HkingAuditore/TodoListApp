package com.project.todolist.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskType

@Entity(tableName = "task_table")
data class TaskData(
	@PrimaryKey(autoGenerate = true)
	var id: Int,
	var title: String,
	var priority: Priority,
	var taskType: TaskType,
	var description: String
)