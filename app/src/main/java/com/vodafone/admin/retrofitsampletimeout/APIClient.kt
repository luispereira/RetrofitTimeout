package com.vodafone.admin.retrofitsampletimeout

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * @author lpereira on 20/07/2017.
 */

interface APIClient{

    @GET("articlesearch.json")
    fun request(@Query("api-key") apiKey : String) : Observable<Doc>
}
