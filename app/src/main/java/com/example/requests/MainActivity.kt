package com.example.requests

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.requests.adapters.UserListAdapter
import com.example.requests.api.PostJson
import com.example.requests.api.UserJson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

//        getUsers()
        createPost()
    }

    private fun getUsers() {
        val api = Retrofit.Builder()
            .baseUrl(ApiRequests.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch ( Dispatchers.IO ) {
            val response = api.getUsers().awaitResponse()

            if (!response.isSuccessful) {
                Log.e("User", "USER FAILED")
            } else {
                val data = response.body()!!
                Log.d("User", "GOT RESPONSE")
                withContext(Dispatchers.Main) {
                    loader.visibility = View.INVISIBLE

                    listUsers.isClickable = true
                    listUsers.adapter = UserListAdapter(context, data as ArrayList<UserJson>)
                    listUsers.setOnItemClickListener { parent, view, position, id ->
                        val i = Intent(context, UserInfo::class.java)
                        i.putExtra("username", data[position].username)
                        i.putExtra("website", data[position].website)
                        i.putExtra("email", data[position].email)
                        startActivity(i)
                    }
                }
            }
        }
    }

    private fun getUser() {
        val api = Retrofit.Builder()
            .baseUrl(ApiRequests.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch ( Dispatchers.IO ) {
            val response = api.getUser(1).awaitResponse()

            if (!response.isSuccessful) {
                Log.e("User", "USER FAILED")
            } else {
                val data = response.body()!!
                Log.d("User", data.name)

                withContext(Dispatchers.Main) {
                    loader.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun createPost() {
        val api = Retrofit.Builder()
            .baseUrl(ApiRequests.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch ( Dispatchers.IO ) {
            val post = PostJson("Body", 1, "Title", 5)

            val response = api.createPost(post).awaitResponse()

            if(!response.isSuccessful) {
                Log.e("Post creation", "FAILED TO CREATE A POST")
            } else {
                Log.d("Post creation", response.body().toString())
            }
        }
    }
}