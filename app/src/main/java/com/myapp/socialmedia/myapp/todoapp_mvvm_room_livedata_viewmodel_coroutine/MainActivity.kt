package com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.adapter.UserAdapter
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.databinding.ActivityMainBinding
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.model.User
import com.myapp.socialmedia.myapp.todoapp_mvvm_room_livedata_viewmodel_coroutine.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var alertDialog: AlertDialog
    private lateinit var builder:AlertDialog.Builder
    private lateinit var note:EditText
    private lateinit var body:EditText
    private lateinit var save:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userAdapter = UserAdapter(this, ArrayList<User>())
        //recyclerView = findViewById(R.id.recyclerView)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=userAdapter
        }


        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getDatafromAllUser(applicationContext)?.observe(this, Observer {

            userAdapter.setData(it as ArrayList<User>)

        })
            binding.floatingActionButton.setOnClickListener {
            displayPopup()
        }
    }

    private fun displayPopup() {
    builder=AlertDialog.Builder(this)
        var itemview: View = LayoutInflater.from(applicationContext).inflate(R.layout.popup_view, null)
        alertDialog= builder.create()
        alertDialog.setView(itemview)

        note= itemview.findViewById(R.id.note)
        body= itemview.findViewById(R.id.body)
        save= itemview.findViewById(R.id.save)
        save.setOnClickListener {
            saveDataIntoRoomDb()
        }
            alertDialog.show()

    }

    private fun saveDataIntoRoomDb() {
        val getNote= note.text.toString().trim()
        val getBody= body.text.toString().trim()

        if(!TextUtils.isEmpty(getNote)&& !TextUtils.isEmpty(getBody)){
            userViewModel.insert(this, User(getNote,getBody))
            alertDialog.dismiss()
        }else{
            Toast.makeText(applicationContext, "Fill all the fields", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }
    }
}