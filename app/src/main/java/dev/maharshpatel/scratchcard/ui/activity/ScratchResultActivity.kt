package dev.maharshpatel.scratchcard.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import dev.maharshpatel.scratchcard.databinding.ActivityScratchResultBinding
import dev.maharshpatel.scratchcard.ui.dailog.ScratchCardDialog
import kotlinx.coroutines.launch

class ScratchResultActivity : AppCompatActivity() {

    private var binding: ActivityScratchResultBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<ScratchResultViewModel>()

        binding = ActivityScratchResultBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val count = intent.extras?.getInt("count", 0) ?: 0

            viewModel.generateCards(count)

            recyclerView.layoutManager = GridLayoutManager(this@ScratchResultActivity, 2)

            val adapter = ScratchResultAdapter { scratchCard ->
                ScratchCardDialog(
                    this@ScratchResultActivity, scratchCard
                ) {
                    viewModel.cardScratched(it)
                }.show()
            }

            recyclerView.adapter = adapter

            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.scratchCards.collect {
                        adapter.submitList(it)
                    }
                }
            }


        }

    }
}