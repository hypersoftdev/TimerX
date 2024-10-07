package com.sample.example.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytimerx.formater.FormatManager
import com.example.mytimerx.intervaltimer.CountDownTimerWithIntervals
import com.sample.example.databinding.FragmentCounterWithIntervalsBinding


class CounterWithIntervalsFragment : Fragment() {
    private var _binding: FragmentCounterWithIntervalsBinding? = null
    private val binding get() = _binding!!
    private val counterWithIntervals: CountDownTimerWithIntervals = CountDownTimerWithIntervals()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCounterWithIntervalsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonListeners()
    }

    private fun setupButtonListeners() {

        binding.startButton.setOnClickListener {
            counterWithIntervals.start(10,1,
                CountDownTimerWithIntervals.TimeFormat.SECONDS,
                false,
                { remainingTime ->
                    Log.d("TAG_TIMER", "onStart $remainingTime")
                binding.timerTextView.text = remainingTime.toString()
            }, {
                Log.d("TAG_TIMER", "onFinish: Timer Finished")
                    "Finished".also { binding.timerTextView.text = it }
            })
        }
        binding.startButtonWithUp.setOnClickListener {
            counterWithIntervals.start(10,1,
                CountDownTimerWithIntervals.TimeFormat.SECONDS,
                true,
                { remainingTime ->
                    Log.d("TAG_TIMER", "onStart $remainingTime")
                    binding.timerTextView.text = remainingTime.toString()
                }, {
                    Log.d("TAG_TIMER", "onFinish: Timer Finished")
                    "Finished".also { binding.timerTextView.text = it }
                })
        }
        binding.pauseButton.setOnClickListener { counterWithIntervals.pause() }
        binding.resumeButton.setOnClickListener { counterWithIntervals.resume() }
        binding.stopButton.setOnClickListener {
            counterWithIntervals.stop()
            "Stopped".also { binding.timerTextView.text = it }
        }
        binding.resetButton.setOnClickListener {
            counterWithIntervals.reset()
            "Reset".also { binding.timerTextView.text = it }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}