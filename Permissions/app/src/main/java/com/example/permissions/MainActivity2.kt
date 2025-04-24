package com.example.permissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity2 : AppCompatActivity() {

    lateinit var demo_btn_camera: Button
    private var isPermissionDeniedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        demo_btn_camera = findViewById(R.id.demo_btn_camera)

        demo_btn_camera.setOnClickListener {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted
            Toast.makeText(this, "Camera Permission Already Granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
                savePermissionStatus(true)
            } else {
                // Permission denied

                if (isPermissionDeniedOnce) {
                    showSettingDialog()
                } else {
                    isPermissionDeniedOnce = true
                    Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun savePermissionStatus(isGranted: Boolean) {
        val sharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(CAMERA_PERMISSION_KEY, isGranted)
        editor.apply()
    }

    private fun showSettingDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Camera Permission Required")
            setMessage("This app needs the Camera permission to function. Please grant the permission in Settings.")
            setPositiveButton("Settings") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
        private const val PREFERENCES_FILE = "com.example.permissions.PREFERENCE_FILE_KEY"
        private const val CAMERA_PERMISSION_KEY = "camera_permission_granted"
    }
}