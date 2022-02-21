package com.app.baassignmentapp.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.baassignmentapp.R
import com.app.baassignmentapp.boot.BOOT_ARTS
import com.app.baassignmentapp.databinding.ActivitySplashBinding
import com.app.baassignmentapp.utils.NetworkMonitor
import com.app.baassignmentapp.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()
    private lateinit var activitySplashBinding: ActivitySplashBinding
    private var isNetworkConnected: NetworkMonitor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_splash
        )
        setObserver()
        callBootSequence()
    }

    private fun setObserver() {
        splashViewModel.isShowProgress.observe(this) {
            activitySplashBinding.loader.isIndeterminate = it
        }
        splashViewModel.bootComplete.observe(this) {
            callMainActivity()
        }
    }

    private fun callBootSequence() {
        isNetworkConnected = NetworkMonitor.getInstance(this)
        isNetworkConnected?.observe(this) {
            if (it) {
                splashViewModel.initiateBootCall(BOOT_ARTS)
            } else {
                callMainActivity()
            }
        }
    }

    private fun callMainActivity() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}