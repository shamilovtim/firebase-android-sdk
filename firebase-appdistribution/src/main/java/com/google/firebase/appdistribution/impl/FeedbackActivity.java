package com.google.firebase.appdistribution.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;

/** Activity for tester to compose and submit feedback. */
public class FeedbackActivity extends AppCompatActivity {

  private static final String TAG = "FeedbackActivity";

  public static final String RELEASE_NAME_KEY =
      "com.google.firebase.appdistribution.FeedbackActivity.RELEASE_NAME";

  private FirebaseAppDistributionTesterApiClient testerApiClient;
  private String releaseName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    releaseName = getIntent().getStringExtra(RELEASE_NAME_KEY);
    testerApiClient = FirebaseApp.getInstance().get(FirebaseAppDistributionTesterApiClient.class);
    setContentView(R.layout.activity_feedback);
  }

  public void submitFeedback(View view) {
    // Switch UI to "submitting" state
    findViewById(R.id.submitButton).setVisibility(View.INVISIBLE);
    findViewById(R.id.loadingLabel).setVisibility(View.VISIBLE);

    EditText feedbackText = (EditText) findViewById(R.id.feedbackText);
    testerApiClient
        .createFeedback(releaseName, feedbackText.getText().toString())
        // TODO(lkellogg): Upload screenshots here
        .onSuccessTask(testerApiClient::commitFeedback)
        .addOnSuccessListener(
            unused -> {
              LogWrapper.getInstance().i(TAG, "Feedback submitted");
              Toast.makeText(this, "Feedback submitted", Toast.LENGTH_SHORT).show();
              finish();
            })
        .addOnFailureListener(
            e -> {
              LogWrapper.getInstance().e(TAG, "Failed to submit feedback", e);
              finish();
            });
  }
}
