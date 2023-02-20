package dev.maharshpatel.scratchcard.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.maharshpatel.scratchcard.R
import dev.maharshpatel.scratchcard.data.ScratchCard
import dev.maharshpatel.scratchcard.databinding.ScratchableItemBinding
import dev.maharshpatel.scratchcard.utils.dp

class ScratchResultAdapter(private val listener: (ScratchCard) -> Unit) :
    ListAdapter<ScratchCard, ScratchResultAdapter.ScratchResultViewHolder>(ScratchCard.diff) {


    inner class ScratchResultViewHolder(private val binding: ScratchableItemBinding) :
        ViewHolder(binding.root) {

        init {
            val context = binding.root.context
            val width = context.resources.displayMetrics.widthPixels / 2

            binding.root.layoutParams = ViewGroup.LayoutParams(width, width)



            binding.root.setPadding(context.dp(8f))

            val layoutParams = binding.cardView.layoutParams as ConstraintLayout.LayoutParams
            val dp4 = context.dp(4f)
            layoutParams.marginEnd = dp4
            layoutParams.marginStart = dp4
            layoutParams.topMargin = dp4
            layoutParams.bottomMargin = dp4

            binding.cardView.layoutParams = layoutParams

            binding.scratchView.visibility = View.GONE

            binding.scratchCardViewLayout.setOnClickListener {
                val currentPosition = absoluteAdapterPosition
                if (currentPosition != RecyclerView.NO_POSITION) {
                    listener(getItem(currentPosition))
                }
            }
        }


        fun with(card: ScratchCard) {

            binding.currencyTextView.text = "${card.value}₹"
            if (card.isScratched) {
                binding.scratchCardViewLayout.foreground = null
            } else {
                binding.scratchCardViewLayout.foreground = ContextCompat.getDrawable(
                    binding.root.context, R.drawable.img_scratch
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScratchResultViewHolder {
        return ScratchResultViewHolder(
            ScratchableItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ScratchResultViewHolder, position: Int) {
        holder.with(getItem(position))
    }
}