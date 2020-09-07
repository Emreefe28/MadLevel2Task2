package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.question.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter
        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.CORRECT[i]))
        }
        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        questionAdapter.notifyDataSetChanged()
        createLeftTouch().attachToRecyclerView(rvQuestions)
        createRightTouch().attachToRecyclerView(rvQuestions)
    }


    private fun createLeftTouch(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (!questions[position].correct) {
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                    Snackbar.make(textView, "Good answer", Snackbar.LENGTH_SHORT).show()
                } else {
                    questionAdapter.notifyDataSetChanged()
                    Snackbar.make(textView, "Wrong answer", Snackbar.LENGTH_SHORT).show()
                    return
                }
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun createRightTouch(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (questions[position].correct) {
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                    Snackbar.make(textView, "Good answer", Snackbar.LENGTH_SHORT).show()
                } else {
                    questionAdapter.notifyDataSetChanged()
                    Snackbar.make(textView, "Wrong answer", Snackbar.LENGTH_SHORT).show()
                    return
                }
            }
        }
        return ItemTouchHelper(callback)
    }


}