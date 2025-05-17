package com.example.ecommerceapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.nio.charset.CodingErrorAction.IGNORE

@Dao
interface EcommDao {
   @Query("Select * from EcommTable")
   fun getCartItems(): LiveData<MutableList<EcommTable>>

   @Query("Select SUM(price) from EcommTable")
   fun  getCartSum(): LiveData<Int>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun addtoCart(ecomm: EcommTable)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun addtoFavourites(ecomm: EcommTable)

   @Query("Select * from EcommTable where isFavourite =1")
   fun getFavouriteItems(ecomm: EcommTable)

}