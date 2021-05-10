package com.project.todolist.data

import androidx.room.TypeConverter
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskType

class Converter {
	@TypeConverter
	fun priorityToString(priority: Priority): String{
		return priority.name
	}

	@TypeConverter
	fun stringToPriority(string: String): Priority {
		return Priority.valueOf(string)
	}

	@TypeConverter
	fun taskTypeToString(taskType: TaskType): String{
		return taskType.name
	}

	@TypeConverter
	fun stringToTaskType(string: String): TaskType {
		return TaskType.valueOf(string)
	}
}