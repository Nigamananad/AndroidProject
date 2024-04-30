package com.example.datafetchfromfile

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnImages: Button
    private val REQUEST_CODE_PERMISSIONS = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnImages = findViewById(R.id.btn_images)

        btnImages.setOnClickListener {
            if (checkPermission()) {
                fetchImages()
            } else {
                requestPermission()
            }
        }
    }
    private fun checkPermission(): Boolean {
        val permission = ContextCompat.checkSelfPermission(
            this,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return permission == PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_CODE_PERMISSIONS
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showPermissionSettingsDialog()
                } else {
                }
            } else if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchImages()
            }
        }
    }

    private fun showPermissionSettingsDialog() {
        AlertDialog.Builder(this)
            .setMessage("This app needs permission to access storage. You can grant permission in the app settings.")
            .setPositiveButton("Go to Settings") { _, _ ->
                // Open app settings
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }


    private fun fetchImages() {
        val imagePaths = fetchImagePaths()
        val intent = Intent(this, DisplayImagesActivity::class.java)
        intent.putStringArrayListExtra("imagePaths", ArrayList(imagePaths))
        startActivity(intent)
    }
    private fun fetchImagePaths(): List<String> {
        val imagePaths = mutableListOf<String>()
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        cursor?.use { cursor ->
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            while (cursor.moveToNext()) {
                val filePath = cursor.getString(columnIndex)
                imagePaths.add(filePath)
            }
        }
        return imagePaths
    }

}