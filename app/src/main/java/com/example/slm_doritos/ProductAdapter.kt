package com.example.slm_doritos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.slm_doritos.databinding.ItemProductBinding

/**
 * Pertemuan 11: RecyclerView.Adapter untuk mengikat data produk ke dalam ViewHolder
 */
class ProductAdapter(
    private val productList: List<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder sebagai pemegang referensi ViewBinding item
    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Membuat ViewHolder baru dengan meng-inflate layout item_product.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    // Mengikat data produk pada posisi tertentu ke dalam elemen View
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        with(holder.binding) {
            tvProductName.text = item.name
            tvProductPrice.text = item.price

            // Memuat gambar produk dari URL secara asynchronous menggunakan pustaka Glide
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgProduct)

            // Menangani aksi klik pada item produk
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    // Mengembalikan jumlah total data produk
    override fun getItemCount(): Int = productList.size
}
