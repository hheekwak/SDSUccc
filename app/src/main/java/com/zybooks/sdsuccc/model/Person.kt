package com.zybooks.sdsuccc.model

data class Person(
    var id: Long = 0,
    var name: String = "",
    var dob: String = "",
    var allergy: String = "",
    var cclassId: Long = 0) {
}