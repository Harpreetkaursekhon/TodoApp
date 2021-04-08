package com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.database.UserDatabase
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

    companion object {
        private var userDatabase:UserDatabase ?= null
            // get the Db fun
        fun initialiseDb(context: Context): UserDatabase? {
            return UserDatabase.createdb(context)

        }
        fun insert(context: Context, user: User) {
            userDatabase= initialiseDb(context)
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.insert(user)
            }

        }

        fun getDatafromAllUsers(context: Context): LiveData<List<User>>? {
            userDatabase= initialiseDb(context)
            return userDatabase?.getDao()?.getDatafromAllUsers()

        }

    }
}