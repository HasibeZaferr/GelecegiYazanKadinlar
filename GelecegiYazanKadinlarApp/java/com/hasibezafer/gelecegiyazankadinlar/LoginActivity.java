package com.hasibezafer.gelecegiyazankadinlar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etMail;
    EditText etPassword;
    Button btnLogin;
    TextView tvReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        tvReset = (TextView) findViewById(R.id.tvReset);
        tvReset.setOnClickListener(this);

        //Veritabanımızla bağlantımızı kurup referans oluşturuyoruz.
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onClick(View view) {

        if(view == btnLogin) {
            login();
        }

        else if (view == tvReset)
        {
            passwordReset();
        }


    }

    private void passwordReset() {

        String rvMail = etMail.getText().toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if(!rvMail.equals(" ") && !rvMail.isEmpty() )
        {


            mAuth.sendPasswordResetEmail(rvMail).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Parola sıfırlama maili gönderildi!", Toast.LENGTH_LONG).show();

                }

            }

        }).addOnFailureListener(LoginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if(e.getMessage().contains("[ INVALID_EMAIL ]"))
                //Mail adresi format kontrolü
                {
                    Toast.makeText(getApplicationContext(), "Mail adresinizi hatalı girdiniz!", Toast.LENGTH_LONG).show();

                }

                else if(e instanceof FirebaseAuthInvalidUserException)
                //Kullanıcı sistemde kayıtlı mı kontrolü
                {
                    Toast.makeText(getApplicationContext(), "Kayıtlı değil misiniz ? Kayıt olun !", Toast.LENGTH_LONG).show();

                }

            }
        });


    }
    else
            Toast.makeText(getApplicationContext(), "Boş olamaz!", Toast.LENGTH_LONG).show();

    }

    private void login() {

        String rvMail = etMail.getText().toString();
        String rvPassword = etPassword.getText().toString();


        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if(!rvMail.equals(" ") && !rvMail.isEmpty() && !rvPassword.equals(" ") && !rvPassword.isEmpty())
        {


            mAuth.signInWithEmailAndPassword(rvMail,rvPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    FirebaseUser mUser = task.getResult().getUser();

                    if(mUser.isEmailVerified())
                    {
                        Toast.makeText(getApplicationContext(), "Giriş işlemi başarılı!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(), "Lütfen mailinizi onaylayın !", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Kullanıcı adı ya da şifre hatatlı !", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    else
            Toast.makeText(getApplicationContext(), "Boş olamaz!", Toast.LENGTH_LONG).show();

    }
}
