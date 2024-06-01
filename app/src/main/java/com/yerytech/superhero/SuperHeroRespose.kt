package com.yerytech.superhero

import com.google.gson.annotations.SerializedName

data class SuperHeroResponse( @SerializedName("response") val response:String,
                              @SerializedName("results") val superHero:List<SuperHeroItemsResponse>)


data class SuperHeroItemsResponse(
    @SerializedName("id") val superHeroId:String,
    @SerializedName("name") val superHeroName:String,
    @SerializedName("image") val superHeroImage:SuperHeroImageResponse,

    )

data class SuperHeroImageResponse(@SerializedName("url")val url:String)