package com.vodafone.admin.retrofitsampletimeout

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.functions.Func0
import java.util.concurrent.TimeUnit

/**
 * @author lpereira on 20/07/2017.
 */

class NetworkFactory {
    private val retrofit: Retrofit
    private val service: APIClient

    constructor(){
        retrofit = getObservableClient()
        service = retrofit.create<APIClient>(APIClient::class.java)
    }
    //New York Times api key
    val API_KEY = "524f482851314080a2fb06854de12e6e"
    val URL = "https://api.nytimes.com/svc/search/v2/"
    fun request(): Observable<Doc> {
       return Observable.defer(Func0 {
            return@Func0 service.request(API_KEY)
        })
    }


    fun getObservableClient(): Retrofit {
        val builder = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)

        val okHttpClient = builder.build()

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
}
