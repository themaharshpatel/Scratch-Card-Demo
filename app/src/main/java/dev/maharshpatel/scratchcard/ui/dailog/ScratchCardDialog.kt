package dev.maharshpatel.scratchcard.ui.dailog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anupkumarpanwar.scratchview.ScratchView
import dev.maharshpatel.scratchcard.data.ScratchCard
import dev.maharshpatel.scratchcard.databinding.ScratchableItemBinding
import dev.maharshpatel.scratchcard.utils.dp

class ScratchCardDialog(
    context: Context, private val scratchCard: ScratchCard, val listener: (ScratchCard) -> Unit
) : Dialog(context) {

    private val binding: ScratchableItemBinding

    init {
        binding = ScratchableItemBinding.inflate(LayoutInflater.from(context))
        binding.currencyTextView.text = "${scratchCard.value}₹"
        if (scratchCard.isScratched) {
            binding.scratchView.visibility = View.GONE
        } else {
            binding.scratchView.visibility = View.VISIBLE
        }
        binding.scratchView.setStrokeWidth(20)
        binding.scratchView.setRevealListener(object : ScratchView.IRevealListener {
            override fun onRevealed(scratchView: ScratchView?) {
                scratchView?.reveal()
                listener(scratchCard)

            }

            override fun onRevealPercentChangedListener(scratchView: ScratchView?, percent: Float) {
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val width = context.resources.displayMetrics.widthPixels - context.dp(32f)
        window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}