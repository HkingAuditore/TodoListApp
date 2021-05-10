package com.project.todolist.data

import android.content.Context
import androidx.room.*
import com.project.todolist.data.models.TaskData

@Database(entities = [TaskData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TaskDatabase: RoomDatabase() {
	abstract fun taskDao(): TaskDao

	companion object{
		//单例模式

		@Volatile
		private var INSTANCE : TaskDatabase? = null

		fun getDatabase(context: Context): TaskDatabase{
			val tempInstance = INSTANCE
			if(tempInstance!=null){
				return tempInstance
			}

			synchronized(this){
				val instance = Room.databaseBuilder(
					context.applicationContext,
					TaskDatabase::class.java,
					"task_database"
												   ).build()
				INSTANCE = instance
				return instance
			}
		}
	}
}