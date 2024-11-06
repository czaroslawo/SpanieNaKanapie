package com.example.shared.data.repositories


import com.example.shared.data.models.City
import com.example.shared.data.models.Post
import com.example.shared.data.services.PostService
import com.example.shared.data.storage.SharedSettingsHelper
import com.example.shared.data.utils.Resource
import io.ktor.utils.io.errors.EOFException

class PostRepositoryImpl(
    private val postService: PostService,
) : PostRepository{
    override suspend fun createPost(post: Post) {
        return try{
            postService.createPost(post)
        }catch (e: EOFException){
            
        }


    }

    override suspend fun getPosts(): Resource<List<Post>> {
        return postService.getPosts()
    }

    override suspend fun getCity(): Resource<List<City>> {
        return postService.getCity()
    }

}