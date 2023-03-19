package com.example.kitahack.adapter

import java.io.Serializable

class PopularFoodAdapterClass : Serializable {
    var title: String
    var pic: String
    var description: String
    var fee: Double
    var numberInCart = 0

    constructor(title: String, pic: String, description: String, fee: Double) {
        this.title = title
        this.pic = pic
        this.description = description
        this.fee = fee
    }

    constructor(title: String, pic: String, description: String, fee: Double, numberInCart: Int) {
        this.title = title
        this.pic = pic
        this.description = description
        this.fee = fee
        this.numberInCart = numberInCart
    }
}