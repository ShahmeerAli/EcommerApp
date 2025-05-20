package com.example.ecommerceapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao

interface EcommDao {
   @Query("Select * from EcommTable")
   fun getCartItems(): LiveData<MutableList<EcommTable>>

   @Query("Select SUM(price) from EcommTable")
   fun  getCartSum(): LiveData<Int>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addtoCart(ecomm: EcommTable)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun addtoFavourites(ecomm: EcommTable)

   @Query("Select * from EcommTable where isFavourite =1")
   fun getFavouriteItems(): LiveData<MutableList<EcommTable>>

   @Query("Update EcommTable SET quantity =:quantity WHERE id =:id")
  suspend fun updateQuantity(quantity:Int, id:Int)
  @Query("Delete from EcommTable where id=:id")
  suspend fun deleteCart(id:Int)



}