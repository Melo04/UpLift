package com.example.kitahack.adapter

import java.io.Serializable

class PopularJobsAdapterClass(
    var title: String,
    var pic: String,
    var description: String,
    var salary: String,
    var time: String,
    var area: String,
    var location: String,
    var requirement: String,
) : Serializable {
}