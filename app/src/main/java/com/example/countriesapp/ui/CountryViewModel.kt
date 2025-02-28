package com.example.countriesapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.R
import com.example.countriesapp.model.Country
import com.example.countriesapp.repository.CountryRepo
import com.example.countriesapp.ui.states.AppStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countriesRepo: CountryRepo
) : ViewModel() {

    private val _countriesState = MutableLiveData<AppStates>(AppStates.None)
    val state: LiveData<AppStates> = _countriesState
    private var allCountries = mutableListOf<Country>()

    fun fetchCountries() {
        _countriesState.value = AppStates.Loading
        viewModelScope.launch {
            try {

                val countries = countriesRepo.fetchCountries()
                allCountries = countries.toMutableList()
                _countriesState.value = AppStates.Success(allCountries)
            } catch (e: Throwable) {
                Log.e("Fetch", e.toString())
                _countriesState.value = AppStates.Error(R.string.error_message)
            }
        }
    }
}
