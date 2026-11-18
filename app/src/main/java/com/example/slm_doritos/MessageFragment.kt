package com.example.slm_doritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.slm_doritos.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Kapster Budi", "Halo Kak! Slot pangkas jam 15:00 siap ya.", "https://avatarapi.runflare.run/public/1"),
        MessageModel("Admin Alonz", "Promo potongan 20% Paket VIP Alonz sedang aktif!", "https://avatarapi.runflare.run/public/2"),
        MessageModel("Kapster Fajar", "Gaya rambut Gentleman Haircut sudah dicarikan modelnya.", "https://avatarapi.runflare.run/public/3"),
        MessageModel("Alonz Barbershop", "Terima kasih telah menjadi Member VIP Alonz Barbershop!", "https://avatarapi.runflare.run/public/4"),
        MessageModel("Kasir Alonz", "Kupon diskon pangkas rambut Anda berlaku sampai akhir bulan.", "https://avatarapi.runflare.run/public/5"),
        MessageModel("Kapster Dika", "Sampai jumpa di slot cukur besok sore jam 16:30 WIB!", "https://avatarapi.runflare.run/public/6")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Pesan Masuk"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
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
