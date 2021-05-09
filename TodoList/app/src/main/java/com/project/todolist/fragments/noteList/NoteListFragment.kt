package com.project.todolist.fragments.noteList

import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.todolist.R
import com.project.todolist.animation.startAnimation
import com.project.todolist.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {
	private lateinit var binding: FragmentNoteListBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
							 ): View? {

		binding = FragmentNoteListBinding.inflate(inflater, container, false)
		val view = binding.root

		//圆圈过渡动画
		val animation = AnimationUtils.loadAnimation(this.context,R.anim.circle_explosion_anim).apply {
			duration = 500
			interpolator = AccelerateDecelerateInterpolator()

		}

		binding.floatingActionButton.setOnClickListener{
			binding.floatingActionButton.isVisible = false
			binding.circle.isVisible = true
			binding.circle.startAnimation(animation){
				binding.root.setBackgroundColor(ContextCompat.getColor(this.requireContext(),R.color.colorPrimary))
				binding.circle.isVisible = false
				findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
			}

		}

		binding.listLayout.setOnClickListener{
			findNavController().navigate(R.id.action_noteListFragment_to_updateFragment)
		}


		setHasOptionsMenu(true)
		return view
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.note_list_fragment_menu,menu)
	}


}