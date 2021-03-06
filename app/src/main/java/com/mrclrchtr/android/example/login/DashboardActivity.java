package com.mrclrchtr.android.example.login;

/**
 * Created by Miguel A Tunubala on 25/01/2018.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mrclrchtr.android.example.R;

public class DashboardActivity extends AppCompatActivity {

    Button LogOut;
    TextView EmailShow;
    String EmailHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Obtener recursos
        LogOut = (Button)findViewById(R.id.button);
        EmailShow = (TextView)findViewById(R.id.EmailShow);

        //Mostrar E-mail
        Intent intent = getIntent();
        EmailHolder = intent.getStringExtra(UserLoginActivity.UserEmail);
        EmailShow.setText(EmailHolder);

        //Boton salir
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                Intent intent = new Intent(DashboardActivity.this, UserLoginActivity.class);

                startActivity(intent);

                Toast.makeText(DashboardActivity.this, "Log Out Successfully", Toast.LENGTH_LONG).show();


            }
        });
    }
}
