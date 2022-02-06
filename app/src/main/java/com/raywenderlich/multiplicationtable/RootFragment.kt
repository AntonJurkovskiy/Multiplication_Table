package com.raywenderlich.multiplicationtable

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.raywenderlich.multiplicationtable.databinding.RootFragmentBinding

class RootFragment : Fragment(R.layout.root_fragment) {

    private lateinit var binding: RootFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG", " RootFragment onViewCreated start!")
        super.onViewCreated(view, savedInstanceState)
        binding = RootFragmentBinding.bind(view)
        with(binding) {
            bMainTable.setOnClickListener {
                goLevelFragment("table")
            }

            bMainFast.setOnClickListener {
                goFast("fast")
            }
            binding.bLearnTable.setOnClickListener {
                findNavController().navigate(R.id.action_rootFragment_to_tableActivity)
            }
        }
    }

    private fun goLevelFragment(mode: String) {
        findNavController().navigate(
            R.id.action_rootFragment_to_levelFragment,
            bundleOf(LevelFragment.ARG_MODE_L to mode)
        )
    }

    private fun goFast(mode: String) {
        findNavController().navigate(
            R.id.action_rootFragment_to_workFragment,
            bundleOf(WorkFragment.ARG_MODE_W to mode)
        )
    }


}