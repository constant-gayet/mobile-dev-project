package com.gayetgeffroy.todo.tasklist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gayetgeffroy.todo.R
import com.gayetgeffroy.todo.databinding.FragmentTaskListBinding
import com.gayetgeffroy.todo.databinding.ItemTaskBinding
import kotlin.math.log

// l'IDE va râler ici car on a pas encore implémenté les méthodes nécessaires
class TaskListAdapter(val listener: TaskListListener) : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskDiffCallback) {

    // on utilise `inner` ici afin d'avoir accès aux propriétés de l'adapter directement
    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                //Deal with click feature later, show the list first
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val binding = holder.binding
        getItem(position).let {
            binding.taskTitle.text = it.title
            binding.taskDescription.text = it.description

            binding.deleteButton.setOnClickListener(View.OnClickListener {
                listener.onClickDelete(getItem(position))
            })
            binding.editButton.setOnClickListener(View.OnClickListener {
                listener.onClickEdit(getItem(position))
            })
        }
    }
}

object TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) : Boolean {
        return oldItem.id == newItem.id // comparaison: est-ce la même "entité" ? => même id?
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task) : Boolean {
        return oldItem == newItem // comparaison: est-ce le même "contenu" ? => mêmes valeurs? (avec data class: simple égalité)
    }
}