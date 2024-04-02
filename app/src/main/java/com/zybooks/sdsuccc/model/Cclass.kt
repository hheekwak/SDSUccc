package com.zybooks.sdsuccc.model

data class Cclass(
    var id: Long = 0,
    var text: String,
    var updateTime: Long = System.currentTimeMillis()) {
}

