package com.example.aman;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    private LinearLayout parentRelativeLayout;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private String keeper ="";
    private Uri notification;
    private Ringtone r;
    private int Index;
    private TextToSpeech mTTS;
    private EditText mEditText;

    //private SeekBar mSeekBarPitch;
    //private SeekBar mSeekBarSpeed;
    private Button mButtonSpeak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Index=getRandomInteger(10, 1);

        /// mButtonSpeak = findViewById(R.id.button_speak);
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        speak(Index);
                        //mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        checkVoiceCommandPermission();
        parentRelativeLayout=findViewById(R.id.parentRelativeLayout);
        speechRecognizer=SpeechRecognizer.createSpeechRecognizer(MainActivity.this);
        speechRecognizerIntent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.setRecognitionListener(new RecognitionListener()
        {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results)
            {////boolean cheak;
                notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                int contant;
                int AnswersArray[]={13,33,15,55,4,110,48,30,13,9};
                ArrayList<String> matchesFound=results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                if(matchesFound!=null){
                    keeper=matchesFound.get(0);
                }
                contant=Integer.parseInt(keeper);
                if(AnswersArray[Index-1]==contant){
APK(s) generated successfully for 1 module: Module 'app': locate or analyze the APK.
                    Toast.makeText(MainActivity.this,"The driver says  ("+keeper+") which a correct answer ",Toast.LENGTH_LONG).show();
                    MainActivity.super.onDestroy();
                }else{
                    Toast.makeText(MainActivity.this,"The driver says  ("+keeper+") but this is an incorrect Answer! ",Toast.LENGTH_LONG).show();
                    r.play();
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });


        parentRelativeLayout.setOnTouchListener(new View.OnTouchListener()
                                                {
                                                    @Override
                                                    public boolean onTouch(View v, MotionEvent motionEvent)
                                                    {
                                                        switch (motionEvent.getAction())
                                                        {
                                                            case MotionEvent.ACTION_DOWN:
                                                                speechRecognizer.startListening(speechRecognizerIntent);
                                                                break;
                                                            case MotionEvent.ACTION_UP:
                                                                break;

                                                        }
                                                        return false;
                                                    }
                                                }

        );
        mEditText = (EditText) findViewById(R.id.edit_text);
//        mSeekBarPitch = findViewById(R.id.seek_bar_pitch);
//        mSeekBarSpeed = findViewById(R.id.seek_bar_speed);
//        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                speak();
//            }
//        });
    }

    private void speak(int randomInteger) {
//        int randomInteger=getRandomInteger(10, 1);
//        Index=randomInteger;
        String equation=getEquation(randomInteger);
        String text;
        String intro="How many ";
        text=intro+equation;
        mEditText.setText(equation);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }
    public static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }
    public static String getEquation(int equationNumber){
        String equation;// switch statement with int data type
        switch (equationNumber) {
            case 1://13
                equation= "8 + 5";
                break;
            case 2:
                equation = "20 + 13";
                break;
            case 3:
                equation = "7 + 8";
                break;
            case 4:
                equation = "50 + 5";
                break;
            case 5:///4
                equation = "3 + 1";
                break;
            case 6:///110
                equation = "99 + 11";
                break;
            case 7:///48
                equation = "34 + 13";
                break;
            case 8:///30
                equation="22 + 8";
                break;
            case 9:///13
                equation="11 + 2";
                break;
            case 10:///9
                equation="4 + 5";
                break;
            default://60
                equation = "52 + 8";
                break;
        }
        //System.out.println();
        return equation;
    }
    private void checkVoiceCommandPermission(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(!(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED))
            {
                Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName()));
                startActivity(intent);
                finish();
            }
        }

    }
//    public static boolean cheakAnswer(String driverAnswer,int indexOfCorrectAnswer) {
//        int AnswersArray[]={13,33,15,55,4,110,48,30,13,9};
//        int keeper=Integer.parseInt(driverAnswer);
//        if(AnswersArray[indexOfCorrectAnswer]==keeper){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }4
}
