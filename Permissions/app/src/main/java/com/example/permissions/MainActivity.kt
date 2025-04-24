package com.example.permissions

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    //single permission
    private val cameraContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            if (it) {
                //true
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                //false
                showPermissionDialog(
                    "Camera Permission",
                    "This permission is required to update your profile picture."
                ) {
                    // Deny action code here
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }

    //single permission
    private val contactContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            if (it) {
                //true
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                //false

                showPermissionDialog(
                    "Contact Permission",
                    "This permission is required to update your profile picture."
                ) {
                    // Deny action code here
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }


    //    private fun showPermissionDialog(tittle: String, message: String) {
//        AlertDialog.Builder(this).apply {
//            setTitle(tittle)
//            setMessage(message)
//                .setPositiveButton("Setting",
//                    DialogInterface.OnClickListener { dialogInterface, i ->
//                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                        intent.data = Uri.fromParts("package", packageName, null)
//                        startActivity(intent)
//                    })
//                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
//
//                }).show()
//        }
//    }
    private fun showPermissionDialog(tittle: String, message: String, denyAction: () -> Unit) {
        val alertDialog = AlertDialog.Builder(this).apply {
            setTitle(tittle)
            setMessage(message)
                .setPositiveButton("Setting",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.fromParts("package", packageName, null)
                        startActivity(intent)
                    })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                    denyAction()
                })
        }.create()

        alertDialog.setOnCancelListener {
            // Handle cancel (clicking outside the dialog) action here
            // For example, you can do nothing or show a message
        }

        alertDialog.show()
    }


    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCamera.setOnClickListener {
            cameraContract.launch(android.Manifest.permission.CAMERA)

        }
        binding.btnContact.setOnClickListener {
            contactContract.launch(android.Manifest.permission.READ_CONTACTS)
        }
        binding.btnPermission.setOnClickListener {
            val permission = arrayOf(
                android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.SET_ALARM,
                android.Manifest.permission.ACCEPT_HANDOVER
            )
            multiplePermission.launch(permission)
        }

        binding.btnCamera1.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    90
                )
            } else {
                // यहां अनुमति की आवश्यकता होने पर काम करने वाले कोड को डालें...
            }
        }



        binding.btnNavigate.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top)
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
//        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top)
        overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom) 
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    //Multiple Permission
    private val multiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { result ->
            var areAllGranted = true
            for (isGranted in result.values) {
                Log.d(TAG, "permission granted : $isGranted")
                areAllGranted = areAllGranted && isGranted
            }
            if (areAllGranted) {
                Toast.makeText(this, "All Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "permission denied : ")
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
            }
        }
}