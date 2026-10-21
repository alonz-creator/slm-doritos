package com.example.slm_doritos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.slm_doritos.databinding.FragmentLayananBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class LayananFragment : Fragment() {

    private var _binding: FragmentLayananBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLayananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.btnBookHaircut.setOnClickListener {
            showBookingConfirmation("Gentleman Haircut")
        }

        binding.btnBookBeard.setOnClickListener {
            showBookingConfirmation("Beard Trim & Shape")
        }

        binding.btnBookStyling.setOnClickListener {
            showBookingConfirmation("Hair Styling & Wash")
        }
    }

    private fun showBookingConfirmation(serviceName: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Konfirmasi Booking")
            .setMessage("Apakah Anda yakin ingin memesan slot untuk $serviceName?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                Log.e("Info Booking", "Booking berhasil untuk $serviceName")

                // Tampilkan Snackbar setelah berhasil booking
                Snackbar.make(
                    binding.root,
                    "Booking $serviceName Berhasil!",
                    Snackbar.LENGTH_LONG
                ).setAction("Tutup") {
                    Log.e("Info Snackbar", "Snackbar booking ditutup")
                }.show()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
                Log.e("Info Booking", "Booking dibatalkan")
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
