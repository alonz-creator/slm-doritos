package com.example.slm_doritos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slm_doritos.databinding.FragmentLayananBinding
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LayananFragment : Fragment() {

    private var _binding: FragmentLayananBinding? = null
    private val binding get() = _binding!!

    private var selectedService = "Gentleman Haircut"

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

        // Floating Action Button Pesan (Pojok Kanan Bawah)
        binding.fabMessages.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MessageFragment())
                .addToBackStack(null)
                .commit()
        }

        // Selection Grid Card 1: Haircut
        binding.cardHaircut.setOnClickListener {
            selectedService = "Gentleman Haircut"
            highlightCard(1)
            Toast.makeText(requireContext(), "Memilih: Gentleman Haircut", Toast.LENGTH_SHORT).show()
        }

        // Selection Grid Card 2: Beard Trim
        binding.cardBeard.setOnClickListener {
            selectedService = "Beard Trim & Shape"
            highlightCard(2)
            Toast.makeText(requireContext(), "Memilih: Beard Trim & Shape", Toast.LENGTH_SHORT).show()
        }

        // Selection Grid Card 3: Styling & Wash
        binding.cardStyling.setOnClickListener {
            selectedService = "Hair Styling & Wash"
            highlightCard(3)
            Toast.makeText(requireContext(), "Memilih: Hair Styling & Wash", Toast.LENGTH_SHORT).show()
        }

        // Chip Filter Listener
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Tombol Simpan Booking Paling Bawah
        binding.btnSimpanBooking.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                binding.textInputLayout.error = "Email member tidak boleh kosong"
            } else {
                binding.textInputLayout.error = null
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Konfirmasi Simpan Booking")
                    .setMessage("Simpan booking slot $selectedService untuk $email?")
                    .setPositiveButton("Ya, Simpan") { dialog, _ ->
                        dialog.dismiss()
                        Toast.makeText(requireContext(), "Booking $selectedService Berhasil Disimpan!", Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun highlightCard(cardIndex: Int) {
        val goldColor = ContextCompat.getColor(requireContext(), R.color.gold_primary)
        val defaultStroke = ContextCompat.getColor(requireContext(), R.color.card_stroke)

        binding.cardHaircut.strokeColor = if (cardIndex == 1) goldColor else defaultStroke
        binding.cardHaircut.strokeWidth = if (cardIndex == 1) 6 else 2

        binding.cardBeard.strokeColor = if (cardIndex == 2) goldColor else defaultStroke
        binding.cardBeard.strokeWidth = if (cardIndex == 2) 6 else 2

        binding.cardStyling.strokeColor = if (cardIndex == 3) goldColor else defaultStroke
        binding.cardStyling.strokeWidth = if (cardIndex == 3) 6 else 2
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
