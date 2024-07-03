package com.dicoding.asclepius.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.ArticlesItem
import com.dicoding.asclepius.data.ArticleRepository
import com.dicoding.asclepius.data.Result

class ArticleVM(articleRepository: ArticleRepository): ViewModel(){

    val listUser: LiveData<Result<List<ArticlesItem>>> = articleRepository.getListUser()
}