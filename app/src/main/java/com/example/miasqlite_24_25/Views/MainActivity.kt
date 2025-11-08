package com.example.miasqlite_24_25.Views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.miasqlite_24_25.databinding.ActivityMainBinding
import com.example.miasqlite_24_25.db.User
import com.example.miasqlite_24_25.db.UserDao
import com.example.miasqlite_24_25.db.UserDatabase

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), UserAdapter.HandelUserClick {
    lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: UserAdapter
   val love="i love "

    private lateinit var dao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val db= Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
           " User_DB"
        ). allowMainThreadQueries().build()
         dao=db.getUserDao()


          dao.getAllUser().apply {
           userAdapter= UserAdapter(this@MainActivity,this)
              binding.rv.adapter=userAdapter
          }


        binding.btn2.setOnClickListener {
            val intent= Intent(this@MainActivity, AddUserActivity::class.java)
            startActivity(intent)
        }
         }

    override fun onEditClick(user: User) {
        val editintent= Intent(this@MainActivity, AddUserActivity::class.java)
        editintent.putExtra(AddUserActivity.editKey,user)
        startActivity(editintent)
    }


}
