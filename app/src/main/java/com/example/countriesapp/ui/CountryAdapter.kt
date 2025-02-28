package com.example.countriesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.databinding.CountryItemBinding
import com.example.countriesapp.model.Country

class CountryAdapter : RecyclerView.Adapter<CountryViewHolder>() {

    private val countries = mutableListOf<Country>()

    fun loadUsers(data: List<Country>) {
        countries.clear()
        countries.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindToView(countries[position])
    }
}