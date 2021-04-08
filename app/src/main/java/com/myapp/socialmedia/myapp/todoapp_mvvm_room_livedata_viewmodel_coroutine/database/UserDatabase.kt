package com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.database

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.dao.UserDao
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract fun getDao():UserDao

    companion object{
        private const val DATABASE_NAME= "UserDatabase"

        @Volatile
        var userDatabaseInstance: UserDatabase ? = null

        fun createdb(context: Context):UserDatabase?
        {
            if (userDatabaseInstance==null){
                synchronized(UserDatabase::class.java){
                    if (userDatabaseInstance==null){
                        userDatabaseInstance= Room.databaseBuilder(context,UserDatabase::class.java, DATABASE_NAME)
                            .build()
                    }
                }
            }
            return userDatabaseInstance
        }
    }
}