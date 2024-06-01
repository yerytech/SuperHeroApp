package com.yerytech.superhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats:PowerStatsResponse,
    @SerializedName("image") val image: SuperHeroImageDetail,
    @SerializedName("biography") val biography: SuperHeroBiographyDetail,

)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,

)

data class SuperHeroImageDetail(@SerializedName("url") val url:String)
data class SuperHeroBiographyDetail(
    @SerializedName("full-name") val fullName:String,
    @SerializedName("alter-egos") val alterEgos:String,
    @SerializedName("aliases") val aliases:List<String>,

)
