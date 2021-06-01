package com.project.todolist.fragments.noteList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.R
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.Priority.*
import com.project.todolist.data.models.TaskData
import com.project.todolist.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.TaskViewHolder>() {
	var dataList = emptyList<TaskData>()

	class TaskViewHolder(binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		val binding = binding
			get() = field
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
		val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return TaskViewHolder(binding)
	}

	override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
		holder.binding.titleText.text = dataList[position].title
		holder.binding.descriptionText.text = dataList[position].description
		holder.binding.rowBackground.setOnClickListener{
			val action = NoteListFragmentDirections.actionNoteListFragmentToUpdateFragment(dataList[position])
			holder.itemView.findNavController().navigate(action)
		}

		when (dataList[position].priority) {
			HIGH -> holder.binding.priorityIndicator.setCardBackgroundColor(
					ContextCompat.getColor(
							holder.itemView.context,
							R.color.red
					)
			)
			MEDIUM -> holder.binding.priorityIndicator.setCardBackgroundColor(
					ContextCompat.getColor(
							holder.itemView.context,
							R.color.yellow
					)
			)
			LOW -> holder.binding.priorityIndicator.setCardBackgroundColor(
					ContextCompat.getColor(
							holder.itemView.context,
							R.color.green
					)
			)
		}
	}

	override fun getItemCount(): Int {
		return dataList.size
	}

	fun setData(taskData: List<TaskData>){
		this.dataList = taskData
		notifyDataSetChanged()
	}

}