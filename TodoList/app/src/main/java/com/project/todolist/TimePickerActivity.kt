package com.project.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.project.todolist.databinding.ActivityTimePickerBinding
import com.royrodriguez.transitionbutton.TransitionButton
import com.royrodriguez.transitionbutton.TransitionButton.OnAnimationStopEndListener


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

//		binding.confirmTimeButton.setOnClickListener {
//			binding.confirmTimeButton.run {
//				this.startAnimation()
//				this.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND,TransitionButton.OnAnimationStopEndListener {
//					val intent = Intent(context, TimerActivity::class.java)
//					intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//					startActivity(intent)
//				})
//			}
//
//		}

		binding.confirmTimeButton.setOnClickListener {

			binding.confirmTimeButton.startAnimation()
			Handler().postDelayed(Runnable {
				binding.confirmTimeButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND,
				                                        OnAnimationStopEndListener {
					                                        val data = Intent()
					                                        data.putExtra("time", hour*3600L+minute*60L);

					                                        setResult(Activity.RESULT_OK, data);
					                                        finish()
					                                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
				                                        })
			}, 1000)

		}

		setContentView(view)
	}

	private fun setTimeText() {
		binding.timeTextView.text = "${hour} 小时 ${minute} 分钟"
	}
}