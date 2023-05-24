package com.example.shop.framgents

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.Adapters.BookmarksAdapter
import com.example.shop.R
import com.example.shop.room_db.product_bookmark.Product
import com.example.shop.room_db.product_bookmark.ProductViewModel

class BookmarksFragment : BookmarksAdapter.OnItemClickListener, Fragment() {

    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var adapter : BookmarksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_liked_products, container, false)
        adapter = BookmarksAdapter(object : BookmarksAdapter.OnItemClickListener {
            override fun onItemClick(productBookmark: Product) {
                deleteItemAnimation(rootView)
                deleteProductFromDb(productBookmark)
            }

            private fun deleteProductFromDb(productBookmark: Product) {
                mProductViewModel.deleteProduct(productBookmark)
            }
        })
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        mProductViewModel.readAllData.observe(viewLifecycleOwner) { product ->
            adapter.setData(product)
        }

        return rootView
    }

    private fun deleteItemAnimation(view :View){
        val translationYFrom = 1000f
        val translationYTo = 0f
        val valueAnimator = ValueAnimator.ofFloat(translationYFrom, translationYTo).apply {
            interpolator = LinearInterpolator()
            duration = 1000
        }
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            view.translationY = value
        }
        valueAnimator.start()
    }
    override fun onItemClick(productBookmark: Product) {
    }
}