package com.project.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.project.todolist.databinding.ActivityMainBinding
import com.project.todolist.databinding.FragmentUpdateBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {

	lateinit var toggle: ActionBarDrawerToggle
	private var _binding: ActivityMainBinding? = null
	private var binding: ActivityMainBinding
		get() = _binding!!
		set(value) {
			_binding = value
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding.root

		setContentView(view)



		setupActionBarWithNavController(findNavController(R.id.navHostFragment))



	}


	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.navHostFragment)
		return navController.navigateUp() || super.onSupportNavigateUp()
	}
}