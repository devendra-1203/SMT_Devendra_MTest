package com.example.smt

import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi
{
    @GET("posts")
    suspend fun getPosts(@Query("skip") skip:Int,@Query("limit") limit: Int ): PostResposeData
}