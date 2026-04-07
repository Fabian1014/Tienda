package com.tienda.ui.main.productos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tienda.R

class ProductoAdapter(
    private val productos: List<Product>,
) : RecyclerView.Adapter<ProductoAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.img_producto)
        val nombre: TextView = itemView.findViewById(R.id.tv_nombre)
        val precio: TextView = itemView.findViewById(R.id.tv_precio)
        val btnAgregar: Button = itemView.findViewById(R.id.btn_agregar)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductViewHolder(view)
    }
    override fun getItemCount(): Int = productos.size
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val producto = productos[position]
        holder.imagen.setImageResource(producto.imagenRes)
        holder.nombre.text = producto.nombre
        holder.precio.text = "$${producto.precio}"
        holder.btnAgregar.setOnClickListener {
            // Agregar el producto al carrito
        }
    }

}
