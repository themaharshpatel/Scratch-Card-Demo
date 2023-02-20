package dev.maharshpatel.scratchcard.data

import androidx.recyclerview.widget.DiffUtil

data class ScratchCard(
    val id: Int,
    val value: Int,
    val isScratched: Boolean
) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<ScratchCard>() {
            override fun areItemsTheSame(oldItem: ScratchCard, newItem: ScratchCard): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ScratchCard, newItem: ScratchCard): Boolean {
                return oldItem == newItem
            }

        }
    }
}
