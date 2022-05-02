package com.google.firebase.appdistribution.internal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.firebase.appdistribution.R

class FeedbackActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_feedback)
    val button: Button = findViewById(R.id.feedback_button)
    button.setOnClickListener {

    }
  }
}