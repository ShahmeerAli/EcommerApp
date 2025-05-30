package com.example.ecommerceapp

import androidx.lifecycle.LiveData

class RepositoryRoom(private val rdb: DBHelperClass)  {

    val CartItems: LiveData<MutableList<EcommTable>>?=rdb.ecommDao().getCartItems()

    fun Totalprice()=rdb.ecommDao().getCartSum()

    suspend fun insert(ecommDao: EcommTable)=rdb.ecommDao().addtoCart(ecommDao)

    fun AddFavouriteItems(ecommDao: EcommTable)=rdb.ecommDao().addtoFavourites(ecommDao)

    fun getFavouriteItems()=rdb.ecommDao().getFavouriteItems()

    suspend fun getQuantity(quantity:Int,id:Int)=rdb.ecommDao().updateQuantity(quantity,id)

    suspend fun deleteCart(id:Int)=rdb.ecommDao().deleteCart(id)

}