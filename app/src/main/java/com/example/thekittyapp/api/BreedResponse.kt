package com.example.thekittyapp.api

import com.example.thekittyapp.models.Breed
import com.example.thekittyapp.models.BreedResponseItem
import com.example.thekittyapp.models.Image
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json



data class BreedResponse(
//    @SerializedName("id") val id:String,
//    @SerializedName( "url") val url:String,
//    @SerializedName("breeds") val breeds:List<BreedResponseItem>,
//    @SerializedName("categories") val categories:List<Any>,
    val breeds:ArrayList<BreedResponseItem>,
    val height:String,
    val id:String,
    @Json(name="url") val url:String,
// @SerializedName( "url") val url:String,
    val width:String,

    )

//){
//    override fun toString():String{
//        return "BreedResponse(id='$id', url='$url',breeds=$breeds,categories=$categories)"
//    }
//}
//val id:String,
//@Json(name="url") val url:String,
//// @SerializedName( "url") val url:String,
//val breeds:List<BreedResponseItem>,
//val categories:List<Any>,