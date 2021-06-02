package com.project.todolist.fragments.noteList.adapter

import androidx.recyclerview.widget.DiffUtil
import com.project.todolist.data.models.TaskData

class TaskDiffUtil(
		private val oldList: List<TaskData>,
		private val newList: List<TaskData>
):DiffUtil.Callback() {
	override fun getOldListSize(): Int {
		return oldList.size
	}

	override fun getNewListSize(): Int {
		return newList.size
	}

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return  oldList[oldItemPosition] === newList[newItemPosition]
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition].id == newList[newItemPosition].id
				&& oldList[oldItemPosition].title == newList[newItemPosition].title
				&& oldList[oldItemPosition].description == newList[newItemPosition].description
				&& oldList[oldItemPosition].priority == newList[newItemPosition].priority
				&& oldList[oldItemPosition].taskType == newList[newItemPosition].taskType
	}
}