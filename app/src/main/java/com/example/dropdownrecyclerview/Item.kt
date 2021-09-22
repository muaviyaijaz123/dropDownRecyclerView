package com.example.dropdownrecyclerview

class Item(var itemName:String,var itemPrice:Float,var itemLiked:Boolean ) {

    public fun _getItemName() : String{
        return itemName
    }

    public fun _getItemPrice() : Float{
        return itemPrice
    }
    public fun _getItemLiked() : Boolean{
        return itemLiked
    }

    public fun _setItemName(_itemName:String)  {
        itemName= _itemName
    }

    public fun _setItemPrice(_itemPrice:Float) {
        itemPrice= _itemPrice
    }
    public fun _setItemLiked(status:Boolean){
        itemLiked = status
    }

}