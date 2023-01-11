package com.gayetgeffroy.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gayetgeffroy.todo.databinding.ActivityMainBinding
import data.Api
import kotlinx.coroutines.runBlocking


// TODO: TP2.9 PARTAGER
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                val receivedText = intent.getStringExtra(Intent.EXTRA_TEXT)
                // Use the received text to pre-fill the form for creating a new task
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                val receivedText = intent.getStringExtra(Intent.EXTRA_TEXT)
                // Use the received text to pre-fill the form for creating a new task
            }
        }
    }

    override fun onResume() {
        super.onResume()
        runBlocking {
            try {
                val user = Api.userWebService.fetchUser().body()!!
                binding.userTextView.text = user.name
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}
