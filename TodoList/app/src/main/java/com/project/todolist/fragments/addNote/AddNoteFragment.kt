package com.project.todolist.fragments.addNote

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.project.todolist.R
import com.project.todolist.databinding.FragmentAddNoteBinding


class AddNoteFragment : Fragment() {
	private lateinit var binding: FragmentAddNoteBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
							 ): View? {
		binding = FragmentAddNoteBinding.inflate(inflater, container, false)
		val view = binding.root

		setHasOptionsMenu(true)
		// Inflate the layout for this fragment

		return view
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.add_fragment_menu,menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return super.onOptionsItemSelected(item)
	}



}