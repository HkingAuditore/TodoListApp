package com.project.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.mut_jaeryo.circletimer.CircleTimer
import com.owl93.dpb.CircularProgressView
import com.project.todolist.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {
	lateinit var binding: ActivityTimerBinding
	lateinit var timerProgressView : CircularProgressView

	var countDownTime : Long = 0

	var timer: CountDownTimer? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityTimerBinding.inflate(layoutInflater)
		val view = binding.root

		timerProgressView = binding.progressView


		binding.bottomBarView.background = null
		binding.bottomBarView.menu.getItem(1).isEnabled = false
		binding.bottomBarView.menu.getItem(2).isChecked = true
		binding.bottomBarView.setOnNavigationItemSelectedListener {
			when(it.itemId){
				R.id.item_task ->{
					val intent = Intent(this, MainActivity::class.java)
					finish()
				}
				R.id.item_clock ->{

				}
			}
			true
		}

		setTimer(0,1,0)

		binding.floatingActionButton.setOnClickListener {
			val intent = Intent(this, TimePickerActivity::class.java)
			startActivity(intent)
		}



		setContentView(view)

//		setContentView(R.layout.activity_timer)
	}

	fun setTimer(h:Int,m:Int,s:Int){
		countDownTime = ((3600 * h +60 * m + s)*1000).toLong()

		timer = (object: CountDownTimer(countDownTime, 10) {
			override fun onTick(millisUntilFinished: Long) {
				showTime(millisUntilFinished)
			}

			override fun onFinish() {
			}
		})

		(timer as CountDownTimer).start()
	}

	fun showTime(h:Int,m:Int,s:Int){
		timerProgressView.text = "${h.toString().padStart(2, '0')} : ${m.toString().padStart(2, '0')} : ${s.toString().padStart(2, '0')}"

	}

	fun showTime(ms : Long){
		val second : Long = ms / 1000
		val remainMinute : Int = (second / 60).toInt()
		val remainHour : Int = (second / 3600).toInt()
		timerProgressView.progress = 100.0F - (ms/ countDownTime.toFloat()) * 100.0F
		showTime(remainHour,remainMinute - 60 * remainHour,second.toInt() - 60 * remainMinute)

	}
}