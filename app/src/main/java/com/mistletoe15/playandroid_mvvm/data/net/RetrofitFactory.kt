package com.mistletoe15.playandroid_mvvm.data.net

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Mistletoe on 2020/5/29
 **/
class RetrofitFactory private constructor() {
    companion object {
        val instance: RetrofitFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitFactory()
        }
    }
    private val retrofit: Retrofit
    init {
        val gson = Gson().newBuilder()
            .setLenient()
            .serializeNulls()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.PLAY_ANDROID_BASE_URL)
            .client(initOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }
    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(initLogInterceptor())
            .build()
    }
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i("Retrofit", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }
}