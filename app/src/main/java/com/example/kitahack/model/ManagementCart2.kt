package com.example.kitahack.model

import android.content.Context
import android.widget.Toast
import com.example.kitahack.Interface.ChangeNumberItemsListener
import com.example.kitahack.adapter.PopularFoodAdapterClass2
import java.util.ArrayList

class ManagementCart2(private val context: Context) {
    private val foodDB2: foodDB2

    init {
        foodDB2 = foodDB2(context)
    }

    fun insertFood(item: PopularFoodAdapterClass2) {
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
        foodDB2.putListObject("CartList", listFood)
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show()
    }

    val listCart: ArrayList<PopularFoodAdapterClass2>
        get() = foodDB2.getListObject("CartList")

    fun plusNumberFood(listFood: ArrayList<PopularFoodAdapterClass2>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        listFood[position].numberInCart++
        foodDB2.putListObject("CartList", listFood)
        changeNumberItemsListener.changed()
    }

    fun minusNumberFood(listfood: ArrayList<PopularFoodAdapterClass2>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        if (listfood[position].numberInCart == 1) {
            listfood.removeAt(position)
        } else {
            listfood[position].numberInCart--
        }
        foodDB2.putListObject("CartList", listfood)
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