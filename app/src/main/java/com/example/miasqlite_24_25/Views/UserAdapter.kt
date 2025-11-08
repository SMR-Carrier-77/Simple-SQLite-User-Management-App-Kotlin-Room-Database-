package com.example.miasqlite_24_25.Views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miasqlite_24_25.databinding.ItemUserBinding
import com.example.miasqlite_24_25.db.User




class UserAdapter(val listener: HandelUserClick,val userList: List<User>): RecyclerView.Adapter<UserAdapter.userVH>() {
      interface HandelUserClick{
          fun onEditClick(user: User)

          fun onLongDeleteClick(user: User)

      }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): userVH {
        return userVH(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(
        holder: userVH,
        position: Int
    ) {
      userList[position].let { user ->
          holder.binding.apply{
           tvusernam.text="Name:${user.name}"
              tvuserage.text="Age:${user.age}"
             tvuserphone.text="Phone:${user.number}"
              editbtn.setOnClickListener {
                listener.onEditClick(user)
              }
              root.setOnLongClickListener {
                  listener.onLongDeleteClick(user)
                  true
              }
          }

      }



    }

    override fun getItemCount():Int =userList.size
    class userVH( val  binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
}