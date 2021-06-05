package com.project.todolist.receiver

import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.project.todolist.AlarmActivity
import com.project.todolist.R
import com.project.todolist.TimePickerActivity
import io.karn.notify.Notify


class AlarmReceiver : BroadcastReceiver() {
	override fun onReceive(context: Context, intent: Intent) {
		val timeInMillis = intent.getLongExtra("ALARM_TIME",0L)
		Log.d("ALARM", "RECEIVER:$timeInMillis")


		when(intent.action){
			"ALARM" -> {
				buildNotification(context,"闹钟","结束了",(timeInMillis / 1000).toInt())
				val intent = Intent(context,AlarmActivity::class.java)
				intent.putExtra("TIME",(timeInMillis / 1000).toInt())
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent)
			}
		}
	}

	private fun buildNotification(context: Context,title: String, message: String, s:Int){
		Notify
			.with(context)
			.meta {
				val intent = Intent(context,AlarmActivity::class.java)
				intent.putExtra("TIME",s)
				clickIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

			}
			.content {
				this.title = title
				this.text = message
			}.show()
	}



}