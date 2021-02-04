package com.shobanasha.roomdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shobanasha.roomdemo.database.data.SubscriberDatabase
import com.shobanasha.roomdemo.database.repository.SubscriberRepository
import com.shobanasha.roomdemo.databinding.ActivityMainBinding
import com.shobanasha.roomdemo.viewmodel.SubscriberViewModel
import com.shobanasha.roomdemo.viewmodel.SubscriberViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        displaySubscriberList()

    }

    private fun displaySubscriberList() {
        subscriberViewModel.subscriber.observe(this, Observer {
            Log.i("MYTAG", it.toString())
        })
    }
}
