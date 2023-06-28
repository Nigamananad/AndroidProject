package com.example.dialogboxandcustomdialogbox

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dialogboxandcustomdialogbox.databinding.LayoutCustomDialogBinding

class MainActivity : AppCompatActivity() {

    var color = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClicked(view: View) {
        when (view.id) {
            R.id.btn_dialog -> {
//                showSimpleAlertDialog()
                showAlertDialog()
            }
            R.id.btn_single_dialog -> {
                //showSingleChoiceAlertDialog()
                showSingleChoiceDialog()
            }
            R.id.btn_multi_choice_dialog -> {
                showMultiChoiceDialog()
            }
            R.id.btn_custom_dialog -> {
                showCustomDialog()
            }
        }
    }

    private fun showCustomDialog() {


        var bind = LayoutCustomDialogBinding.inflate(layoutInflater)

        var builder = AlertDialog.Builder(this)
            .setView(bind.root)

        var dialog = builder.create()

        dialog.show()
    }

    private fun showMultiChoiceDialog() {
        var colorArray = arrayOf("Red", "Black", "Blue", "Green", "Yellow")
        //var checkedArray = booleanArrayOf(true, false, false, true, false)

        var checkedItemList = mutableListOf<String>()

        var builder = AlertDialog.Builder(this)
            .setTitle("Select colors")
            .setMultiChoiceItems(
                colorArray,
                null,
                DialogInterface.OnMultiChoiceClickListener { dialogInterface, index, status ->

                    var color = colorArray[index]

                    if (status) {
                        checkedItemList.add(color)
                    } else {
                        checkedItemList.remove(color)
                    }
                    Toast.makeText(this, "${checkedItemList.toString()}", Toast.LENGTH_SHORT).show()
                })
        var dialog = builder.create()
        dialog.show()

    }

    private fun showSingleChoiceDialog() {

        var colorArray = arrayOf("Red", "Black", "Blue", "Green", "Yellow")

        var builder = AlertDialog.Builder(this)
            .setTitle("Pick a color")
            .setItems(colorArray, DialogInterface.OnClickListener { dialogInterface, index ->
                //Toast.makeText(this, "$index", Toast.LENGTH_SHORT).show()
                var color = colorArray[index]
                Toast.makeText(this, "$color", Toast.LENGTH_SHORT).show()
            })


        var dialog = builder.create()
        dialog.show()
    }

    private fun showAlertDialog() {
        var builder = AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("are you sure you want to exit from this application?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                // this block is execute when user click on logout button
//                Toast.makeText(this, "Logout button clicked", Toast.LENGTH_SHORT).show()
                finish()
            }).setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                // this block is execute when user click on cancel button
//                Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show()
            })

        var dialog = builder.create()
        dialog.show()
    }

//    private fun showSingleChoiceAlertDialog() {
//
//        var colorArray = arrayOf("Red", "Green", "Blue", "White", "Orange", "Yellow")
//
//        var builder = AlertDialog.Builder(this)
//            .setTitle("Pick Color")
//            .setItems(colorArray, DialogInterface.OnClickListener { dialogInterface, index ->
//                color = colorArray[index]
//                Toast.makeText(this, "$color", Toast.LENGTH_SHORT).show()
//            })
//        builder.show()
//    }

//    private fun showSimpleAlertDialog() {
//        var builder = AlertDialog.Builder(this)
//            .setTitle("Logout")
//            .setIcon(R.mipmap.ic_launcher)
//            .setMessage("Are you sure you want to logout from this application?")
//            .setPositiveButton("Logout", DialogInterface.OnClickListener { dialogInterface, i ->
//                // this block will execute when user click on dialog's positive button
//                Toast.makeText(this, "Logout button clicked", Toast.LENGTH_SHORT).show()
//
//            }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
//                // this block will execute when user click on dialog's negative button
//                Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show()
//            })
//        builder.show()
//
//    }

}
