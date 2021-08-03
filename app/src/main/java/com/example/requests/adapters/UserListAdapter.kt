package com.example.requests.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.requests.R
import com.example.requests.api.UserJson
import kotlinx.coroutines.CoroutineScope

class UserListAdapter(private val context: Activity, private val arrayList: ArrayList<UserJson>) :
    ArrayAdapter<UserJson>(context, R.layout.userlistrow, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.userlistrow, null)

        val username : TextView = view.findViewById(R.id.username)
        val website : TextView = view.findViewById(R.id.website)
        val email : TextView = view.findViewById(R.id.email)

        username.text = arrayList[position].username
        website.text = arrayList[position].website
        email.text = arrayList[position].email

        return view
    }
}