package com.example.dialogboxandcustomdialogbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.dialogboxandcustomdialogbox.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerForContextMenu(binding.btnContext)

        binding.button3.setOnClickListener {
            var popup = PopupMenu(this, it)
            menuInflater.inflate(R.menu.menu_item, popup.menu)
            popup.show()

            popup.setOnMenuItemClickListener(this)

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
            R.id.op_profile -> {
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.op_settings -> {
                Toast.makeText(this, "Setting item clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.op_logout -> {
                Toast.makeText(this, "Logout item clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.op_profile -> {
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.op_settings -> {
                Toast.makeText(this, "Setting item clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.op_logout -> {
                Toast.makeText(this, "Logout item clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.op_profile -> {
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.op_settings -> {
                Toast.makeText(this, "Setting item clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.op_logout -> {
                Toast.makeText(this, "Logout item clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }

}
