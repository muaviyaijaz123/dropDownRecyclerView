package com.example.dropdownrecyclerview


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    var allItemsList = ArrayList<Item>()
    var list_type:String =""

    fun setData(_items: ArrayList<Item>,_list_type:String) {
        allItemsList = _items
        list_type =_list_type
        notifyDataSetChanged()
    }

    /*override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(allItemsList.get(position));
    }*/
    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        //holder.bind(allItemsList[position],list_type)
        var item_label = holder.itemView.findViewById(R.id.itemName) as TextView
        var item_Price = holder.itemView.findViewById(R.id.itemPrice) as TextView
        var item_image = holder.itemView.findViewById(R.id.starFavouriteButton) as ImageView

        item_label.text = allItemsList[position]._getItemName()
        item_Price.text = allItemsList[position]._getItemPrice().toString()

        if(allItemsList[position]._getItemLiked()==true)
        {
            item_image.setImageResource(R.drawable.yellow_star)
        }
        else
        {
            item_image.setImageResource(R.drawable.grey_star)
        }

        item_image.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View) {
                //var _position: Int =p0.getTag() as Int;
                if(list_type.equals("allitems")==true)
                {
                    // Access the row position here to get the correct data item

                    if(allItemsList[holder.adapterPosition]._getItemLiked()==true)
                    {
                        allItemsList[holder.adapterPosition].itemLiked=false
                        Log.e("item_name disliked: ",allItemsList[holder.adapterPosition]._getItemName())
                        notifyItemChanged(holder.adapterPosition)
                    }
                    else if(allItemsList[holder.adapterPosition]._getItemLiked()==false)
                    {
                        allItemsList[holder.adapterPosition].itemLiked=true
                        Log.e("item_name liked: ",allItemsList[holder.adapterPosition]._getItemName())
                        notifyItemChanged(holder.adapterPosition)
                    }

                }
                else if(list_type.equals("favitems")==true)
                {
                    Log.e("Removed item_name: ",allItemsList[holder.adapterPosition]._getItemName())
                    allItemsList.removeAt(holder.adapterPosition)
                    notifyItemRemoved(holder.adapterPosition)
                }

            }

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return allItemsList.size
    }

    fun getItems() : ArrayList<Item>
    {
        return allItemsList
    }

    fun getlistType() : String
    {
        return list_type
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(_item: Item,list_type:String): Unit {



        }
    }



}

