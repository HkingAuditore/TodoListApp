package com.project.todolist.fragments.noteList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.todolist.data.models.TaskData
import com.project.todolist.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.TaskViewHolder>() {

	var dataList = emptyList<TaskData>()
		get() = field
		private set(value) {
			field = value
		}


	class TaskViewHolder(binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
		val binding = binding
			get() = field

		fun bind(taskData: TaskData){
			binding.taskData = taskData
			binding.executePendingBindings()
		}

		companion object{

			fun from(parent: ViewGroup): TaskViewHolder {
				return TaskViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
		return TaskViewHolder.from(parent)
	}


	override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
		//已淘汰
//		holder.binding.titleText.text = dataList[position].title
//		holder.binding.descriptionText.text = dataList[position].description
//		holder.binding.rowBackground.setOnClickListener{
//			val action = NoteListFragmentDirections.actionNoteListFragmentToUpdateFragment(dataList[position])
//			holder.itemView.findNavController().navigate(action)
//		}
//
//		when (dataList[position].priority) {
//			HIGH -> holder.binding.priorityIndicator.setCardBackgroundColor(
//					ContextCompat.getColor(
//							holder.itemView.context,
//							R.color.red
//					)
//			)
//			MEDIUM -> holder.binding.priorityIndicator.setCardBackgroundColor(
//					ContextCompat.getColor(
//							holder.itemView.context,
//							R.color.yellow
//					)
//			)
//			LOW -> holder.binding.priorityIndicator.setCardBackgroundColor(
//					ContextCompat.getColor(
//							holder.itemView.context,
//							R.color.green
//					)
//			)
//		}
		val currentItem = dataList[position]
		holder.bind(currentItem)
	}

	override fun getItemCount(): Int {
		return dataList.size
	}

	fun setData(taskData: List<TaskData>){
		val taskDiffUtil = TaskDiffUtil(dataList,taskData)
		val taskDiffResult = DiffUtil.calculateDiff(taskDiffUtil)
		this.dataList = taskData
		taskDiffResult.dispatchUpdatesTo(this)
//		notifyDataSetChanged()
	}

}