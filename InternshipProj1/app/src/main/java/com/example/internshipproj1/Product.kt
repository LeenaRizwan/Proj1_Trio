package com.example.internshipproj1

data class Product(
    var Catagory1:String,
    var Catagory2:String,
    var Colour:String,
    var Desc:String,
    var Name:String,
    var Price:Int,
    var Sale_Percent:Int,
    var Sold:Int,
    var Stock:Int
) {
    constructor() : this("A", "B", "C", "D", "E", 0, 0, 0, 0) {

    }
}