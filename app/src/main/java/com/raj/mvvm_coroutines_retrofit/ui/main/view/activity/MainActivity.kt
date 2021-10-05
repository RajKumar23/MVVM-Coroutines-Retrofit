package com.raj.mvvm_coroutines_retrofit.ui.main.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raj.mvvm_coroutines_retrofit.data.api.ApiHelper
import com.raj.mvvm_coroutines_retrofit.data.api.ApiServiceImpl
import com.raj.mvvm_coroutines_retrofit.data.model.User
import com.raj.mvvm_coroutines_retrofit.databinding.ActivityMainBinding
import com.raj.mvvm_coroutines_retrofit.ui.base.ViewModelFactory
import com.raj.mvvm_coroutines_retrofit.ui.main.adapter.MainAdapter
import com.raj.mvvm_coroutines_retrofit.ui.main.viewmodel.MainViewModel
import com.raj.mvvm_coroutines_retrofit.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.requestUser()
        mainViewModel.getUsersValues().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.apply {
            addData(users)
            notifyDataSetChanged()
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
    }
}