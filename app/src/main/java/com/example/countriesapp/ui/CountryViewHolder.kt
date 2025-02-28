package com.example.countriesapp.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.databinding.CountryItemBinding
import com.example.countriesapp.model.Country

class CountryViewHolder(
    private val item: CountryItemBinding
) : ViewHolder(item.root) {

    fun bindToView(country: Country) {
        item.countryName.text = country.name+",   "
        item.countryCode.text = country.code
        item.countryRegion.text = country.region
        item.countryCapital.text = country.capital
    }}