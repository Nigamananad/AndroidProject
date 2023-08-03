package com.example.categoryaddproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.categoryaddproject.adapter.CategoryListAdapter
import com.example.categoryaddproject.database.AppDatabase
import com.example.categoryaddproject.databinding.ActivityMainBinding
import com.example.categoryaddproject.model.Category


class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: CategoryListAdapter
    private var categoryList = mutableListOf<Category>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "category.db")
            .allowMainThreadQueries().build()

//        categoryList.add(Category(1, "Electronics", "asjhdgjasdbjhcgjabjk"))
//        categoryList.add(Category(1, "Electronics", "asjhdgjasdbjhcgjabjk"))
//        categoryList.add(Category(1, "Electronics", "asjhdgjasdbjhcgjabjk"))
//        categoryList.add(Category(1, "Electronics", "asjhdgjasdbjhcgjabjk"))
//        categoryList.add(Category(1, "Electronics", "asjhdgjasdbjhcgjabjk"))


        mAdapter = CategoryListAdapter(this, categoryList)
        binding.recyclerCategory.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerCategory.adapter = mAdapter

        updateList()
    }

    private fun updateList() {
        categoryList = db.categorydao().getAllElement()
        mAdapter.setitem(categoryList)
    }

    override fun onResume() {
        super.onResume()
        if (db != null) {
            updateList()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.it_category -> {
                var intent = Intent(this, AddCategoryActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.it_product -> {
                var intent = Intent(this, AddProductActivity::class.java)
                startActivity(intent)
                true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}