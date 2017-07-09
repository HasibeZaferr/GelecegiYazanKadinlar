package com.hasibezafer.gelecegiyazankadinlar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin )
        {
            Intent intent = new Intent(getBaseContext(),LoginActivity.class);
            startActivity(intent);
        }

        else if(view == btnSignUp)
        {
            Intent intent = new Intent(getBaseContext(),SignUpActivity.class);
            startActivity(intent);
        }
    }
}
