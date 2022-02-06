package com.raywenderlich.multiplicationtable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.raywenderlich.multiplicationtable.databinding.ResultFragmentBinding

class ResultFragment : Fragment(R.layout.result_fragment) {
    companion object {
        const val ARG_POS = "neg_number"
        const val ARG_NEG = "pos_number"

        const val ARG_MODE = "mode"

    }

    private lateinit var binding: ResultFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ResultFragmentBinding.bind(view)
        val positive = requireArguments().getInt(ResultFragment.ARG_POS)
        val negative = requireArguments().getInt(ResultFragment.ARG_NEG)
        val mode = requireArguments().getString(ResultFragment.ARG_MODE)
        binding.resFrGreet.setText(R.string.greet_fast)
        var greet = binding.resFrGreet
        if (mode != "fast") {
            when (positive) {
                in 0..3 -> {
                    greet.setText(R.string.greet_badly)
                }
                in 4..6 -> {
                    greet.setText(R.string.greet_neutral)
                }
                in 7..9 -> {
                    greet.setText(R.string.greet_good)
                }
                else -> {
                    greet.setText(R.string.greet_goodly)
                }
            }
        } else {
            greet.setText(R.string.greet_fast)
        }

        binding.resFrPos.text = positive.toString()
        binding.resFrNeg.text = negative.toString()

        with(binding) {
            bMenu.setOnClickListener {
                findNavController().popBackStack(R.id.rootFragment, false)
            }
            when (mode) {
                "fast" -> {
                    bLevel.visibility = View.GONE
                }
                else -> {
                    bLevel.setOnClickListener {
                        findNavController().popBackStack(R.id.levelFragment, false)
                    }
                }
            }


//               bAgain.setOnClickListener {
//                   //findNavController().popBackStack(R.id.workFragment,false)
//              findNavController().navigateUp()
//               }
        }
    }
}