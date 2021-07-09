package micros_leader.george.nosileutikosfakelos.Listeners;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VoiceListener implements RecognitionListener {

    ProgressDialog progressDialog;

    boolean isEndOfSpeech = false;
    private Activity activity;
    private TextView textView;
    private EditText editText;

    public VoiceListener(Activity activity, TextView textView){
        this.activity = activity;
        this.textView = textView;

    }

    public VoiceListener(Activity activity, EditText editText){
        this.activity = activity;
        this.editText = editText;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
      //  Log.d("Speech", "ReadyForSpeech");
    }

    @Override
    public void onBeginningOfSpeech() {
      //  Log.d("Speech", "beginSpeech");

    }

    @Override
    public void onRmsChanged(float rmsdB) {
       // Log.d("Speech", "onrms");

    }

    @Override
    public void onBufferReceived(byte[] buffer) {
    //   Log.d("Speech", "onbuffer");

    }

    @Override
    public void onEndOfSpeech() {
        isEndOfSpeech = true;

    }

    @Override
    public void onError(int error) {
      //  Log.e("voice", "error " + error);
        if (!isEndOfSpeech) {
            return;
        }
        Toast.makeText(activity.getApplicationContext(), "Δοκιμάστε ξανά", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String word = (String) data.get(data.size() - 1);
        if (textView != null)
            textView.setText(word);
        else
            editText.setText(word);

        progressDialog.dismiss();


    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        ArrayList data = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String word = (String) data.get(data.size() - 1);
        if (textView != null)
            textView.setText(word);
        else
            editText.setText(word);    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}