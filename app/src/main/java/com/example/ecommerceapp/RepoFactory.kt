package com.example.ecommerceapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepoFactory(val repo: RepositoryClass?=null,val repositoryRoom: RepositoryRoom?=null): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(repo, repositoryRoom) as T
    }
}