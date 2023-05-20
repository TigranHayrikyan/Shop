package com.example.shop.framgents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.Adapters.BasketAdapter
import com.example.shop.R
import com.example.shop.room_db.product_basket.ProductBasket
import com.example.shop.room_db.product_basket.ProductBasketViewModel
import com.example.shop.room_db.product_bookmark.Product
import com.example.shop.room_db.product_bookmark.ProductViewModel

class BasketFragment : BasketAdapter.OnItemClickListener, Fragment() {

    private lateinit var mProductViewModel: ProductBasketViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_liked_products, container, false)
        val adapter = BasketAdapter(object : BasketAdapter.OnItemClickListener {
            override fun onItemClick(productBasket: ProductBasket) {
                deleteProductFromDb(productBasket)
            }

            private fun deleteProductFromDb(productBasket: ProductBasket) {
                mProductViewModel.deleteProduct(productBasket)
            }
        })
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        val emptyTextView = view?.rootView?.findViewById<TextView>(R.id.emptyTextView)
        emptyTextView?.isVisible = recyclerView.isEmpty()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mProductViewModel = ViewModelProvider(this)[ProductBasketViewModel::class.java]
        mProductViewModel.readAllBasketData.observe(viewLifecycleOwner) { product ->
            adapter.setData(product)
        }

        return rootView
    }

    override fun onItemClick(productBasket: ProductBasket) {
    }
}