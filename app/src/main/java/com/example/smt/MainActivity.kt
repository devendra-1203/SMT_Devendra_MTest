package com.example.smt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    private  lateinit var  viewModel: PostViewModel
    private lateinit var  adapter: PostDataAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(PostApi::class.java)
        val repository = PostRepository(api)
        viewModel = PostViewModel(repository)

        adapter = PostDataAdapter(emptyList())
        val recyclerView : RecyclerView = findViewById(R.id.recyclerview)
      recyclerView.layoutManager = layoutManager
      recyclerView.adapter = adapter

        viewModel.post.observe(this, Observer { posts ->
        adapter.addPosts(posts)


   })

        recyclerView.addOnScrollListener(object  : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.canScrollVertically(1)){
                    viewModel.loadMorePost()
                }
            }
        })
    }


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val view = inflater.inflate(R.layout.main_activity, container, false)
//        adapter = PostDataAdapter(emptyList())
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
//
//        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
//        viewModel.post.observe(viewLifecycleOwner){ posts ->
//            adapter.addPosts(posts)
//        }
//
//
//
//        recyclerView.addOnScrollListener(object  : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (recyclerView.canScrollVertically(1)){
//                    viewModel.loadMorePost()
//                }
//            }
//        })
//        return view
//    }

}