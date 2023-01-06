package com.gayetgeffroy.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}
