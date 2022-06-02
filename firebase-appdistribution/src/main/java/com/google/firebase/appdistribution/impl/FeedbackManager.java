package com.google.firebase.appdistribution.impl;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appdistribution.FirebaseAppDistributionException;

/** Main entrypoint for collecting and sending tester feedback. */
class FeedbackManager {

  private final FirebaseApp firebaseApp;
  private final ApkHashExtractor apkHashExtractor;
  private final FirebaseAppDistributionTesterApiClient testerApiClient;

  FeedbackManager(
      FirebaseApp firebaseApp,
      ApkHashExtractor apkHashExtractor,
      FirebaseAppDistributionTesterApiClient testerApiClient) {
    this.firebaseApp = firebaseApp;
    this.apkHashExtractor = apkHashExtractor;
    this.testerApiClient = testerApiClient;
  }

  /** Show the collect feedback dialog to the tester and send that feedback to the backend. */
  void collectAndSendFeedback() {
    // TODO(lkellogg):
    //  - refactor this, ApkHashExtractor, and ReleaseIdentificationUtils into "ReleaseIdentifier"
    //  - create FeedbackActivity
    //  - use former, and launch latter, from FirebaseAppDistributionImpl
    findRelease()
        .onSuccessTask(
            releaseName ->
                collectFeedbackText()
                    .onSuccessTask(
                        feedbackText -> testerApiClient.createFeedback(releaseName, feedbackText)))
        .onSuccessTask(feedbackName -> testerApiClient.commitFeedback(feedbackName))
        .addOnFailureListener(e -> LogWrapper.getInstance().e("Failed to submit feedback", e));
  }

  private Task<String> findRelease() {
    Context context = firebaseApp.getApplicationContext();

    // Attempt to find release using IAS artifact ID, which identifies app bundle releases
    String iasArtifactId = null;
    try {
      iasArtifactId = ReleaseIdentificationUtils.extractInternalAppSharingArtifactId(context);
    } catch (FirebaseAppDistributionException e) {
      LogWrapper.getInstance()
          .e(
              "Error extracting IAS artifact ID to identify app bundle. Assuming release is an APK.",
              e);
    }
    if (iasArtifactId != null) {
      return testerApiClient.findReleaseUsingIasArtifactId(iasArtifactId);
    }

    // If we can't find an IAS artifact ID, we assume the installed release is an APK
    String apkHash;
    try {
      apkHash = apkHashExtractor.extractApkHash();
    } catch (FirebaseAppDistributionException e) {
      return Tasks.forException(e);
    }
    return testerApiClient.findReleaseUsingApkHash(apkHash);
  }

  // TODO(lkellogg): Actually prompt and collect feedback
  private Task<String> collectFeedbackText() {
    return Tasks.forResult("This app is cool!");
  }
}
