/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.shobanasha.navigationGraph

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.google.android.material.navigation.NavigationView
import com.shobanasha.navigationGraph.databinding.ActivityMainBinding
import com.shobanasha.navigationGraph.databinding.NavHeaderMainBinding
import com.shobanasha.navigationGraph.viewmodel.LettersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  private val navController by lazy { findNavController(R.id.nav_host_fragment) } //1
  private val appBarConfiguration by lazy {
    AppBarConfiguration(
      setOf(
        R.id.sentFragment,
        R.id.inboxFragment
      ), drawerLayout
    )
  } //2

  private var lettersViewModel: LettersViewModel? = null
  private lateinit var headerBinding: NavHeaderMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    try {
      setupDataBinding()
      setSupportActionBar(toolbar)
      setupNavigation()
      setupViewModel()
      setupViews()
    } catch (ex: Exception) {
      ex.printStackTrace()
    }
  }

  override fun onDestroy() {
    lettersViewModel?.apply { closeDb() }
    super.onDestroy()
  }

  private fun setupDataBinding() {
    val activityMainBinding =
      DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    headerBinding = DataBindingUtil.inflate(
      layoutInflater, R.layout.nav_header_main, activityMainBinding.navView, false
    )
    headerBinding.ivEdit.setOnClickListener {
      navController.navigate(R.id.editProfileFragment)

      drawerLayout.closeDrawer(GravityCompat.START)
    }
    activityMainBinding.navView.addHeaderView(headerBinding.root)
  }

  private fun setupNavigation() {
    NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

    NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

    navController.addOnDestinationChangedListener { _, destination, _ ->
      if (destination.id in arrayOf(
          R.id.createLetterFragment,
          R.id.presentationFragment,
          R.id.editProfileFragment
        )
      ) {
        fab.hide()
      } else {
        fab.show()
      }

      if (destination.id == R.id.presentationFragment) {
        toolbar.visibility = View.GONE
      } else {
        toolbar.visibility = View.VISIBLE
      }
    }

  }

  private fun setupViewModel() {
    try {
      val viewModelProvider = ViewModelProvider(
        navController.getViewModelStoreOwner(R.id.nav_graph),
        ViewModelProvider.AndroidViewModelFactory(application)
      )
      lettersViewModel = viewModelProvider.get(LettersViewModel::class.java)
      headerBinding.viewModel = lettersViewModel
      lettersViewModel?.loadProfile()
    } catch (e: IllegalArgumentException) {
      e.printStackTrace()
    }

  }

  private fun setupViews() {
    navView.setNavigationItemSelectedListener(this)

    fab.setOnClickListener {
      navController.navigate(R.id.createLetterFragment)
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {

      R.id.nav_inbox -> {
        navController.popBackStack(R.id.inboxFragment, false)
      }

      R.id.nav_sent -> {
        navController.navigate(R.id.sentFragment)
      }

      R.id.nav_privacy_policy -> {
        navController.navigate(Uri.parse("loveletter://agreement/privacy-policy"))
      }

      R.id.nav_terms_of_service -> {
        navController.navigate(Uri.parse("loveletter://agreement/terms-of-service"))
      }
    }
    drawerLayout.closeDrawer(GravityCompat.START)
    return true
  }
}
