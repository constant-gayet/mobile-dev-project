package com.gayetgeffroy.todo.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gayetgeffroy.todo.R
import com.gayetgeffroy.todo.detail.ui.theme.TodoGayetGeffroyTheme
import com.gayetgeffroy.todo.tasklist.Task
import java.util.*

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receivedText = intent.getStringExtra("shared_text")
        val initialTask = intent.getSerializableExtra("task") as Task?
        setContent {
            TodoGayetGeffroyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Detail(initialTask ?: receivedText?.let {
                        Task(
                            UUID.randomUUID().toString(),
                            it
                        )
                    }, onValidate = {
                        intent.putExtra("task", it)
                        setResult(RESULT_OK, intent)
                        finish()
                    })
                }
            }

        }
    }
}


@Composable
fun Detail(initialTask: Task?, onValidate: (task: Task) -> Unit) {

    Column(modifier = Modifier.padding(16.dp), Arrangement.spacedBy(16.dp)) {
        val newTask = Task(UUID.randomUUID().toString(), "")
        var task by remember {
            mutableStateOf(
                initialTask ?: newTask
            )
        } // faire les imports suggérés par l'IDE
        OutlinedTextField(value = task.title, onValueChange = {
            task = task.copy(title = it)

        }, label = { Text(text = "Enter a title") })

        OutlinedTextField(value = task.description, onValueChange = {
            task = task.copy(description = it)
        }, label = { Text(text = "Enter a description") })

        Button(onClick = {
            onValidate(task)
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_done_24),
                contentDescription = "Done"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    TodoGayetGeffroyTheme {
    }
}