package com.mrclrchtr.android.example.voz;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mrclrchtr.android.example.R;

import java.util.ArrayList;
/**
 * Created by Miguel A Tunubala on 28/01/2018.
 */


public class Lector extends AppCompatActivity {

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    private TextView txtviewSpeech2Text;
    private EditText txtTextoManual;
    private String TextoManual;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inicio);

        txtviewSpeech2Text = findViewById(R.id.txt_view_speech_to_text);
        txtTextoManual = (EditText) findViewById(R.id.txtTextoManual);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {
                    // Establece el habla en una lista de arreglos
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    // El discurso más preciso está en la posición 0
                    String strSpeech2Text = speech.get(0);
                    txtviewSpeech2Text.setText(strSpeech2Text);

                    // Abrir web con el resultado en google academic
                    setContentView(R.layout.activity_web_view);
                    webView = (WebView) findViewById(R.id.webView);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl("https://scholar.google.es/scholar?q="+strSpeech2Text);
                }

                break;
            default:
                break;
        }
    }

    public void onClickImgBtnHablar_(View v) {
        // Discurso de reconocimiento de voz
        Intent intentActionRecognizeSpeech = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Establece el idioma, predeterminado Ingles Ussa
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "us-US");

        // Inicia la actividad de reconocimiento de voz
        try {
            startActivityForResult(intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "¡Opps! Su dispositivo no es compatible con el reconocimiento de voz.", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickImgBtnBuscar(View v) {
        TextoManual = txtTextoManual.getText().toString();
        // Abrir web con el resultado en google academic
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://scholar.google.es/scholar?q="+TextoManual);
    }

}