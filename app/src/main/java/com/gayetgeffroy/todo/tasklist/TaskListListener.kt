package com.gayetgeffroy.todo.tasklist

interface TaskListListener {
    fun onClickDelete(task:Task)
    fun onClickEdit(task:Task)
}
