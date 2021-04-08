package com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(user: User)

    @Query("SELECT * FROM userTable ORDER BY id ASC")
    fun getDatafromAllUsers():LiveData<List<User>>
}