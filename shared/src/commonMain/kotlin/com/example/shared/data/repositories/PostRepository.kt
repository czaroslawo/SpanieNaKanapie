package com.example.shared.data.repositories

import com.example.shared.data.models.City
import com.example.shared.data.models.Post
import com.example.shared.data.utils.Resource

interface PostRepository {
    suspend fun createPost(post: Post)
    suspend fun getPosts(city: String): Resource<List<Post>>
    suspend fun getCity(): Resource<List<City>> //maybe i will need add a ktorfit repsonseConverter
}