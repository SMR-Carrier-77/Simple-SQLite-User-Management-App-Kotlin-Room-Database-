package com.example.miasqlite_24_25.Views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.Update
import com.example.miasqlite_24_25.Views.AddUserActivity.Companion.update
import com.example.miasqlite_24_25.databinding.ActivityAddUserBinding
import com.example.miasqlite_24_25.db.User
import com.example.miasqlite_24_25.db.UserDao
import com.example.miasqlite_24_25.db.UserDatabase

@Suppress("DEPRECATION")
class AddUserActivity : AppCompatActivity() {
    companion object{
        const val editKey="edit"
        const val update="Update user"
        const val add="Add User"
    }
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var dao: UserDao
    var userId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db= Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "User_DB"
        ). allowMainThreadQueries().build()
         dao=db.getUserDao()

        if (intent.hasExtra(editKey)){
            val user=intent.getParcelableExtra<User>("edit")
            binding.btnAddContact.text=update

            user?.let{
                binding.apply {
                    editName.setText(it.name)
                    editAge.setText(it.age.toString())
                    editPhone.setText(it.number)
                    userId=it.id
                }
            }

        }else{
            binding.btnAddContact.text=add
        }


         binding.btnAddContact.setOnClickListener {

             val name=binding.editName.text.toString().trim()
             val age=binding.editAge.text.toString().trim()
             val phone=binding.editPhone.text.toString().trim()

             if (binding.btnAddContact.text.toString()==add){
                 addUser(name,age,phone)
             }else{
                 updateUser(name,age,phone)
             }




         }


    }

    private fun updateUser(name: String, age: String, phone: String) {

        var user= User(userId, name, age.toInt(),phone)
        dao.editUser(user)
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private  fun  addUser(name: String, age: String, phone: String) {

        var user= User(0, name, age.toInt(),phone)
        dao.addUser(user)
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


