package com.hypersoft.timer.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.hypersoft.timerx.formater.FormatManager
import com.hypersoft.timer.databinding.FragmentFormatTimeDataBinding


@RequiresApi(Build.VERSION_CODES.O)
class FormatTimeDataFragment : Fragment() {
    private var _binding : FragmentFormatTimeDataBinding? = null
    private val binding get() = _binding!!
    private val timeFormatter : FormatManager by lazy {
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
        binding.btnFormatTime.setOnClickListener {
            val result = timeFormatter.formatTime(
                timeInMillis = System.currentTimeMillis(),
                format = "dd/MM/yyyy HH:mm"
            )
            "Formatted Time: $result".also { binding.tvResult.text = it }
        }
        binding.btnConvertMillis.setOnClickListener {
            val result = timeFormatter.convertMillisToFormattedString(
                timeInMillis = System.currentTimeMillis(),
                format = "HH:mm:ss"
            )
            "Converted Millis:: $result".also { binding.tvResult.text = it }
        }
        binding.btnGetCurrentTime24.setOnClickListener {
            val result = timeFormatter.getCurrentTime24HourFormat()
            "Current Time (24-Hour): $result".also { binding.tvResult.text = it }
        }
        binding.btnGetCurrentTime12.setOnClickListener {
            val result = timeFormatter.getCurrentTime12HourFormat()
            "Current Time (12-Hour): $result".also { binding.tvResult.text = it }
        }
        binding.btnTimeDifference.setOnClickListener {
            val time1 = "14:30"
            val time2 = "18:45"
            val result = timeFormatter.timeDifference(time1, time2)
            "Time Difference: $result".also { binding.tvResult.text = it }
        }
        binding.btnIsValidTime.setOnClickListener {
            val time = "14:30"
            val result = timeFormatter.isValidTimeFormat(time)
            "Is Valid Time Format (HH:mm): $result".also { binding.tvResult.text = it }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}