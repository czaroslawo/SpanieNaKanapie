package com.example.shared.data.repositories


import com.example.shared.data.models.City
import com.example.shared.data.models.Post
import com.example.shared.data.services.PostService
import com.example.shared.data.storage.SharedSettingsHelper
import com.example.shared.data.utils.Resource
import de.jensklingenberg.ktorfit.http.Query
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

    override suspend fun getPosts(city: String): Resource<List<Post>> {
        return try{
            val posts = postService.getPosts(city)
            Resource.Success(posts)
        }catch(e: Exception){
            Resource.Error("Failed to fetch posts")
        }
    }

    override suspend fun getCity(): Resource<List<City>> {
        return try {
            val response = postService.getCity()
            Resource.Success(response.data)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch cities")
        }
    }

}