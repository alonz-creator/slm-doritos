package com.example.slm_doritos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.slm_doritos.databinding.FragmentBerandaBinding
import com.google.android.material.snackbar.Snackbar

class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        // Baca username dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Member Alonz")
        binding.tvGreeting.text = "Halo, $username!"

        // Card 1: Total Pangkas Hari Ini -> Snackbar
        binding.cardTotalPangkas.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Total Pangkas Hari Ini: 15 Pelanggan Selesai",
                Snackbar.LENGTH_SHORT
            ).setAction("Tutup") {
                Log.e("Info Snackbar", "Snackbar Total Pangkas ditutup")
            }.show()
        }

        // Card 2: Slot Antrean Tersedia -> Snackbar
        binding.cardSlotAntrean.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Slot Antrean: 3 Slot Masih Tersedia Hari Ini",
                Snackbar.LENGTH_SHORT
            ).setAction("Tutup") {
                Log.e("Info Snackbar", "Snackbar Slot Antrean ditutup")
            }.show()
        }

        // Card 3: Barber On-Duty -> Snackbar
        binding.cardBarberOnDuty.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Barber On-Duty: 4 Stylist Profesional Siap Melayani",
                Snackbar.LENGTH_SHORT
            ).setAction("Tutup") {
                Log.e("Info Snackbar", "Snackbar Barber On-Duty ditutup")
            }.show()
        }

        // Card 4: Rating & Ulasan -> Snackbar
        binding.cardRatingLayanan.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Rating Barbershop: 4.9/5.0 dari 500+ Ulasan",
                Snackbar.LENGTH_SHORT
            ).setAction("Tutup") {
                Log.e("Info Snackbar", "Snackbar Rating ditutup")
            }.show()
        }

        // Action Web Barbershop
        binding.btnWebsite.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        // Klaim Promo -> Snackbar
        binding.btnKlaimPromo.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Kode PROMO20 Berhasil Diklaim!",
                Snackbar.LENGTH_SHORT
            ).setAction("Tutup") {}.show()
        }
    }

    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(requireContext(), "Search Clicked", Toast.LENGTH_SHORT).show()
                Log.e("Info Menu", "Option Menu Search dipilih")
                true
            }
            R.id.action_settings -> {
                Toast.makeText(requireContext(), "Settings Clicked", Toast.LENGTH_SHORT).show()
                Log.e("Info Menu", "Option Menu Settings dipilih")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
