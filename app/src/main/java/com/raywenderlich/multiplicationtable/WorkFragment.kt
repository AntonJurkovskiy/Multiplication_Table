package com.raywenderlich.multiplicationtable


import android.app.ActionBar
import android.os.Bundle
import android.os.CountDownTimer

import android.util.Log

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.raywenderlich.multiplicationtable.databinding.WorkFragmentBinding

class WorkFragment : Fragment(R.layout.work_fragment) {
    companion object {
        const val ARG_MODE_W = "mode_w"
        const val ARG_NUMBER = "number"
    }

    private var timer: CountDownTimer? = null
    private val workData = Data()
    private val listWorkNumber = workData.numberList.shuffled()
    private val myWorkRange = workData.numberList

    private var count = 0
    private var goodAnswer = 0
    private var badAnswer = 0
    var modeWorkFragment: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "onCreate start!")
        super.onCreate(savedInstanceState)
    }

    lateinit var binding: WorkFragmentBinding
    lateinit var animBounce: Animation
    lateinit var animscale: Animation
    lateinit var levelToolBar: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", " WorkFragment onViewCreated start!")
        super.onViewCreated(view, savedInstanceState)
        binding = WorkFragmentBinding.bind(view)
        val userInput = binding.inputEditText.text
        val level = requireArguments().getInt(ARG_NUMBER)
        modeWorkFragment = requireArguments().getString(ARG_MODE_W)
        animBounce =
            android.view.animation.AnimationUtils.loadAnimation(getActivity(), R.anim.bounce)
        animscale = android.view.animation.AnimationUtils.loadAnimation(getActivity(), R.anim.scale)

        checkLevel(level, modeWorkFragment.toString())

        binding.bCheck.setOnClickListener {
            if (userInput.toString() != "") {

                when (modeWorkFragment) {
                    "table" -> {
                        tableMode(level, listWorkNumber[count], level, userInput.toString())
                    }
                    "fast" -> {
                        startCountDawnTimer(60000)
                        tableModeFast(userInput.toString(), modeWorkFragment.toString())


                    }
                }
            } else {
                Toast.makeText(context, R.string.toast_input_empty, Toast.LENGTH_SHORT).show()
            }

        }


    }

    // Определение уровня. Подстановка значений в зависимости от уровня. Отбор колличесва правильных и не правильных ответов
    private fun tableMode(number1: Int, number2: Int, level: Int, inputNumber: String) {

        when (level) {
            10 -> {
                val mainRes = binding.workNumber1Tv.text.toString().toInt() *
                        binding.workNumber2Tv.text.toString().toInt()

                if (mainRes == inputNumber.toInt()) {
                    binding.goodSmileIm.startAnimation(animBounce)
                    goodAnswer++


                    binding.inputEditText.text?.clear()
                } else {
                    binding.badSmileIm.startAnimation(animBounce)
                    badAnswer++

                    binding.inputEditText.text?.clear()
                }
                count++
                if (count < listWorkNumber.size) {
                    binding.workNumber1Tv.text = (2..9).random().toString()
                    binding.workNumber2Tv.text = (2..9).random().toString()

                } else {

                    count = 0
                    getAnswer(goodAnswer, badAnswer)

                }
            }
            else -> {
                val mainRes = number1 * number2
                if (mainRes == inputNumber.toInt()) {
                    binding.goodSmileIm.startAnimation(animBounce)
                    goodAnswer++
                    //binding.goodAnswerTv.text = goodAnswer.toString()
                    binding.inputEditText.text?.clear()
                } else {
                    badAnswer++
                    binding.badSmileIm.startAnimation(animBounce)
                    //binding.badAnswerTv.text = badAnswer.toString()
                    binding.inputEditText.text?.clear()
                }
                count++
                if (count < listWorkNumber.size) {
                    binding.workNumber2Tv.text = listWorkNumber[count].toString()
                } else {
                    count = 0
                    getAnswer(goodAnswer, badAnswer)
                }

            }
        }
    }

    private fun tableModeFast(inputNumber: String, modeType: String) {
        val mainRes = binding.workNumber1Tv.text.toString().toInt() *
                binding.workNumber2Tv.text.toString().toInt()


        if (mainRes == inputNumber.toInt()) {
            goodAnswer++
            binding.goodSmileIm.startAnimation(animBounce)
            binding.inputEditText.text?.clear()
        } else {
            badAnswer++
            binding.badSmileIm.startAnimation(animBounce)
            binding.inputEditText.text?.clear()
        }
        count++
        if (timer != null) {
            binding.workNumber1Tv.text = myWorkRange.random().toString()
            binding.workNumber2Tv.text = myWorkRange.random().toString()

        } else {

            count = 0
            getFastAnswer(goodAnswer, badAnswer, modeWorkFragment.toString())

        }

    }

    private fun startCountDawnTimer(timeMillis: Long) {
        if (timer == null) {
            timer = object : CountDownTimer(timeMillis, 100) {
                override fun onTick(millisUntilFinished: Long) {
                    var finalMillis = millisUntilFinished / 1000
                    binding.timerTv.visibility = View.VISIBLE
                    binding.timerTv.text = finalMillis.toString()
                    if (finalMillis < 11 && finalMillis.toInt() % 2 != 0) {
                        binding.timerTv.textSize = 55f

                    } else {
                        binding.timerTv.textSize = 40f

                    }

                }

                override fun onFinish() {
                    getFastAnswer(
                        posAnswer = goodAnswer,
                        negAnswer = badAnswer,
                        mode = modeWorkFragment.toString()
                    )

                }
            }.start()


        }

    }

    private fun checkLevel(level: Int, mode: String) {
        with(binding) {
            if (level < 10 && mode != "fast") {
                binding.timerTv.visibility = View.INVISIBLE
                workNumber1Tv.text = level.toString()
                workNumber2Tv.text = listWorkNumber[count].toString()
            } else {
                workNumber1Tv.text = myWorkRange.random().toString()
                workNumber2Tv.text = listWorkNumber[count].toString()
            }


        }

    }

    private fun getAnswer(posAnswer: Int, negAnswer: Int) {
        findNavController().navigate(
            R.id.action_workFragment_to_resultFragment,
            bundleOf(ResultFragment.ARG_POS to posAnswer, ResultFragment.ARG_NEG to negAnswer)
        )

    }

    private fun getFastAnswer(posAnswer: Int, negAnswer: Int, mode: String) {
        findNavController().navigate(
            R.id.action_workFragment_to_resultFragment,
            bundleOf(
                ResultFragment.ARG_POS to posAnswer, ResultFragment.ARG_NEG to negAnswer,
                ResultFragment.ARG_MODE to mode
            )
        )

    }


    override fun onPause() {
        Log.d("TAG", " WorkFragment onPause start!")
        super.onPause()
    }

    override fun onStop() {
        Log.d("TAG", "WorkFragment onStop start!")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("TAG", "WorkFragment onDestroy start!")
        super.onDestroy()
    }

}




















