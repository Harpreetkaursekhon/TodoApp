package com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class User (val note:String, val body:String){

    @PrimaryKey(autoGenerate = true)
    var id:Int?= null

}