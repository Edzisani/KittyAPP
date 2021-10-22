package com.example.thekittyapp.api
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class KittyDataSource(retrofit:Retrofit) {
    private val api:KittyApi = retrofit.create(KittyApi::class.java)


    fun getCats(limit: Int, category_ids: Int?, breed_ids: String? ) =
        api.getCats(limit, category_ids, breed_ids)
   //category_ids?.let { api.getCats(limit, it) }


    interface KittyApi{
        //"images/search"
        @GET("images/search?breed_ids=beng")
        fun getCats(@Query("limit") limit:Int,
                    @Query("category_ids") category_ids:Int?,
                    @Query("breed_ids")breed_ids:String?): Single<List<BreedResponse>>

    }
}