package com.example.shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.findColumnIndexBySuffix
import com.example.shop.room_db.Product
import com.example.shop.room_db.ProductViewModel


class HomeFragment : Fragment() {

    private lateinit var mProductViewModel: ProductViewModel
    private lateinit var recyclerView: RecyclerView
    private var nameList: ArrayList<String> = ArrayList()
    private var priceList: ArrayList<String> = ArrayList()
    private var imageList: ArrayList<Int> = ArrayList()
    private lateinit var recyclerAdapter: RecyclerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        nameList.add("Name: Banana")
        priceList.add("Price: 10$")
        imageList.add(R.drawable.banana)
        recyclerAdapter = RecyclerAdapter(nameList, priceList, imageList, requireContext())
        recyclerView.adapter = recyclerAdapter

        val addBtn = rootView.findViewById<Button>(R.id.addBtn)
        kotlin.run {
            addBtn.setOnClickListener {
                insertDataToDatabase()
            }
        }

        return rootView
    }

    private fun insertDataToDatabase() {
        val product = Product(1, "nameList", "priceList", R.drawable.home)

        mProductViewModel.addProduct(product)
        Toast.makeText(requireContext(), "product is saved", Toast.LENGTH_SHORT).show()
    }
}