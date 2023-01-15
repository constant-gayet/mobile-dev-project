package com.gayetgeffroy.todo.tasklist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gayetgeffroy.todo.databinding.FragmentTaskListBinding
import com.gayetgeffroy.todo.detail.DetailActivity
import kotlinx.coroutines.launch

class TaskListFragment : Fragment() {

    private val viewModel: TaskListViewModel by viewModels()

    val adapterListener: TaskListListener = object : TaskListListener {
        override fun onClickDelete(task: Task) {
            viewModel.remove(task)
        }

        override fun onClickEdit(task: Task) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("task", task)
            editTask.launch(intent)
        }

        override fun onLongClickShare(task: Task) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${task.title}\n${task.description}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }

    val adapter = TaskListAdapter(adapterListener)

    private var _binding: FragmentTaskListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    //    Creating the launcher for task creation
    val createTask =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // dans cette callback on récupèrera la task et on l'ajoutera à la liste
            val task = result.data?.getSerializableExtra("task") as Task?
            if (task != null) {
                viewModel.add(task)
            }
        }

    //    Creating the launcher for task edition
    val editTask =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // dans cette callback on récupèrera la task et on l'ajoutera à la liste
            val task = result.data?.getSerializableExtra("task") as Task?
            if (task != null) {
                viewModel.edit(task)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //if (savedInstanceState != null) {
        //    this.taskList = savedInstanceState.getSerializable("taskList") as List<Task>
        //    viewModel.edit(task)
        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root;
    }


    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mainRecyclerView.adapter = adapter
        binding.addTaskBtn.setOnClickListener(View.OnClickListener {
            // Instanciation d'un objet task avec des données préremplies:
            val intent = Intent(context, DetailActivity::class.java)
            createTask.launch(intent)

        })
        lifecycleScope.launch { // on lance une coroutine car `collect` est `suspend`
            viewModel.tasksStateFlow.collect {
                adapter.submitList(it)
            }
        }
    }

}