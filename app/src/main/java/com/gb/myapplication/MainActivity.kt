package com.gb.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.gb.myapplication.blankfragments.CameraFragment
import com.gb.myapplication.blankfragments.DetailFragment
import com.gb.myapplication.blankfragments.SettingsFragment
import com.gb.myapplication.blankfragments.ShoppingFragment
import com.gb.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addOnBackStackChangedListener {

        }

        bottomNavigation()

    }

    private fun bottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_dest -> {
                    launchFragment(MainFragment(), "MainFragment")
                    true
                }
                R.id.detail_dest -> {
                    launchFragment(DetailFragment(), "DetailFragment")
                    true
                }
                R.id.shopping_dest -> {
                    launchFragment(ShoppingFragment(), "ShoppingFragment")
                    true
                }
                R.id.settings_dest -> {
                    launchFragment(SettingsFragment(), "SettingsFragment")
                    true
                }
                R.id.camera_dest -> {
                    launchFragment(CameraFragment(), "CameraFragment")
                    true
                }
                else -> {
                    true
                }
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.home_dest
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragments = supportFragmentManager.fragments
        supportFragmentManager.popBackStack()
        when(fragments.last()){
            is MainFragment->    binding.bottomNavigation.selectedItemId = R.id.home_dest
            is ShoppingFragment ->   binding.bottomNavigation.selectedItemId = R.id.shopping_dest
            is DetailFragment ->   binding.bottomNavigation.selectedItemId = R.id.detail_dest
            is SettingsFragment ->   binding.bottomNavigation.selectedItemId = R.id.settings_dest
            is CameraFragment ->   binding.bottomNavigation.selectedItemId = R.id.camera_dest
            else ->false
        }

    }

    private fun launchFragment(fragment: Fragment, tagFragment: String) {
        val uuid = supportFragmentManager.fragments.size.toString()// если хочется бесконечный бекстек
        val newTagFragment = "$tagFragment$uuid"// если хочется бесконечный бекстек
        if (supportFragmentManager.findFragmentByTag(tagFragment) == null
        ) {
            supportFragmentManager.apply {
                beginTransaction()
                    .addToBackStack(newTagFragment)
                    .add(R.id.nav_host_fragment, fragment,  newTagFragment)
                    .commit()
            }
        } else {
            supportFragmentManager
                .popBackStack(newTagFragment, 0)
        }
    }

}