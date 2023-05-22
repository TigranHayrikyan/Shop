package com.example.shop.framgents

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        idList.add(1)
        nameList.add("Cappuccino")
        priceList.add("$10")
        imageList.add(R.drawable.img)
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
    }
    private fun insertBasketDataToDatabase(productBasket: ProductBasket) {
        basketProductViewModel.addProduct(productBasket)
    }

    override fun onItemClick(productBookmark: Product) {}

    override fun onBasketItemClick(productBasket: ProductBasket) {}
}