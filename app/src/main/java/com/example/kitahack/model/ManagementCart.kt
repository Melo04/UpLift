package com.example.kitahack.model

import android.content.Context
import com.example.kitahack.adapter.PopularFoodAdapterClass
import android.widget.Toast
import com.example.kitahack.Interface.ChangeNumberItemsListener
import java.util.ArrayList

class ManagementCart(private val context: Context) {
    private val foodDB: foodDB

    init {
        foodDB = foodDB(context)
    }

    fun insertFood(item: PopularFoodAdapterClass) {
        val listFood = listCart
        var existAlready = false
        var n = 0
        for (i in listFood.indices) {
            if (listFood[i]!!.title == item.title) {
                existAlready = true
                n = i
                break
            }
        }
        if (existAlready) {
            listFood[n]!!.numberInCart = item.numberInCart
        } else {
            listFood.add(item)
        }
        foodDB.putListObject("CartList", listFood)
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show()
    }

    val listCart: ArrayList<PopularFoodAdapterClass>
        get() = foodDB.getListObject("CartList")

    fun plusNumberFood(listFood: ArrayList<PopularFoodAdapterClass>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        listFood[position].numberInCart++
        foodDB.putListObject("CartList", listFood)
        changeNumberItemsListener.changed()
    }

    fun minusNumberFood(listfood: ArrayList<PopularFoodAdapterClass>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        if (listfood[position].numberInCart == 1) {
            listfood.removeAt(position)
        } else {
            listfood[position].numberInCart--
        }
        foodDB.putListObject("CartList", listfood)
        changeNumberItemsListener.changed()
    }

    fun getTotalFee(): Double {
        val listFood = listCart
        var fee = 0.0
        for (i in 0 until listFood.size) {
            fee += listFood[i].fee * listFood[i].numberInCart
        }
        return fee
    }

}