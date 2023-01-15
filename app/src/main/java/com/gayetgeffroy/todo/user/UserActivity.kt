package com.gayetgeffroy.todo.user

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.gayetgeffroy.todo.user.ui.theme.TodoGayetGeffroyTheme
import okhttp3.MultipartBody


class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { result ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (result != null) {
//                uri = result
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
        setContent {
            var bitmap: Bitmap? by remember { mutableStateOf(null) }
            var uri: Uri? by remember { mutableStateOf(null) }
            val takePicture = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                bitmap = it
            }

            // Registers a photo picker activity launcher in single-select mode.
            TodoGayetGeffroyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        AsyncImage(
                            modifier = Modifier.fillMaxHeight(.2f),
                            model = bitmap ?: uri,
                            contentDescription = null
                        )
                        Button(
                            onClick = {
                                takePicture.launch()
                            },
                            content = { Text("Take picture") }
                        )
                        Button(
                            onClick = {// Launch the photo picker and allow the user to choose only images.
                               pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                      },
                            content = { Text("Pick photo") }
                        )
                    }
                }
            }
        }
    }
}
