package com.dicoding.asclepius.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.adapter.ArticleAdapter
import com.dicoding.asclepius.databinding.ActivityArticleBinding
import com.dicoding.asclepius.data.Result

class ArticleActivity : AppCompatActivity() {


    private lateinit var binding: ActivityArticleBinding
    private lateinit var viewModel: ArticleVM
    private val adapter by lazy { ArticleAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        observerListUser()
    }

    private fun setupViewModel() {
        val factory: ArticleVMFactory = ArticleVMFactory.getInstance()

        viewModel = ViewModelProvider(
            this,
            factory
        )[ArticleVM::class.java]
    }

    private fun setupRecyclerView() {
        binding.rvArticle.setHasFixedSize(true)
        binding.rvArticle.layoutManager = LinearLayoutManager(this)
        binding.rvArticle.adapter = adapter
    }

    private fun observerListUser() {
        viewModel.listUser.observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    adapter.submitList(it.data)
                }

                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this@ArticleActivity,"Terjadi Kesalahan, try again perhaps?", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}