package com.gayetgeffroy.todo.tasklist

interface TaskListListener {
    fun onLongClickShare(task: Task)
    fun onClickDelete(task: Task)
    fun onClickEdit(task: Task)
}
