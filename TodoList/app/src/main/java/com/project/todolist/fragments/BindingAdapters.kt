package com.project.todolist.fragments

import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.ListFragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.todolist.R
import com.project.todolist.data.models.Priority
import com.project.todolist.data.models.TaskData
import com.project.todolist.data.models.TaskType
import com.project.todolist.fragments.noteList.NoteListFragment
import com.project.todolist.fragments.noteList.NoteListFragmentDirections
import com.project.todolist.view.CustomSpinner

class BindingAdapters {

	companion object{

		/**
		 *  导航至添加面板
		 */
		@BindingAdapter("android:navigateToAddFragment")
		@JvmStatic
		fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
			view.setOnClickListener {
				if(navigate){
					view.findFragment<NoteListFragment>().animateNavigate()
				}
			}
		}

		/**
		 * 判定空数据库
		 */
		@BindingAdapter("android:emptyDatabase")
		@JvmStatic
		fun emptyDatabase(view : View, emptyDatabase: MutableLiveData<Boolean>){
			when(emptyDatabase.value) {
				true -> view.visibility = View.VISIBLE
				false -> view.visibility = View.INVISIBLE
			}
		}

		/**
		 * 显示重要性
		 */
		@BindingAdapter("android:parsePriority")
		@JvmStatic
		fun parsePriority(view: CustomSpinner, priority: Priority){
			when(priority){
				Priority.HIGH -> {
					view.textView?.setText(view.textView?.adapter?.getItem(0).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(0).toString()
					view.textView?.setTextColor(ContextCompat.getColor(view.context, R.color.LightRed))
				}
				Priority.MEDIUM -> {
					view.textView?.setText(view.textView?.adapter?.getItem(1).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(1).toString()
					view.textView?.setTextColor(ContextCompat.getColor(view.context, R.color.yellow))
				}
				Priority.LOW -> {
					view.textView?.setText(view.textView?.adapter?.getItem(2).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(2).toString()
					view.textView?.setTextColor(ContextCompat.getColor(view.context, R.color.green))
				}
			}

		}

		/**
		 * 显示事务类别
		 */
		@BindingAdapter("android:parseTaskType")
		@JvmStatic
		fun parseTaskType(view: CustomSpinner, taskType: TaskType){
			when(taskType){
				TaskType.WORK -> {
					view.textView?.setText(view.textView?.adapter?.getItem(0).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(0).toString()
				}
				TaskType.SOCIAL -> {
					view.textView?.setText(view.textView?.adapter?.getItem(2).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(2).toString()

				}
				TaskType.STUDY -> {
					view.textView?.setText(view.textView?.adapter?.getItem(1).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(1).toString()

				}
				TaskType.ENTERTAIN -> {
					view.textView?.setText(view.textView?.adapter?.getItem(3).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(3).toString()

				}
				TaskType.OTHERS -> {
					view.textView?.setText(view.textView?.adapter?.getItem(4).toString(), false)
					view.selectedItem = view.textView?.adapter?.getItem(4).toString()

				}
			}
		}

		/**
		 * 根据重要性显示颜色
		 */
		@BindingAdapter("android:parsePriorityColor")
		@JvmStatic
		fun parsePriorityColor(cardView: CardView, priority: Priority){
			when (priority) {
				Priority.HIGH -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))
				Priority.MEDIUM -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
				Priority.LOW -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
			}
		}

		/**
		 * 根据事务类别显示图标
		 */
		@BindingAdapter("android:parseTaskTypeIcon")
		@JvmStatic
		fun parseTaskTypeIcon(imageView: ImageView,taskType: TaskType){
			when(taskType){
				TaskType.WORK -> imageView.setImageResource(R.drawable.ic_work)
				TaskType.SOCIAL -> imageView.setImageResource(R.drawable.ic_social)
				TaskType.STUDY -> imageView.setImageResource(R.drawable.ic_study)
				TaskType.ENTERTAIN -> imageView.setImageResource(R.drawable.ic_entertain)
				TaskType.OTHERS -> imageView.setImageResource(R.drawable.ic_others)
			}
		}

		/**
		 * 将选定数据发送至修改页面
		 */
		@BindingAdapter("android:sendDataToUpdateFragment")
		@JvmStatic
		fun sendDataToUpdateFragment(view: ConstraintLayout,currentItem: TaskData){
			view.setOnClickListener {

				it.findNavController().navigate(
						NoteListFragmentDirections.actionNoteListFragmentToUpdateFragment(
								currentItem))

			}
		}
	}
}