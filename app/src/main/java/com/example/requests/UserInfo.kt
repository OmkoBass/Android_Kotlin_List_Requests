package com.example.requests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val username = intent.getStringExtra("username")
        val website = intent.getStringExtra("website")
        val email = intent.getStringExtra("email")

        userInfoUsername.text = username
        userInfoWebsite.text = website
        userInfoEmail.text = email
    }
}