package com.loop.ideas.apps.androidblog01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.loop.ideas.apps.androidblog01.BR
import com.loop.ideas.apps.androidblog01.R
import com.loop.ideas.apps.androidblog01.databinding.ActivityMainBinding
import com.loop.ideas.apps.androidblog01.extensions.onSearchClicked
import com.loop.ideas.apps.androidblog01.extensions.openTwitter
import com.loop.ideas.apps.androidblog01.manual_di.AppInjector
import com.loop.ideas.apps.androidblog01.presentation.adapter.MastersAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter = MastersAdapter(listOf()) { master ->
        openTwitter(master.twitterId)
    }

    private val vmFactory by lazy {
        MainViewModelFactory(
            AppInjector.provideGetUserUseCase(),
            AppInjector.provideGetUsersUseCase(),
            AppInjector.providePresentationMapper()
        )
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.mainViewModel, mainViewModel)
        /*
         * If u are on fragments use "viewLifecycleOwner" instead of this.
         */
        binding.lifecycleOwner = this
        initListeners()
        observe()
    }

    private fun initListeners() {
        binding.etMasterId.onSearchClicked {
            mainViewModel.onFindMasterClicked(it)
        }
        binding.btnGetAllMasters.setOnClickListener {
            mainViewModel.onGetAllMasterButtonClicked()
        }
        binding.layoutMaster.masterContainer.setOnClickListener {
            openTwitter(mainViewModel.master.value?.twitterId)
        }

        binding.rvMasters.layoutManager = LinearLayoutManager(this)
        binding.rvMasters.adapter = adapter
    }

    private fun observe() {
        /*
         * If u are on fragments use "viewLifecycleOwner" instead of this.
         */
        mainViewModel.masterName.observe(this) {
            Toast.makeText(this.applicationContext, it, Toast.LENGTH_LONG).show()
        }

        mainViewModel.masters.observe(this) {
            adapter.addItems(it)
        }
    }
}