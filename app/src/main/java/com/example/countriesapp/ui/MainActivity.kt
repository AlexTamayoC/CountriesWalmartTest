package com.example.countriesapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.ui.states.AppStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CountryAdapter
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel.fetchCountries()
        observeDataChanges()
    }

    private fun setupRecyclerView() {
        adapter = CountryAdapter()
        binding.countriesView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainActivity.adapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.fetchCountries()
                    }
                }
            })
        }
    }

    private fun observeDataChanges() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is AppStates.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
                }
                AppStates.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                AppStates.None -> {}
                is AppStates.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.loadUsers(state.countries)
                }
            }
        }
    }
}