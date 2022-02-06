package com.raywenderlich.multiplicationtable.table_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raywenderlich.multiplicationtable.table_screens.*

class PagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 8
    }



    override fun createFragment(position: Int): Fragment {
          return when(position) {
              0-> ScreenFragment_2()
              1-> ScreenFragment_3()
              2-> ScreenFragment_4()
              3-> ScreenFragment_5()
              4-> ScreenFragment_6()
              5-> ScreenFragment_7()
              6-> ScreenFragment_8()
              else-> ScreenFragment_9()

          }
    }


}