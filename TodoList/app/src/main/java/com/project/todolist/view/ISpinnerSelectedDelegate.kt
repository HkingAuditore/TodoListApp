package com.project.todolist.view

import android.view.View
import android.widget.AdapterView

interface ISpinnerSelectedDelegate {
	fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long,customSpinner: CustomSpinner)
}