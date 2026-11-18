package com.example.slm_doritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.slm_doritos.databinding.FragmentTabKatalogProdukBinding

/**
 * Pertemuan 11: Fragment pengelola RecyclerView Katalog Produk
 */
class TabKatalogProdukFragment : Fragment() {

    private var _binding: FragmentTabKatalogProdukBinding? = null
    private val binding get() = _binding!!

    // Sumber data produk lokal (Product List)
    private val productList = listOf(
        ProductModel("Alonz Matte Pomade", "Rp 85.000", "https://picsum.photos/seed/pomade1/400/300"),
        ProductModel("Alonz Hair Wax Strong Hold", "Rp 75.000", "https://picsum.photos/seed/wax1/400/300"),
        ProductModel("Alonz Beard Oil Organic", "Rp 65.000", "https://picsum.photos/seed/beardoil1/400/300"),
        ProductModel("Alonz Hair Tonic Ginseng", "Rp 95.000", "https://picsum.photos/seed/tonic1/400/300"),
        ProductModel("Alonz Shaving Cream Soft", "Rp 55.000", "https://picsum.photos/seed/cream1/400/300"),
        ProductModel("Alonz Barber Comb Set", "Rp 45.000", "https://picsum.photos/seed/comb1/400/300"),
        ProductModel("Alonz Sea Salt Spray", "Rp 80.000", "https://picsum.photos/seed/spray1/400/300"),
        ProductModel("Alonz Charcoal Shampoo", "Rp 90.000", "https://picsum.photos/seed/shampoo1/400/300"),
        ProductModel("Alonz Clay Pomade Natural", "Rp 88.000", "https://picsum.photos/seed/clay1/400/300"),
        ProductModel("Alonz Barber Cape Professional", "Rp 120.000", "https://picsum.photos/seed/cape1/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabKatalogProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi ProductAdapter dengan callback penanganan klik item
        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih produk: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        // 2. Mengatur LayoutManager menjadi Grid 2 Kolom dan memasangkan Adapter ke RecyclerView
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
