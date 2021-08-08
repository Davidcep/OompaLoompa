package com.example.ompaaloompa.data.remote

import com.example.ompaaloompa.data.models.UnsplashResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Connect to the API to get Oompa's information
 */
interface OompaService {

    @GET("oompa-loompas/")
    suspend fun getOompas(
        @Query("page") query: Int
    ): Response<UnsplashResponse>

    companion object {
        private const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"

        fun create(): OompaService {
            val client = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OompaService::class.java)
        }
    }

}