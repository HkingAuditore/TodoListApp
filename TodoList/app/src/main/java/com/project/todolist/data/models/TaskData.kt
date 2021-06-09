package com.project.todolist.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskType
import kotlinx.parcelize.Parcelize

@Entity(tableName = "task_table")
@Parcelize
data class TaskData(
	@PrimaryKey(autoGenerate = true)
	/**
	 * 序列号
	 */
	var id: Int,
	/**
	 * 标题
	 */
	var title: String,
	/**
	 * 重要性
	 */
	var priority: Priority,
	/**
	 * 事务类别
	 */
	var taskType: TaskType,
	/**
	 * 描述
	 */
	var description: String
): Parcelable