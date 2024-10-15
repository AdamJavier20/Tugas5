package com.example.tugas4.view

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas4.R



class UserAdapter(val context: Context, private var cursor: Cursor):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.judul)
        val num = v.findViewById<TextView>(R.id.subjudul)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return if (cursor == null) 0 else cursor.count
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (cursor.moveToPosition(position)) {
            val userName = cursor.getString(cursor.getColumnIndexOrThrow("Username"))
            val userNo = cursor.getString(cursor.getColumnIndexOrThrow("Userno"))

            holder.name.text = userName
            holder.num.text = userNo
        }
    }

    fun swapCursor(newCursor: Cursor?) {
        if (cursor != null) {
            cursor.close()
        }
        cursor = newCursor!!
        if (newCursor != null) {
            notifyDataSetChanged()
        }
    }
}
