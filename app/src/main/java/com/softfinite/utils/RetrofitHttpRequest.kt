package com.softfinite.utils

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by Softfinite (Viv'Ek Chodvadiya) on 13-Jun-18.
 */

internal object RetrofitHttpRequest : OkHttpClient() {

    @Throws(IOException::class)
    fun newRequestRetrofit(context: Context): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    //                        okhttp3.Response response = chain.proceed(original);

                    //                        if (!response.isSuccessful()) {
                    //
                    //                            Debug.e("Failed","Failed");
                    //
                    //                            return response;
                    //                        }

                    val request = original.newBuilder()
                            .header(RequestParamsUtils.ACCESS_TOKEN, Utils.getUserToken(context)!!)
                            .header(RequestParamsUtils.CONTENT_TYPE, Constant.APP_JSON)
                            .method(original.method(), original.body())
                            .build()
                    chain.proceed(request)
                }
                .build()
        val builder = Retrofit.Builder()
                .baseUrl(RetrofitUrls.MAIN_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Throws(IOException::class)
    fun newRequestRetrofit(context: Context, url: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    //                        okhttp3.Response response = chain.proceed(original);

                    //                        if (!response.isSuccessful()) {
                    //
                    //                            Debug.e("Failed","Failed");
                    //
                    //                            return response;
                    //                        }

                    val request = original.newBuilder()
                            .header(RequestParamsUtils.ACCESS_TOKEN, Utils.getUserToken(context)!!)
                            .header(RequestParamsUtils.CONTENT_TYPE, Constant.APP_JSON)
                            .method(original.method(), original.body())
                            .build()
                    chain.proceed(request)
                }
                .build()

        val builder = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())

        return builder.build()
    }

    @Throws(IOException::class)
    fun newRequestRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
                .baseUrl(RetrofitUrls.MAIN_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Throws(IOException::class)
    fun newRequestOkHttpClient(context: Context): OkHttpClient {
        return Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    //                        okhttp3.Response response = chain.proceed(original);

                    //                        if (!response.isSuccessful()) {
                    //
                    //                            Debug.e("Failed","Failed");
                    //
                    //                            return response;
                    //                        }

                    val request = original.newBuilder()
                            .header(RequestParamsUtils.ACCESS_TOKEN, Utils.getUserToken(context)!!)
                            .header(RequestParamsUtils.CONTENT_TYPE, Constant.APP_JSON)
                            .method(original.method(), original.body())
                            .build()
                    chain.proceed(request)
                }
                .build()
    }
}



