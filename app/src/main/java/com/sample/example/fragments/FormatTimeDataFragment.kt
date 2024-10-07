package com.sample.example.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.mytimerx.formater.FormatManager
import com.sample.example.R
import com.sample.example.databinding.FragmentFormatTimeDataBinding
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
class FormatTimeDataFragment : Fragment() {
    private var _binding : FragmentFormatTimeDataBinding? = null
    private val binding get() = _binding!!
    private val timeFormatter :FormatManager by lazy {
        FormatManager()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFormatTimeDataBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val time1 = timeFormatter.getCurrentTime24HourFormat()
            val time2 = timeFormatter.getCurrentTime12HourFormat()
            Log.d("TAG_FORMATTER", time1)
            Log.d("TAG_FORMATTER", time2)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}