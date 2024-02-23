package com.skyblue.swipe_refreshlayout

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.skyblue.swipe_refreshlayout.databinding.ActivityMainBinding
import java.util.Collections
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var postAdapter: PostAdapter
    lateinit var postList: ArrayList<Post>
    var context: Context = this@MainActivity

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postList = ArrayList();

        postAdapter = PostAdapter(context, postList)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = postAdapter

        postList.add(Post("Android Development", R.drawable.android))
        postList.add(Post("C++ Development", R.drawable.cpp1))
        postList.add(Post("Java Development", R.drawable.java))
        postList.add(Post("Json Development", R.drawable.json))
        postList.add(Post("JavaScript Development", R.drawable.js))
        postAdapter.notifyDataSetChanged()

        binding.swipRefreshLayout.setOnRefreshListener {
            binding.swipRefreshLayout.isRefreshing = false
            Collections.shuffle(postList, Random(System.currentTimeMillis()))
            postAdapter.notifyDataSetChanged()
        }
    }
}