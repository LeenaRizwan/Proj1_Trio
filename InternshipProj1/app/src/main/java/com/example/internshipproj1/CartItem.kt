package com.example.internshipproj1

data class CartItem(
    var Name:String,
    var Price:Int,
    var ID:Int
) {
    constructor() : this("A", 0, 0) {

    }
}