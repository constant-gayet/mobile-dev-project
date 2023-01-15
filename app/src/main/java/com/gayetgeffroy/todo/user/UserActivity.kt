package com.gayetgeffroy.todo.user

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.gayetgeffroy.todo.user.ui.theme.TodoGayetGeffroyTheme


class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bitmap: Bitmap? by remember { mutableStateOf(null) }
            val uri: Uri? by remember { mutableStateOf(null) }
            TodoGayetGeffroyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    User(bitmap, uri)
                }
            }
        }
    }
}

@Composable
fun User(bitmap: Bitmap?, uri: Uri?) {
    Column {
        AsyncImage(
            modifier = Modifier.fillMaxHeight(.2f),
            model = bitmap ?: uri,
            contentDescription = null
        )
        Button(
            onClick = {},
            content = { Text("Take picture") }
        )
        Button(
            onClick = {},
            content = { Text("Pick photo") }
        )
    }}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoGayetGeffroyTheme {

    }
}