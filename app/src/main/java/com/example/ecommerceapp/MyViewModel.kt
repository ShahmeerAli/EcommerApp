package com.example.ecommerceapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.MutableData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MyViewModel( val repo: RepositoryClass?=null,val repositoryRoom: RepositoryRoom?=null): ViewModel() {
    private val _dataHome= MutableLiveData<List<DataHome>>()
    val data: LiveData<List<DataHome>> get() = _dataHome
    val dataMen= MutableLiveData<List<DataHome>>()





        fun getDataHome(category: String){
            Log.d("View Model Check","Checkingg111")
            repo?.FetchDataHome (category){
                    item->
                _dataHome.postValue(item)

            }
        }

        fun getMenData(category: String){
           repo?.getMenData(category) {

               item->
               dataMen.postValue(item)
           }
        }



    fun CartItems(): LiveData<MutableList<EcommTable>>? {
      return repositoryRoom?.CartItems()
    }

    fun insert(ecommDao: EcommTable){
        viewModelScope.launch {
            repositoryRoom?.insert(ecommDao)
        }
    }

    fun TotalPrice(): LiveData<Int>? {
        return repositoryRoom?.Totalprice()
    }


    fun AddFavouriteItems(ecommDao: EcommTable){
        viewModelScope.launch { {
            repositoryRoom?.AddFavouriteItems(ecommDao)
        } }
    }

    fun GetFavItems(ecommDao: EcommTable){
        viewModelScope.launch {
            repositoryRoom?.getFavouriteItems(ecommDao)
        }
    }












}