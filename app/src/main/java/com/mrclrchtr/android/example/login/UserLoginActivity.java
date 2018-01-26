package com.mrclrchtr.android.example.login;

/**
 * Created by Miguel A Tunubala on 25/01/2018.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrclrchtr.android.example.MainActivity;
import com.mrclrchtr.android.example.R;
import java.util.HashMap;

public class UserLoginActivity extends AppCompatActivity {

    EditText Email, Password;
    Button LogIn, Register ;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "https://androidjsonblog.000webhostapp.com/User/UserLogin.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText)findViewById(R.id.txtEmail);
        Password = (EditText)findViewById(R.id.txtPasword);
        LogIn = (Button)findViewById(R.id.btnLogin);
        Register = (Button)findViewById(R.id.btnIngresar);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserLoginFunction(EmailHolder, PasswordHolder);

                }
                else {
                    Toast.makeText(UserLoginActivity.this, "Faltan datos.", Toast.LENGTH_LONG).show();
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLoginActivity.this,User_register_login.class);
                startActivity(intent);
            }
        });
    }
    public void CheckEditTextIsEmptyOrNot(){
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {
           CheckEditText = true ;
        }
    }

    public void UserLoginFunction(final String email, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(UserLoginActivity.this,"Iniciando sesión",null,true,true);
            }
            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){
                    finish();
                     /*
                     *Para poder pasar a la ventana principal despues de inciar session, simplemente cambiar
                     * el MainActivity por la clase que se desea acceder, .
                     * En el archivo DashboardActivity esta como recibir el nombre de usuario y el boton de cerrar
                     * la sesión de usuario, revisar, corregir tambien el diseño
                     *                                                           Miguel A. Tunubalá
                     */
                    Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                    intent.putExtra(UserEmail,email); //Envia el nombre de usuario a la clase MainActivity
                    startActivity(intent);
                }
                else{
                    Toast.makeText(UserLoginActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            protected String doInBackground(String... params) {
                hashMap.put("email",params[0]);
                hashMap.put("password",params[1]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }
        UserLoginClass userLoginClass = new UserLoginClass();
        userLoginClass.execute(email,password);
    }
}
