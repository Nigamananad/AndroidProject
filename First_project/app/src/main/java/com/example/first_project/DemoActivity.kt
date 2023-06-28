package com.example.first_project

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.first_project.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    lateinit var binding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Long press (Context Menu)
       registerForContextMenu(binding.btnClick)


        //popup
        binding.btnClicked.setOnClickListener {
            var popup=PopupMenu(this,it)
            menuInflater.inflate(R.menu.menu_item,popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.it_Profile -> {
                var intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.it_setting -> {
                true
            }
            R.id.it_logout -> {
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_item, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.it_Profile -> {
                var intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.it_setting -> {
                true
            }
            R.id.it_logout -> {
                true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    override fun onMenuItemClick(item : MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.it_Profile -> {
                var intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.it_setting -> {
                true
            }
            R.id.it_logout -> {
                showSimpleDialog()
                true
            }
            else -> false
        }
    }

    fun onClicked(item: MenuItem) {

        when(item.itemId){
            R.id.it_logout->{
                showSimpleDialog()
            }
        }
    }

    private fun showSimpleDialog() {
        var builder=AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("are you sure you want to exit from this application?")

            .setPositiveButton("Logout", DialogInterface.OnClickListener { dialogInterface, i ->

                finish()
                /*Toast.makeText(
                    this,
                    "Logout Button Clicked",
                    Toast.LENGTH_SHORT
                ).show() */})


            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(
                    this,
                    "Cancel Button Clicked",
                    Toast.LENGTH_SHORT
                ).show() })

        var dialog=builder.create()
        dialog.show()

    }


}