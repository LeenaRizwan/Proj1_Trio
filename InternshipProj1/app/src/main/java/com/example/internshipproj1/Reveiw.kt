package com.example.internshipproj1

data class Reveiw(
    var Stars: Int,
    var Rev: String,
    var User:String,
    var Item:String
){
    constructor() : this(1,"A", "B", "C") {

    }
}