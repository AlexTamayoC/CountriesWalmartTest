package com.example.countriesapp.network

import com.example.countriesapp.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryAPI {
    @GET("countries.json")
    suspend fun getCountries(): Response<List<Country>>
}
