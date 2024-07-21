package com.example.internshipproj1

data class User(
    var Address: String,
    var DoB: String,
    var Email: String,
    var Name: String,
    var Password: String
) {
    constructor() : this("A", "B", "C", "D", "E") {

    }
}
