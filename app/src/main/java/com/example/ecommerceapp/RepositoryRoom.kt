package com.example.ecommerceapp

class RepositoryRoom(private val rdb: DBHelperClass)  {

    fun CartItems()=rdb.EcommmDao().getCartItems()

    fun Totalprice()=rdb.EcommmDao().getCartSum()

    fun insert(ecommDao: EcommTable)=rdb.EcommmDao().addtoCart(ecommDao)
}