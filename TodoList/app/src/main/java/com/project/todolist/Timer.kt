package com.project.todolist

import android.animation.Animator
import android.os.CountDownTimer
import com.airbnb.lottie.LottieAnimationView
import com.owl93.dpb.CircularProgressView

class Timer {

	companion object{
		var countDownTime : Long = 0
			get() = field
			set(value) {
				field = value
				if(timer != null){
					(timer as CountDownTimer).cancel()
					timer = null
				}
				isFinished = false
				curTime = 0
				timer = (object: CountDownTimer(Timer.countDownTime , 10) {
					override fun onTick(millisUntilFinished: Long) {
						showTime(millisUntilFinished)
					}

					override fun onFinish() {
						Timer.isFinished = true
						cancelBtn?.let{
							it.speed = -1f
							it.frame = 40
							it.playAnimation()
							it.addAnimatorListener(object : Animator.AnimatorListener{
								override fun onAnimationStart(animation: Animator?) {

								}

								override fun onAnimationEnd(animation: Animator?) {
									cancel()
									it.setOnClickListener{

									}
									it.removeAllAnimatorListeners()

								}

								override fun onAnimationCancel(animation: Animator?) {

								}

								override fun onAnimationRepeat(animation: Animator?) {

								}

							})
						}
					}
				})
				(timer as CountDownTimer).start()
			}

		var curTime : Long = 0
			get() = field

		var timer: CountDownTimer? = null
			get() = field

		var isFinished : Boolean = false
			get() = field

		var circularProgressView : CircularProgressView? = null

		var cancelBtn : LottieAnimationView? = null




		fun showTime(h:Int,m:Int,s:Int){
			circularProgressView?.text = "${h.toString().padStart(2, '0')} : ${m.toString().padStart(2, '0')} : ${s.toString().padStart(2, '0')}"

		}

		fun showTime(ms : Long){
			val second : Long = ms / 1000
			val remainMinute : Int = (second / 60).toInt()
			val remainHour : Int = (second / 3600).toInt()
			circularProgressView?.progress = 100.0F - (ms/ Timer.countDownTime.toFloat()) * 100.0F
			showTime(remainHour,remainMinute - 60 * remainHour,second.toInt() - 60 * remainMinute)

		}

		fun cancel(){
			curTime = 0
			(timer as CountDownTimer).cancel()
			timer = null

			showTime(0L)
		}





	}

}