package com.project.todolist.fragments.addNote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.project.todolist.R

class AddNoteFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
							 ): View? {
		setHasOptionsMenu(true)
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_add_note, container, false)
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.add_fragment_menu,menu)
	}



}