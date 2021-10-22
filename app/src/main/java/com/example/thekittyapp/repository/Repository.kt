package com.example.thekittyapp.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

//import retrofit2.converter.moshi.MoshiConverterFactory

abstract class Repository(
    baseUrl: String,
    isDebugEnabled: Boolean,
    apiKey: String
) {
    private val apiKeyHeader: String = "a60e81d3-735f-4bdd-8adb-cd618f0ce4cb"
    val retrofit: Retrofit
    val moshi: Moshi

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (isDebugEnabled) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        //Adding the api key as a header
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(apiKey, apiKey)
                .build()
            chain.proceed(request)
        }.addInterceptor(loggingInterceptor)
            .build()


        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}