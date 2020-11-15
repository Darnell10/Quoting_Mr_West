package com.example.quoting_mr_west.view

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.quoting_mr_west.R
import com.example.quoting_mr_west.util.PERMISSION_SEND_SMS
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = QuoteFragment()
        // replaceFragment(fragment)

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(nav_drawer, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)


    }


    fun checkSmsPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, android.Manifest.permission.SEND_SMS)
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Send SMS Permission")
                    .setMessage("This app requires access to send an SMS Message.")
                    .setPositiveButton("Ask me") { dialog, which ->
                        requestSmsPermissions()
                    }
                    .setNegativeButton("No") { dialog, which ->
                        notifyDetailFragment(false)
                    }
                    .show()
            } else {
                requestSmsPermissions()
            }
        } else {
            notifyDetailFragment(true)
        }
    }

    private fun requestSmsPermissions() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.SEND_SMS),
            PERMISSION_SEND_SMS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_SEND_SMS -> {
                if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    notifyDetailFragment(true)
                } else {
                    notifyDetailFragment(false)
                }
            }
        }
    }

    private fun notifyDetailFragment(permissionsGranted: Boolean) {
        val activeFragment = fragment.childFragmentManager.primaryNavigationFragment
        if (activeFragment is QuoteFragment) {
            (activeFragment as QuoteFragment).onPermissionResult(permissionsGranted)
        }
    }
}
