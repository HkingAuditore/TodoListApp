package com.project.todolist.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import com.project.todolist.receiver.AlarmReceiver
import java.util.*

class AlarmService(private val context: Context) {

	companion object{
		/**
		 * 跳转目标
		 */
		private var alarmIntent : PendingIntent? = null
		private var intent : Intent? = null

	}

	/**
	 * 闹钟管理
	 */
	private var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

	/**
	 * 设置闹钟
	 */
	fun setExactAlarm(timeInMillis: Long){
		if(alarmIntent != null) {
			Log.d("ALARM_INTENT","CANCEL")
			alarmManager.cancel(alarmIntent)
			alarmIntent?.cancel()
			alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
		}
		Log.d("ALARM", timeInMillis.toString())
		setAlarm(
				timeInMillis,
				getPendingIntent(
						getIntent().apply {
							action = "ALARM"
							putExtra("ALARM_TIME",timeInMillis)
						}
				))
	}

	private fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent){
		Log.d("ALARM","SEND TIME:" + timeInMillis.toString())
		alarmManager.setExactAndAllowWhileIdle(
					AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + timeInMillis,
					pendingIntent
			)
	}



	private fun getIntent(): Intent {
		intent = Intent(context, AlarmReceiver::class.java)
		return intent!!
	}

	private fun getPendingIntent(intent: Intent): PendingIntent {
		alarmIntent = PendingIntent.getBroadcast(
				context, 0, intent, 0

		)
		return alarmIntent!!
	}

	/**
	 * 取消闹钟
	 */
	fun cancel() {
		alarmManager.cancel(alarmIntent)
		alarmIntent?.cancel()
		alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
	}
}