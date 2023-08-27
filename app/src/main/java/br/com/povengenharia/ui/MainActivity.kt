package br.com.povengenharia.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar.Tab
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.povengenharia.R
import br.com.povengenharia.data.CarFactory
import br.com.povengenharia.ui.adapter.CarAdapter
import br.com.povengenharia.ui.adapter.TabAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupTabs()
    }

    fun setupView() {

        tabLayout = findViewById(R.id.tl_tab_layout)
        viewPager = findViewById(R.id.vp_view_pager)

    }
    fun setupTabs() {
        val tabAdapter = TabAdapter(this)
        viewPager.adapter = tabAdapter
    }

    fun setupListeners() {

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })
    }
}




