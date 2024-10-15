package com.example.smt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostDataAdapter(private var posts: List<PostData>) : RecyclerView.Adapter<PostDataAdapter.PostViewHolder>() {

    inner class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post : PostData){
            view.findViewById<TextView>(R.id.title).text = post.title
            view.findViewById<TextView>(R.id.body).text = post.body

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostDataAdapter.PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent,false)
        return PostViewHolder(view)

    }

    override fun onBindViewHolder(holder: PostDataAdapter.PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    fun  addPosts(newPosts: List<PostData>){
        posts = posts + newPosts
        notifyDataSetChanged()
    }
}