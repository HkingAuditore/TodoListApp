package com.project.todolist.view

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputLayout
import com.project.todolist.R

class CustomSpinner: ConstraintLayout{

	var textView: AutoCompleteTextView? = null
		public get() = field
		private set(value) {
			field = value
		}
	var inputLayout : TextInputLayout? = null
	private var stringArray : Array<String> = arrayOf("null")

	var onClick : ISpinnerSelectedDelegate = object : ISpinnerSelectedDelegate {
		override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long,customSpinner: CustomSpinner) {
			Log.d("TO_DO_SPINNER","On Click")
		}
	}


	var selectedItem : String? = null
		public get() = field
		set(value) {
			Log.d("SQL", "value:$value")
			field =value
		}



	@DrawableRes
	var icon: Int = 0
		set(value) {
			field = value
			inputLayout?.startIconDrawable = context.getDrawable(value)
		}

	var label: CharSequence? = null
		set(value) {
			field = value
			inputLayout?.hint = value
		}

	var entries: Int = 0
		set(value) {
			field = value
			stringArray = resources.getStringArray(value)
		}

	var cursorOutlineColor: ColorStateList = context.getColorStateList(R.color.spinner_color_states)
		set(value) {
			field = value
			inputLayout?.setBoxStrokeColorStateList(value)
			inputLayout?.defaultHintTextColor = value
			inputLayout?.hintTextColor = value
			inputLayout?.setEndIconTintList(value)
			inputLayout?.setStartIconTintList(value)
//			Log.d("TO_DO_SPINNER",value.toString())
//			Log.d("TO_DO_SPINNER",inputLayout.toString())
		}


	constructor(context: Context) : this(context, null)
	constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		attrs?.let { retrieveAttributes(attrs) }
		init(context)
		attrs?.let { retrieveAttributes(attrs) }

	}



	@RequiresApi(Build.VERSION_CODES.R)
	private fun init(context: Context) {

		val view = View.inflate(context, R.layout.custom_spinner, this)//获取布局
		textView = view.findViewById(R.id.autoCompleteTextView)
		inputLayout = view.findViewById(R.id.customSpinner)

		val arrayAdapter = ArrayAdapter(context,
										R.layout.custom_deopdown_item,
										stringArray)

		textView?.setAdapter(arrayAdapter)


		AdapterView.OnItemClickListener { parent, view, position, id ->
			selectedItem = stringArray[id.toInt()]
			onClick.onItemClick(parent,view,position,id,this@CustomSpinner)
		}.also { textView?.onItemClickListener = it }
	}

	private fun retrieveAttributes(attrs: AttributeSet) {
		val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSpinner)

		val iconRes = typedArray.getResourceId(R.styleable.CustomSpinner_icon, 0)
		if (iconRes != 0)
			icon = iconRes

		label = typedArray.getText(R.styleable.CustomSpinner_label)
		val cursorColor = typedArray.getColorStateList(R.styleable.CustomSpinner_cursorOutlineColor)
		if (cursorColor != null) {
			Log.d("TO_DO_SPINNER",cursorColor.toString())
			cursorOutlineColor = cursorColor
		}

		val entriesRes = typedArray.getResourceId(R.styleable.CustomSpinner_entries,0)
		if (entriesRes != 0)
			entries = entriesRes

		typedArray.recycle()
	}





}