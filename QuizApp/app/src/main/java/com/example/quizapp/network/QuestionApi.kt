package com.example.quizapp.network

import com.example.quizapp.model.Question
import retrofit2.http.GET

interface QuestionApi {
    @GET("itmmckernan/triviaJSON/master/world.json")
    suspend fun getAllQuestion(): Question
}