package com.softfinite.utils

import com.google.gson.JsonObject

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 5/8/2018.
 */

interface RetrofitUrls {

    companion object {
        var MAIN_URL = "https://api.themoviedb.org/3/"
    }

    @GET("demos/view-flipper/heroes.php")
    fun homepage(): Call<ResponseBody>

//    @GET("/3/movie/top_rated?api_key=db3749e7bfc2840b77c347438bc2cc6d")
//    fun getMovieData(): Call<ResponseBody>

    @GET("movie/top_rated")
    fun getMovieData(@Query("api_key") apiKey: String): Call<ResponseBody>


    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<ResponseBody>

    @POST("restaurant/getAllRestaurantList")
    abstract fun getAllRestaurantList(@Body jsonObject: JsonObject): Call<ResponseBody>

}
