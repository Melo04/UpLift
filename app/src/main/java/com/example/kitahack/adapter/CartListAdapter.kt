package com.example.kitahack.adapter

import com.example.kitahack.Interface.ChangeNumberItemsListener
import androidx.recyclerview.widget.RecyclerView
import com.example.kitahack.model.ManagementCart
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.kitahack.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.TextView
import java.util.ArrayList

class CartListAdapter(
    private val popularFoodAdapterClass: ArrayList<PopularFoodAdapterClass>,
    context: Context?,
    changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private val managementCart: ManagementCart
    private val changeNumberItemsListener: ChangeNumberItemsListener

    init {
        managementCart = ManagementCart((context)!!)
        this.changeNumberItemsListener = changeNumberItemsListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.title.text = popularFoodAdapterClass[position].title
        holder.feeEachItem.text = popularFoodAdapterClass[position].fee.toString()
        holder.totalEachItem.text = Math.round(
            (popularFoodAdapterClass.get(position).numberInCart * popularFoodAdapterClass.get(
                position
            ).fee * 100 / 100)
        ).toString()
        holder.num.text = popularFoodAdapterClass.get(position).numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(popularFoodAdapterClass[position].pic)
            .into(holder.pic)

        val changeNumberItemsListener = object : ChangeNumberItemsListener {
            override fun changed() {
                notifyDataSetChanged()
                changeNumberItemsListener.changed()
            }
        }

        holder.plusItem.setOnClickListener {
            managementCart.plusNumberFood(
                popularFoodAdapterClass,
                position,
                changeNumberItemsListener
            )
        }

        holder.minusItem.setOnClickListener {
            managementCart.minusNumberFood(
                popularFoodAdapterClass,
                position,
                changeNumberItemsListener
            )
        }

       holder.minusItem.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                managementCart.minusNumberFood(
                    popularFoodAdapterClass,
                    position,
                    object : ChangeNumberItemsListener {
                        @SuppressLint("NotifyDataSetChanged")
                        override fun changed() {
                            notifyDataSetChanged()
                            changeNumberItemsListener.changed()
                        }
                    })
            }
        })
    }

    override fun getItemCount(): Int {
        return popularFoodAdapterClass.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var feeEachItem: TextView
        var pic: ImageView
        var plusItem: ImageView
        var minusItem: ImageView
        var totalEachItem: TextView
        var num: TextView

        init {
            title = itemView.findViewById(R.id.requirementsTxt)
            feeEachItem = itemView.findViewById(R.id.feeEachItem)
            pic = itemView.findViewById(R.id.picCart)
            totalEachItem = itemView.findViewById(R.id.totalEachItem)
            num = itemView.findViewById(R.id.numberItemTxt)
            plusItem = itemView.findViewById(R.id.plusCartBtn)
            minusItem = itemView.findViewById(R.id.minusCartBtn)
        }
    }
}