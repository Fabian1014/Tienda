package com.tienda.ui.main.productos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tienda.R

class HomeFragment : Fragment() {

    private val listaProductos = listOf(
        Product("Camisa Casual", 29.99, R.drawable.camisa),
        Product("Pantalón Slim Fit", 39.99, R.drawable.pantalon),
        Product("Zapatos Deportivos", 59.99, R.drawable.zapatos),
        Product("Jeans", 49.99, R.drawable.jeans),
    )
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_productos)
    recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    recyclerView.adapter = ProductoAdapter(listaProductos)
    return view

}

}