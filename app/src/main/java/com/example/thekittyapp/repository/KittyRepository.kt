package com.example.thekittyapp.repository

import com.example.thekittyapp.api.BreedResponse
import com.example.thekittyapp.api.KittyDataSource
import io.reactivex.Single

class KittyRepository(
    baseUrl:String,
    isDebuEnabled: Boolean,
    apiKey:String
):Repository(baseUrl, isDebuEnabled, apiKey) {

    private val kittyDataSource:KittyDataSource = KittyDataSource(retrofit)
    //a class to wrap around the response

    inner class Result(val breedResponse:List<BreedResponse>? = null, val errorMessage:String?=null){


        fun hasKitty():Boolean{
            return breedResponse!=null && !breedResponse.isEmpty()
        }
        fun hasError():Boolean{
            return errorMessage !=null
        }
    }
    //Method to be called by the fragment/Activity

    fun getCats(limit:Int, category_ids:Int?, breed_ids:String?):Single<Result>{


        return kittyDataSource.getCats(limit,category_ids,breed_ids)
            .map{ breedResponse: List<BreedResponse> -> Result(breedResponse = breedResponse)}
            .onErrorReturn {t:Throwable -> Result(errorMessage =t.message)}
    }
}