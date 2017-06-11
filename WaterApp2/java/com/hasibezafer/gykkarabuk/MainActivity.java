package com.hasibezafer.gykkarabuk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUserName;
    EditText etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

        String rvUserName = etUserName.getText().toString();
        String rvPassword = etPassword.getText().toString();

        if(view == btnLogin)
        {
            if(rvUserName.equals("GelecegiYazanKadinlar")&& rvPassword.equals("12345"))
            {
                Intent intent = new Intent(getApplicationContext(),WaterCount.class);
                startActivity(intent);
            }

            else
            {
                Toast.makeText(getApplicationContext(),"Kullanıcı adı ya da parola hatalı",Toast.LENGTH_SHORT).show();
            }


        }

    }
}
