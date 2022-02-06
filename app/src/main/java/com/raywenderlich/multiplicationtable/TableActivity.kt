package com.raywenderlich.multiplicationtable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.raywenderlich.multiplicationtable.databinding.ActivityTableBinding
import com.raywenderlich.multiplicationtable.table_adapter.PagerAdapter

class TableActivity : AppCompatActivity() {
    lateinit var binding: ActivityTableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", " TableActivity onCreate start!")
        super.onCreate(savedInstanceState)
        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initial()
    }

    private fun initial() {
        binding.viewPager.adapter = PagerAdapter(this)
        binding.tabLayout.tabIconTint = null
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setIcon(R.drawable.icon_2)

                }
                1 -> {
                    tab.setIcon(R.drawable.icon_3)
                    tab?.icon?.alpha = 50
                }
                2 -> {
                    tab.setIcon(R.drawable.icon_4)
                    tab?.icon?.alpha = 50
                }
                3 -> {
                    tab.setIcon(R.drawable.icon_5)
                    tab?.icon?.alpha = 50
                }
                4 -> {
                    tab.setIcon(R.drawable.icon_6)
                    tab?.icon?.alpha = 50
                }
                5 -> {
                    tab.setIcon(R.drawable.icon_7)
                    tab?.icon?.alpha = 50
                }
                6 -> {
                    tab.setIcon(R.drawable.icon_8)
                    tab?.icon?.alpha = 50
                }
                7 -> {
                    tab.setIcon(R.drawable.icon_9)
                    tab?.icon?.alpha = 50
                }

            }
        }.attach()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab?.icon?.alpha = 250
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.alpha = 50
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.icon?.alpha = 0
            }

        })


    }

    override fun onPause() {
        Log.d("TAG", " TableActivity onPause start!")
        super.onPause()
    }

    override fun onStop() {
        Log.d("TAG", " TableActivity onStop start!")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("TAG", " TableActivity onDestroy start!")
        super.onDestroy()
    }
}