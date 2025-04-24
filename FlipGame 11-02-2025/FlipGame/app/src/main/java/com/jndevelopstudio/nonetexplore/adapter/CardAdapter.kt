package com.jndevelopstudio.nonetexplore.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jndevelopstudio.nonetexplore.R

class CardAdapter(
    private val cardImages: List<Int>,
    private var initialShow: Boolean,
    private val recyclerView: RecyclerView,
    private val onAllCardsMatched: () -> Unit
) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageFront: ImageView = itemView.findViewById(R.id.imageFront)
        val imageBack: ImageView = itemView.findViewById(R.id.imageBack)
    }

    private var firstCard: CardViewHolder? = null
    private var secondCard: CardViewHolder? = null
    var matchedPairs = 0

    private var isProcessing = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val imageRes = cardImages[position]
//        holder.imageFront.setImageResource(imageRes)
        if (initialShow) {
            // Initially, show the front of the card
            holder.imageBack.visibility = View.GONE
            holder.imageFront.visibility = View.VISIBLE
            holder.imageFront.setImageResource(imageRes)
        } else {
            // Otherwise, hide the front and show the back
            holder.imageBack.visibility = View.VISIBLE
            holder.imageFront.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            if (!isProcessing) {
                flipCard(holder, position)
            }
        }
    }

    override fun getItemCount(): Int = cardImages.size

    private fun flipCard(holder: CardViewHolder, position: Int) {
        if (holder.imageFront.visibility == View.GONE) {
            // Flip from back to front
            holder.imageBack.animate()
                .rotationY(90f)
                .setDuration(150)
                .withEndAction {
                    holder.imageBack.visibility = View.GONE
                    holder.imageFront.visibility = View.VISIBLE
                    holder.imageFront.rotationY = -90f
                    holder.imageFront.animate().rotationY(0f).setDuration(150).start()
                }.start()

            handleCardFlip(holder, position)
        }
    }

    private fun handleCardFlip(holder: CardViewHolder, position: Int) {
        if (firstCard == null) {
            // First card flipped
            firstCard = holder
        } else if (secondCard == null) {
            // Second card flipped
            secondCard = holder
            isProcessing = true
            if (cardImages[firstCard!!.adapterPosition] == cardImages[secondCard!!.adapterPosition]) {
                // Cards match, keep them open
                matchedPairs++
                firstCard = null
                secondCard = null
                isProcessing = false
                if (matchedPairs == cardImages.size / 2) {
                    onAllCardsMatched() // Trigger callback when all pairs are matched
                }
            } else {
                // Cards do not match, flip them back after a delay
                Handler(Looper.getMainLooper()).postDelayed({
                    flipBackCards()
                }, 1000)
            }
        }
    }

    private fun flipBackCards() {
        firstCard?.let { flipCardBack(it) }
        secondCard?.let { flipCardBack(it) }
        firstCard = null
        secondCard = null
        isProcessing = false
    }

    private fun flipCardBack(holder: CardViewHolder) {
        holder.imageFront.animate()
            .rotationY(90f)
            .setDuration(150)
            .withEndAction {
                holder.imageFront.visibility = View.GONE
                holder.imageBack.visibility = View.VISIBLE
                holder.imageBack.rotationY = -90f
                holder.imageBack.animate().rotationY(0f).setDuration(150).start()
            }.start()
    }

    fun flipAllCards() {
        initialShow = false
        for (i in 0 until itemCount) {
            val holder = recyclerView.findViewHolderForAdapterPosition(i) as? CardViewHolder
            holder?.let { flipCardBack(it) }
        }
    }

}
