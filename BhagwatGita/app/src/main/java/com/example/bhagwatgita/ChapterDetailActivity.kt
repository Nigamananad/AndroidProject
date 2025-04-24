package com.example.bhagwatgita

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bhagwatgita.model.Chapter

class ChapterDetailActivity : AppCompatActivity() {
    private var isEngExpanded = false
    private var isHindiExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_detail)
        val chapter = intent.getSerializableExtra("chapter") as? Chapter


        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvVerses = findViewById<TextView>(R.id.tvVerses)
        val tvEngSummary = findViewById<TextView>(R.id.tvEngSummary)
        val tvHindiSummary = findViewById<TextView>(R.id.tvHindiSummary)

        chapter?.let {
            tvTitle.text = "Chapter ${it.chapterNumber}: ${it.name} (${it.nameTranslated})"
            tvVerses.text = "Verses: ${it.versesCount}"

            tvEngSummary.text = it.chapterSummary ?: "No English summary available"
            tvHindiSummary.text = it.chapterSummaryHindi ?: "कोई हिंदी सारांश उपलब्ध नहीं है"
        }

        // 🧠 English summary click to expand/collapse
        tvEngSummary.setOnClickListener {
            isEngExpanded = !isEngExpanded
            tvEngSummary.maxLines = if (isEngExpanded) Int.MAX_VALUE else 3
        }

        // 🧠 Hindi summary click to expand/collapse
        tvHindiSummary.setOnClickListener {
            isHindiExpanded = !isHindiExpanded
            tvHindiSummary.maxLines = if (isHindiExpanded) Int.MAX_VALUE else 3
        }
    }
}