package com.example.kitahack.model

import java.io.Serializable

class RestaurantModel : Serializable {
    var title: String
    var address: String
    var pic: String
    var description: String
    var category: String
    var fee: Double
    var numberInCart = 0

    constructor(title: String, address: String, pic: String, description: String, category: String, fee: Double) {
        this.title = title
        this.address = address
        this.pic = pic
        this.category = category
        this.description = description
        this.fee = fee
    }

    constructor(title: String, address: String, pic: String, description: String, category: String, fee: Double, numberInCart: Int) {
        this.title = title
        this.address = address
        this.pic = pic
        this.description = description
        this.category = category
        this.fee = fee
        this.numberInCart = numberInCart
    }
}