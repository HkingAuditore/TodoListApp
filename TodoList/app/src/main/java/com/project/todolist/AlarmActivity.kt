package com.project.todolist

import android.animation.Animator
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.animation.Animation
import androidx.core.content.ContextCompat
import com.project.todolist.databinding.ActivityAlarmBinding
import com.project.todolist.databinding.ActivityTimerBinding
import kotlin.concurrent.fixedRateTimer

class AlarmActivity : AppCompatActivity() {
	lateinit var binding : ActivityAlarmBinding
	lateinit var timer: java.util.Timer
	var minute: Int = 0
	var isShowMinute: Boolean = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityAlarmBinding.inflate(layoutInflater)
		val view = binding.root

		binding.animationFinishView.setOnClickListener {
			binding.animationFinishView.speed = -1f
			binding.animationFinishView.playAnimation()
			binding.animationFinishView.frame = 40
			binding.animationFinishView.addAnimatorListener(object : Animator.AnimatorListener{
				override fun onAnimationStart(animation: Animator?) {

				}

				override fun onAnimationEnd(animation: Animator?) {
					finish()
				}

				override fun onAnimationCancel(animation: Animator?) {

				}

				override fun onAnimationRepeat(animation: Animator?) {

				}

			})
//			binding.animationFinishView.addAnimatorUpdateListener {
//				if(binding.animationFinishView.progress <= 0.0001){
//					finish()
//				}
//			}

		}
		Log.d("ALARM","ACTIVITY RECEIVE: ${ intent.getIntExtra("TIME",0) / 60}")
		minute = intent.getIntExtra("TIME",0) / 60

		setContentView(view)

		val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
		if (Build.VERSION.SDK_INT >= 26) {
			vibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.EFFECT_HEAVY_CLICK))
		} else {
			vibrator.vibrate(200)
		}
		timer = fixedRateTimer("", false, 0, 2000) {
			binding.customSwitcher.let {
				if(isShowMinute){
					it.animateText("番茄钟结束")
					isShowMinute = false
				}else{
					it.animateText("$minute 分番茄钟")
					isShowMinute = true

				}
			}
		}



	}
}