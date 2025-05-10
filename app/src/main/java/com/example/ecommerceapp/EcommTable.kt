package com.example.ecommerceapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EcommTable")

data class EcommTable(
    var categoyr:String,
    var image:String,
    var price:Int,
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

) {


}