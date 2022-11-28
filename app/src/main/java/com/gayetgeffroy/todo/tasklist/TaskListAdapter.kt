package com.gayetgeffroy.todo.tasklist

import android.app.ActivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gayetgeffroy.todo.R


// l'IDE va râler ici car on a pas encore implémenté les méthodes nécessaires
class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    var currentList: List<Task> = emptyList()

    // on utilise `inner` ici afin d'avoir accès aux propriétés de l'adapter directement
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleView = itemView.findViewById<TextView>(R.id.task_title)
        private val descriptionView = itemView.findViewById<TextView>(R.id.task_description)


        fun bind(taskTitle: String, taskDescription: String) {
            // on affichera les données ici
            titleView.text = taskTitle
            descriptionView.text = taskDescription

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            holder.bind(currentList[position].title, currentList[position].description)
    }

    override fun getItemCount(): Int {
    return currentList.size
    }
}