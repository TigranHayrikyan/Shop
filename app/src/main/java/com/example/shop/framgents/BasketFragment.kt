package com.example.shop.framgents

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.Adapters.BasketAdapter
import com.example.shop.R
import com.example.shop.room_db.product_basket.ProductBasket
import com.example.shop.room_db.product_basket.ProductBasketViewModel

class BasketFragment : BasketAdapter.OnItemClickListener, Fragment() {

    private lateinit var mProductViewModel: ProductBasketViewModel
    private lateinit var  adapter : BasketAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_liked_products, container, false)
        initAdapterConfigs()
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        mProductViewModel = ViewModelProvider(this)[ProductBasketViewModel::class.java]
        mProductViewModel.readAllBasketData.observe(viewLifecycleOwner) { product ->
            adapter.setData(product)
        }

        return rootView
    }

    private fun initAdapterConfigs() {
        adapter = BasketAdapter(object : BasketAdapter.OnItemClickListener {
            override fun onItemClick(productBasket: ProductBasket) {
                deleteProductFromDb(productBasket)
            }

            private fun deleteProductFromDb(productBasket: ProductBasket) {
                mProductViewModel.deleteProduct(productBasket)
            }
        })
    }

    override fun onItemClick(productBasket: ProductBasket) {
    }
}