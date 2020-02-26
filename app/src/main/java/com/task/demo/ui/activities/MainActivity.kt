package com.task.demo.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.task.demo.R
import com.task.demo.ui.fragments.FilterFragment
import com.task.demo.ui.fragments.NobelPrizeFragment
const val EXTRAS_LOAD_FRAGMENT = "fragment_load"

class MainActivity : AppCompatActivity() {
    companion object {
        fun navigateToCommonActivity(appCompact: AppCompatActivity, item: Int) {
            val intent = Intent(appCompact, MainActivity::class.java)
            intent.putExtra(EXTRAS_LOAD_FRAGMENT, item)
            appCompact.startActivity(intent)
        }
    }

    val list: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Prizes"

        loadFragmentWithoutBack(NobelPrizeFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun loadFragmentWithoutBack(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_filter, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_filter ->{
                list.add("2020")
                list.add("2010")
                list.add("2020")
                list.add("2030")
                list.add("2040")
                loadFragmentChip(FilterFragment.newInstance(list as ArrayList<String>))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //load fragment
    public fun loadFragmentChip(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_in_up,
            R.anim.slide_up,
            R.anim.slide_in_down,
            R.anim.slide_down
        )
        transaction.replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
