package com.example.shared.data.services


import com.example.shared.data.models.City
import com.example.shared.data.models.Post
import com.example.shared.data.models.UserToken
import com.example.shared.data.utils.Resource
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST

interface PostService {
    @POST("post")
    suspend fun createPost(@Body post: Post)

    @GET("post")
    suspend fun getPosts(): Resource<List<Post>>

    @GET("city")
    suspend fun getCity(): Resource<List<City>>
}