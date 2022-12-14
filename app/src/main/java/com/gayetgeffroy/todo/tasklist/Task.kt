package com.gayetgeffroy.todo.tasklist

data class Task (
    val id : String,
    val title : String,
    val description : String = "Description by default",
) : java.io.Serializable { }