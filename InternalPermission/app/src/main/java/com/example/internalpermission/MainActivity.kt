package com.example.internalpermission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.internalpermission.databinding.ActivityMainBinding
import java.io.*
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val FILE_NAME = "sample.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            var message = binding.edtInput.text.toString().trim()
            saveFileToInternalStorage(message)
        }

        binding.btnRead.setOnClickListener {
            readDataFromFile()
        }
    }

    private fun readDataFromFile() {

        try {

            var file = File(filesDir, FILE_NAME)
            println("file path:${file.absolutePath}")
        var fin = FileInputStream(file)
        var reader = InputStreamReader(fin)
        var buffer = BufferedReader(reader)
            var string = java.lang.StringBuilder()
            val sc = Scanner(file)
            while (sc.hasNextLine()) {
                string.append(sc.nextLine())
            }

            Toast.makeText(this, "${string.toString()}", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun saveFileToInternalStorage(message: String) {
        println("fileDir:${filesDir}")
        println("cacheDir:${cacheDir}")
        println("externalFileDir:${getExternalFilesDir(null)}")
        println("externalCacheDir:${externalCacheDir}")

        //save file to file directory
        try {
            var file = File(filesDir, FILE_NAME)
            println("file path:${file.absolutePath}")
            var fout = FileOutputStream(file)
            fout.write(message.toByteArray())
            fout.close()
            Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}