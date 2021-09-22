package com.example.dropdownrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var dropDownitems: ArrayList<String> = arrayListOf("All Items", "Favourite Items")
    var adapter:ArrayAdapter<String>?=null
    var spinner:Spinner?=null

    var customAdapter:CustomAdapter?=null
    var recyclerView:RecyclerView?=null

    var allItems:ArrayList<Item> = ArrayList<Item>()
    var favouriteItems:ArrayList<Item> = ArrayList<Item>()   //Favourite_items list
    var temporaryItems:ArrayList<Item> = ArrayList<Item>()   //All_items list

    var list_type:String=""   // favoutite or all items list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //spinner setup
        spinner = findViewById(R.id.dropDown_List)
        adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_spinner_dropdown_item,dropDownitems)
        spinner!!.adapter =adapter


        //adding items
        allItems.add(Item("Beef Burger",240f,false))
        allItems.add(Item("Chicken Burger",270f,false))
        allItems.add(Item("Shawarma",370f,false))
        allItems.add(Item("Paratha Roll",490f,false))
        allItems.add(Item("French Fries",660f,false))
        allItems.add(Item("Fajita Pizza",990f,false))
        allItems.add(Item("Tikka Pizza",880f,false))
        allItems.add(Item("Chicken Biryani",550f,false))
        allItems.add(Item("Mayonese Sauce",140f,false))
        allItems.add(Item("Lamb",209f,false))
        allItems.add(Item("Zinger Wrap",330f,false))
        allItems.add(Item("Alfredo Pasta",410f,false))
        allItems.add(Item("Lasagna",567f,false))
        allItems.add(Item("Hot $ Sour Soup",440f,false))
        allItems.add(Item("Grilled Fish",120f,false))
        allItems.add(Item("Spiral",770.5f,false))
        allItems.add(Item("Hot Shots",560f,false))
        allItems.add(Item("Nuggets",1020f,false))
        allItems.add(Item("Brownie",920f,false))
        allItems.add(Item("Hot & Lava Cake",1020f,false))

        //copying list to temporary list
        for(i in allItems)
            temporaryItems.add(i)

        //setting RecView and Adapter
        customAdapter = CustomAdapter()
        customAdapter!!.setData(temporaryItems,"allitems")
        recyclerView=findViewById(R.id.itemsRecyclerView)
        recyclerView!!.layoutManager =LinearLayoutManager(this)
        recyclerView!!.adapter =customAdapter

        //spinner onClickListener

        spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if(position==0)
                {
                    temporaryItems =customAdapter!!.getItems()            //get temporary items
                    list_type = customAdapter!!.getlistType()             //get list type

                      if(list_type.equals("allitems")==true)
                      {
                          Log.e("inside all items","hereee!!!")
                          allItems.clear()
                          for(i in temporaryItems)
                              allItems.add(i)

                          favouriteItems.clear()
                          for(i in allItems)
                          {
                              if (i._getItemLiked()==true)
                                  favouriteItems.add(i)
                          }
                      }
                    else if (list_type.equals("favitems")==true)
                      {
                        var found:Boolean = false

                        favouriteItems.clear()
                          for (i in temporaryItems)
                              favouriteItems.add(i)


                        for(i in allItems)
                        {
                            found =false;
                            for (j in favouriteItems)
                            {
                                if (i._getItemName().equals(j._getItemName())==true)
                                {
                                    found= true
                                    break
                                }
                            }
                            if(found ==false)
                            {
                                if(i._getItemLiked()==true)
                                {
                                    i._setItemLiked(false)
                                }
                            }
                        }

                    }
                    temporaryItems.clear()
                    for(i in allItems)
                        temporaryItems.add(i)
                    customAdapter!!.setData(temporaryItems,"allitems")
                }
                else if(position==1)
                {
                    temporaryItems =customAdapter!!.getItems()            //get temporary items
                    list_type = customAdapter!!.getlistType()             //get list type

                    if(list_type.equals("allitems")==true)
                    {
                        allItems.clear()
                        for(i in temporaryItems)
                            allItems.add(i)

                        favouriteItems.clear()
                        for(i in allItems)
                        {
                            if (i._getItemLiked()==true)
                                favouriteItems.add(i)
                        }
                    }
                    else if (list_type.equals("favitems")==true)
                    {
                        var found:Boolean = false

                        favouriteItems.clear()
                        for (i in temporaryItems)
                            favouriteItems.add(i)


                        for(i in allItems)
                        {
                            found =false;
                            for (j in favouriteItems)
                            {
                                if (i._getItemName().equals(j._getItemName())==true)
                                {
                                    found= true
                                    break
                                }
                            }
                            if(found ==false)
                            {
                                if(i._getItemLiked()==true)
                                {
                                    i._setItemLiked(false)
                                }
                            }
                        }

                    }
                    temporaryItems.clear()
                    for(i in favouriteItems)
                        temporaryItems.add(i)
                    customAdapter!!.setData(temporaryItems,"favitems")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        }


}