package com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.model.User
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.repository.UserRepository

class UserViewModel: ViewModel() {

    fun insert(context: Context, user: User){
        UserRepository.insert(context, user)
    }

    fun getDatafromAllUser(context: Context): LiveData<List<User>>? {
       return UserRepository.getDatafromAllUsers(context)
    }
}