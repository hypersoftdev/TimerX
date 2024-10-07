package com.sample.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sample.example.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.simpleCountDownTimerButton.setOnClickListener {
            findNavController().navigate(R.id.simpleCountDownFragment)
        }
        binding.intervalCounterButton.setOnClickListener {
            findNavController().navigate(R.id.counterWithIntervalsFragment)
        }
        binding.formatButton.setOnClickListener {
            findNavController().navigate(R.id.formatTimeDataFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}