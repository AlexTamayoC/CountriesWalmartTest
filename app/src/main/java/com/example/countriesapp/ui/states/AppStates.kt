package com.example.countriesapp.ui.states

import com.example.countriesapp.model.Country

sealed class AppStates {
    object None : AppStates()
    object Loading : AppStates()
    data class Success(val countries: List<Country>) : AppStates()
    data class Error(val message: Int) : AppStates()
}