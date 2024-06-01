package com.yerytech.superhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET ("/api/c835087d963d37bf8b851651f4e7a295/search/{name}")
    suspend fun getSuperheroes(@Path("name")superHeroName:String):Response<SuperHeroResponse>
    @GET("/api/c835087d963d37bf8b851651f4e7a295/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId:String):Response<SuperHeroDetailResponse>

}