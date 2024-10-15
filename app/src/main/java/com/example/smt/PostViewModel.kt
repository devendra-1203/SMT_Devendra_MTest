package com.example.smt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    private val list =  MutableLiveData<List<PostData>>()
    val post: LiveData<List<PostData>> = list

    private  var currentSkip= 0
    private  val limit = 20

    init {
        fetchPosts()
    }

    private fun fetchPosts() {

        viewModelScope.launch {
            try {
                val response = repository.getPosts(currentSkip, limit)
                list.value = list.value.orEmpty() + response.posts
                currentSkip += limit
            } catch (e: Exception){

            }

        }
    }

    fun loadMorePost(){
        fetchPosts()
    }

}