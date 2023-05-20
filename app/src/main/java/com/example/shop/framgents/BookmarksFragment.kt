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
import com.example.shop.Adapters.BookmarksAdapter
import com.example.shop.R
import com.example.shop.room_db.product_bookmark.Product
import com.example.shop.room_db.product_bookmark.ProductViewModel

class BookmarksFragment : BookmarksAdapter.OnItemClickListener, Fragment() {

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_liked_products, container, false)
        val adapter = BookmarksAdapter(object : BookmarksAdapter.OnItemClickListener {
            override fun onItemClick(productBookmark: Product) {
                deleteProductFromDb(productBookmark)
            }

            private fun deleteProductFromDb(productBookmark: Product) {
                mProductViewModel.deleteProduct(productBookmark)
            }
        })
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        val emptyTextView = view?.rootView?.findViewById<TextView>(R.id.emptyTextView)
        emptyTextView?.isVisible = recyclerView.isEmpty()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mProductViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        mProductViewModel.readAllData.observe(viewLifecycleOwner) { product ->
            adapter.setData(product)
        }

        return rootView
    }

    override fun onItemClick(productBookmark: Product) {
    }
}