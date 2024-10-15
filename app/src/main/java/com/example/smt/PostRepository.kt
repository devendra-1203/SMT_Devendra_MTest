package com.example.smt

class PostRepository(private  val api: PostApi) {

    suspend fun getPosts(skip: Int, limit: Int) : PostResposeData {
        return api.getPosts(skip, limit)
    }


}