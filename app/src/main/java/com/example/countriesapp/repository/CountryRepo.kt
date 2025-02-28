package com.example.countriesapp.repository

import com.example.countriesapp.model.Country
import com.example.countriesapp.model.CountryResponse
import com.example.countriesapp.network.CountryAPI
import com.example.countriesapp.repository.IoDispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryRepo @Inject constructor(
    private val api: CountryAPI,
    @IoDispatcher private val networkDispatcher: CoroutineDispatcher
) {
    suspend fun fetchCountries(): List<Country> {
        return withContext(networkDispatcher) {
            val response = api.getCountries()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Empty response")
            } else {
                throw Exception("Error fetching countries: ${response.code()}")
            }
        }
    }
}
