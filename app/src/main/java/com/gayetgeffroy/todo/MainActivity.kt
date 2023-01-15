package com.gayetgeffroy.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import coil.load
import com.gayetgeffroy.todo.databinding.ActivityMainBinding
import com.gayetgeffroy.todo.user.UserActivity
import data.Api
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userImageView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)

        })

        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                val receivedText = intent.getStringExtra(Intent.EXTRA_TEXT)
                // send this data to tasklist fragment
            }
        }
    }

    override fun onResume() {
        super.onResume()
        runBlocking {
            try {
                val user = Api.userWebService.fetchUser().body()!!
                binding.userTextView.text = user.name
                var imageView = findViewById<ImageView>(R.id.user_image_view)
                imageView.load("https://goo.gl/gEgYUd")

            } catch (e: Exception) {
            }
        }
    }
}
