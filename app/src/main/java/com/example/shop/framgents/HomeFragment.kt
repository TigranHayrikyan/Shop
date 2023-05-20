package com.example.shop.framgents

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.Adapters.HomeAdapter
import com.example.shop.Adapters.HomeAdapter.OnItemClickListener
import com.example.shop.R
import com.example.shop.room_db.product_basket.ProductBasket
import com.example.shop.room_db.product_basket.ProductBasketViewModel
import com.example.shop.room_db.product_bookmark.Product
import com.example.shop.room_db.product_bookmark.ProductViewModel


class HomeFragment : OnItemClickListener, Fragment() {

    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var basketProductViewModel: ProductBasketViewModel
    private lateinit var recyclerView: RecyclerView
    private var idList: ArrayList<Int> = ArrayList()
    private var nameList: ArrayList<String> = ArrayList()
    private var priceList: ArrayList<String> = ArrayList()
    private var imageList: ArrayList<Int> = ArrayList()
    private lateinit var homeAdapter: HomeAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        basketProductViewModel = ViewModelProvider(this)[ProductBasketViewModel::class.java]
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        isListEmpty()
        idList.add(1)
        nameList.add("Name: Banana")
        priceList.add("Price: 10$")
        imageList.add(R.drawable.banana)
        idList.add(2)
        nameList.add("Name: Home")
        priceList.add("Price: 1000$")
        imageList.add(R.drawable.home)
        homeAdapter = HomeAdapter(idList,
            nameList, priceList, imageList,
            requireContext(), object : OnItemClickListener {
                override fun onItemClick(productBookmark: Product) {
                    insertDataToDatabase(productBookmark)
                }

                override fun onBasketItemClick(productBasket: ProductBasket) {
                    insertBasketDataToDatabase(productBasket)
                }
            }
        )
        recyclerView.adapter = homeAdapter

        return rootView
    }

    private fun insertDataToDatabase(productBookmark: Product) {
        mProductViewModel.addProduct(productBookmark)
        Toast.makeText(requireContext(), "product is saved", Toast.LENGTH_SHORT).show()
    }
    private fun insertBasketDataToDatabase(productBasket: ProductBasket) {
        basketProductViewModel.addProduct(productBasket)
        Toast.makeText(requireContext(), "product is saved", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(productBookmark: Product) {}

    override fun onBasketItemClick(productBasket: ProductBasket) {}

    private fun isListEmpty(){
        val emptyTextView = view?.rootView?.findViewById<TextView>(R.id.emptyTextView)
        emptyTextView?.isVisible = idList.isEmpty()
    }
}