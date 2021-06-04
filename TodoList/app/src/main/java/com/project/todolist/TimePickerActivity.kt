package com.project.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.project.todolist.databinding.ActivityTimePickerBinding
import com.project.todolist.databinding.ActivityTimerBinding

class TimePickerActivity : AppCompatActivity() {
	lateinit var binding: ActivityTimePickerBinding

	var hour: Int = 0
	var minute: Int = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityTimePickerBinding.inflate(layoutInflater)
		val view = binding.root







		binding.seekHour.setOnSeekBarChangeListener { _, curValue ->
			hour = curValue
			setTimeText()
		}

		binding.seekMinute.setOnSeekBarChangeListener { _, curValue ->
			minute = curValue
			setTimeText()
		}

		binding.seekMinute.curProcess = 30

		setContentView(view)
	}

	private fun setTimeText() {
		binding.timeTextView.text = "${hour} 小时 ${minute} 分钟"
	}
}