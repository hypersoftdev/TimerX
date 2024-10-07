package com.sample.example.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytimerx.simplecountdown.SimpleCountDownTimer
import com.sample.example.databinding.FragmentSimpleCountDownBinding


class SimpleCountDownFragment : Fragment() {

    private var _binding: FragmentSimpleCountDownBinding? = null
    private val binding get() = _binding!!
    private val simpleCountDownTimer: SimpleCountDownTimer = SimpleCountDownTimer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSimpleCountDownBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonListeners()

    }

    private fun setupButtonListeners() {

        binding.startButton.setOnClickListener {
            simpleCountDownTimer.start(10,SimpleCountDownTimer.TimeFormat.SECONDS, { remainingTime ->
                binding.timerTextView.text = remainingTime.toString()
            }, {
                Log.d("TAG_TIMER", "onFinish: Timer Finished")
                binding.timerTextView.text = "Finished"
            })
        }
        binding.pauseButton.setOnClickListener { simpleCountDownTimer.pause() }
        binding.resumeButton.setOnClickListener { simpleCountDownTimer.resume() }
        binding.stopButton.setOnClickListener {
            simpleCountDownTimer.stop()
            binding.timerTextView.text = "Stopped"
        }
        binding.resetButton.setOnClickListener {
            simpleCountDownTimer.reset()
            binding.timerTextView.text = "Reset"
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}