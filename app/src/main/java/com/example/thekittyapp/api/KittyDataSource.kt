package com.example.thekittyapp.api
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class KittyDataSource(retrofit:Retrofit) {
    private val api:KittyApi = retrofit.create(KittyApi::class.java)


    fun getCats(limit: Int, breed_ids: String? ) =
        api.getCats(limit,  breed_ids)

    interface KittyApi{
        //images/search?breed_ids=beng
        @GET("https://api.thecatapi.com/v1/breeds?=a")
        fun getCats(@Query("limit") limit:Int,
                    @Query("breed_ids")breed_ids:String?): Single<List<BreedResponse>>
    }
}