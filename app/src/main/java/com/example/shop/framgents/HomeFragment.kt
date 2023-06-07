package com.example.shop.framgents

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.adapters.HomeAdapter
import com.example.shop.adapters.HomeAdapter.OnItemClickListener
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
    private var priceList: ArrayList<Int> = ArrayList()
    private var imageList: ArrayList<Int> = ArrayList()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var searchEditText: EditText
    private val productList: ArrayList<Product> = ArrayList()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        basketProductViewModel = ViewModelProvider(this)[ProductBasketViewModel::class.java]
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        idList.add(1)
        nameList.add("aaa")
        priceList.add(20)
        imageList.add(R.drawable.img)
        productList.add(Product(idList[0], nameList[0], priceList[0], imageList[0]))

        idList.add(2)
        nameList.add("bbb")
        priceList.add(30)
        imageList.add(R.drawable.img)
        productList.add(Product(idList[1], nameList[1], priceList[1], imageList[1]))

        idList.add(3)
        nameList.add("ccc")
        priceList.add(10)
        imageList.add(R.drawable.img)
        productList.add(Product(idList[2], nameList[2], priceList[2], imageList[2]))

        idList.add(4)
        nameList.add("ddd")
        priceList.add(15)
        imageList.add(R.drawable.img)
        productList.add(Product(idList[3], nameList[3], priceList[3], imageList[3]))

        val allHomeAdapter = HomeAdapter(idList,
            nameList, priceList, imageList,
            object : OnItemClickListener {
                override fun onItemClick(productBookmark: Product) {
                    insertDataToDatabase(productBookmark)
                }

                override fun onBasketItemClick(productBasket: ProductBasket) {
                    insertBasketDataToDatabase(productBasket)
                }
            }
        )
        homeAdapter = allHomeAdapter

        recyclerView.adapter = homeAdapter

        searchEditText = rootView.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener {
           searchItems(searchEditText.text.toString())
        }

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

    private fun searchItems(searchText: String) {

        for (product in productList) {
            if (product.nameList.contains(searchText, ignoreCase = true)) {
                idList.clear()
                idList.add(product.id)
                nameList.clear()
                nameList.add(product.nameList)
                priceList.clear()
                priceList.add(product.priceList)
                imageList.clear()
                imageList.add(product.imageList)
                val searchHomeAdapter = HomeAdapter(idList,
                    nameList, priceList, imageList,
                    object : OnItemClickListener {
                        override fun onItemClick(productBookmark: Product) {
                            insertDataToDatabase(productBookmark)
                        }

                        override fun onBasketItemClick(productBasket: ProductBasket) {
                            insertBasketDataToDatabase(productBasket)
                        }
                    }
                )
                homeAdapter = searchHomeAdapter
            }
        }
    }
}