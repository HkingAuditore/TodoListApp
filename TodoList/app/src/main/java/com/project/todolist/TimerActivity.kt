package com.project.todolist

import android.animation.Animator
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.owl93.dpb.CircularProgressView
import com.project.todolist.animation.startAnimation
import com.project.todolist.databinding.ActivityTimerBinding
import com.project.todolist.service.AlarmService

class TimerActivity : AppCompatActivity() {
	lateinit var binding: ActivityTimerBinding
	lateinit var timerProgressView : CircularProgressView

	private var alarmService:AlarmService? = null


	var view: View? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityTimerBinding.inflate(layoutInflater)
		view = binding.root
		alarmService = AlarmService(this)

		timerProgressView = binding.progressView
		title = "番茄钟"

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
		if(Timer.timer != null && !Timer.isFinished){
			Timer.circularProgressView = binding.progressView
			binding.animationCancelView.let {
				it.progress = 1f
				it.addAnimatorListener(object : Animator.AnimatorListener{
					override fun onAnimationStart(animation: Animator?) {

					}

					override fun onAnimationEnd(animation: Animator?) {
						it.setOnClickListener { btn ->
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

					override fun onAnimationCancel(animation: Animator?) {

					}

					override fun onAnimationRepeat(animation: Animator?) {

					}

				})
			}
			Timer.cancelBtn = binding.animationCancelView
		}

//		setTimer(0,1,0)

		binding.floatingActionButton.setOnClickListener {
			animateNavigate()
		}



		setContentView(view)

//		setContentView(R.layout.activity_timer)
	}

	fun setTimer(h:Int,m:Int,s:Int){
		setTimer((3600 * h +60 * m + s).toLong())
	}

	private fun setTimer(s: Long){
		Timer.countDownTime = (s*1000).toLong()
		Timer.circularProgressView = binding.progressView
		Timer.cancelBtn= binding.animationCancelView
		setAlarm(s)
	}




	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if(resultCode != Activity.RESULT_OK)
			return
		when(requestCode){
			0 -> {
				Log.d("ALARM","GET TIME: ${data?.getLongExtra("time",0)}")
				data?.getLongExtra("time",0)?.let {
					setTimer(it)
				}
				binding.animationCancelView.let{
					it.postDelayed(Runnable {
						it.speed = 1f
						it.playAnimation()
						it.addAnimatorListener(object : Animator.AnimatorListener{
							override fun onAnimationStart(animation: Animator?) {

							}

							override fun onAnimationEnd(animation: Animator?) {
								it.setOnClickListener { btn ->
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

							override fun onAnimationCancel(animation: Animator?) {

							}

							override fun onAnimationRepeat(animation: Animator?) {

							}

						})
					},500)

				}

			}
		}
	}

	/************************动画**********************/
	fun animateNavigate(){
		//圆圈过渡动画
		val animation = AnimationUtils.loadAnimation(this@TimerActivity, R.anim.circle_explosion_anim).apply {
			duration = 500
			interpolator = AccelerateDecelerateInterpolator()

		}
		binding.floatingActionButton.isVisible = false
		binding.animationCircle.isVisible = true
		binding.animationCircle.startAnimation(animation){
			binding.root.setBackgroundColor(ContextCompat.getColor(this@TimerActivity, R.color.colorPrimary))
			binding.constraintLayout.isVisible = false
			binding.bottomBar.isVisible = false
			val intent = Intent(this, TimePickerActivity::class.java)
			startActivityForResult(intent, 0)
			overridePendingTransition(R.anim.fragment_open_enter,R.anim.fragment_open_exit)
		}

	}

	override fun onResume() {
		super.onResume()
		binding?.root?.setBackgroundColor(ContextCompat.getColor(this@TimerActivity, R.color.white))
		binding.constraintLayout.isVisible = true
		binding.bottomBar.isVisible = true
		binding.floatingActionButton.isVisible = true
		binding.animationCircle.isVisible = false
		setContentView(view)
	}


	private fun setAlarm(s : Long){
		alarmService?.setExactAlarm(s * 1000 )
	}

	private fun cancelAlarm(){
		alarmService?.cancel()
	}

	private fun cancel(){
		Timer.cancel()
		cancelAlarm()
	}


}