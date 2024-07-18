package com.example.clickweb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @HighlightClick(url = "https://www.netflix.come")
    private lateinit var highlightedText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        highlightedText = findViewById(R.id.highlighted_text)
        setHighlightedText(highlightedText, "Click here to visit example.com")
    }

    private fun setHighlightedText(textView: TextView, text: String) {
        val spannableString = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val url = this@MainActivity::class.java
                    .getDeclaredField("highlightedText")
                    .getAnnotation(HighlightClick::class.java)?.url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}