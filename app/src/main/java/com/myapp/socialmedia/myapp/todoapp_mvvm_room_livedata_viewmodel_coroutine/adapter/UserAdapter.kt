package com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.R
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.model.User

class UserAdapter(private val context: Context, private var userList:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false))
    }

    override fun getItemCount(): Int =userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user:User=userList[position]
        holder.note.text=user.note
        holder.body.text=user.body
    }

    fun setData(userList:ArrayList<User>)
    {
        this.userList=userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var note: TextView =itemView.findViewById(R.id.note)
        var body:TextView=itemView.findViewById(R.id.body)
    }
}