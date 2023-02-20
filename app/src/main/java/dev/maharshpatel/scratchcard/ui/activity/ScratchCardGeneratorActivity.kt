package dev.maharshpatel.scratchcard.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import dev.maharshpatel.scratchcard.databinding.ActivityScratchGeneratorBinding

class ScratchCardGeneratorActivity : AppCompatActivity() {

    private var binding: ActivityScratchGeneratorBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScratchGeneratorBinding.inflate(layoutInflater).apply {
            setContentView(root)
            nextButton.setOnClickListener {
                val number = try {
                    scratchEditText.text?.toString()?.toInt()
                } catch (_: NumberFormatException) {
                    0
                } ?: 0

                if (number == 0) {
                    Snackbar.make(root, "Enter a number", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val intent = Intent(
                    this@ScratchCardGeneratorActivity,
                    ScratchResultActivity::class.java
                )
                intent.putExtra("count", number)
                startActivity(intent)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}