package com.raywenderlich.multiplicationtable

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.raywenderlich.multiplicationtable.databinding.LevelFragmentBinding

class LevelFragment : Fragment(R.layout.level_fragment) {
    companion object {
        const val ARG_MODE_L = "mode"

    }

    lateinit var binding: LevelFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", " Level Fragment onViewCreated start!")
        super.onViewCreated(view, savedInstanceState)
        val modeInput = requireArguments().getString(ARG_MODE_L)
        binding = LevelFragmentBinding.bind(view)
        //binding.bLevel3.text = modeInput
        with(binding) {
            bLevel2.setOnClickListener {
                levelSelection(2, modeInput.toString())

            }
            bLevel3.setOnClickListener {
                levelSelection(3, modeInput.toString())

            }
            bLevel4.setOnClickListener {
                levelSelection(4, modeInput.toString())

            }
            bLevel5.setOnClickListener {
                levelSelection(5, modeInput.toString())

            }
            bLevel6.setOnClickListener {
                levelSelection(6, modeInput.toString())

            }
            bLevel7.setOnClickListener {
                levelSelection(7, modeInput.toString())

            }
            bLevel8.setOnClickListener {
                levelSelection(8, modeInput.toString())

            }
            bLevel9.setOnClickListener {
                levelSelection(9, modeInput.toString())

            }
            bLevel10.setOnClickListener {
                levelSelection(10, modeInput.toString())

            }
            bLevelBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

    }


    private fun levelSelection(level: Int, mode: String) {
        findNavController().navigate(
            R.id.action_levelFragment_to_workFragment,
            bundleOf(WorkFragment.ARG_NUMBER to level, WorkFragment.ARG_MODE_W to mode)
        )
    }

    override fun onStop() {
        Log.d("TAG", " Level Fragment onStop start!")
        super.onStop()
    }

    override fun onPause() {
        Log.d("TAG", " Level Fragment onPause start!")
        super.onPause()
    }

    override fun onDestroy() {

        Log.d("TAG", " Level Fragment onDestroy start!")
        super.onDestroy()
    }

}



