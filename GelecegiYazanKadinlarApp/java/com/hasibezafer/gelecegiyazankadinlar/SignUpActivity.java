package com.hasibezafer.gelecegiyazankadinlar;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUserName;
    EditText etMail;
    EditText etPassword;
    EditText etPasswordConfirm;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        etUserName = (EditText) findViewById(R.id.etRegisterUserName);
        etMail = (EditText) findViewById(R.id.etRegisterEmail);
        etPassword = (EditText) findViewById(R.id.etRegisterPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etRegisterPasswordConfirm);
        btnSignUp = (Button) findViewById(R.id.btnRegisterSignUp);
        btnSignUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view == btnSignUp)
        {
            register();
        }



    }

    private void register() {

        final String rvUserName = etUserName.getText().toString();
        final String rvMail = etMail.getText().toString();
        String rvPassword = etPassword.getText().toString();
        String rvPasswordConfirm = etPasswordConfirm.getText().toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if(!rvUserName.isEmpty() && !rvMail.isEmpty() && !rvPassword.isEmpty() && !rvPasswordConfirm.isEmpty() )
        {

            if( rvPasswordConfirm.equals(rvPassword) ) {


        mAuth.createUserWithEmailAndPassword(rvMail,rvPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseUser fbUser =  task.getResult().getUser();
                    String userId =   fbUser.getUid();

                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("uyeler");

                    dbRef.child(userId).child("isim").setValue(rvUserName);
                    dbRef.child(userId).child("email").setValue(rvMail);






                    Toast.makeText(getApplicationContext(), "Aramıza Hoşgeldin! " + rvUserName, Toast.LENGTH_LONG).show();


                    fbUser.sendEmailVerification()
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    Toast.makeText(getApplicationContext(), "Onay maili gönderildi!", Toast.LENGTH_LONG).show();
                                }
                            });

                }

            }
        }).addOnFailureListener(this, new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                if(e instanceof FirebaseAuthInvalidUserException)
                {
                    Toast.makeText(getApplicationContext(), "Mail zaten kayıtlı!", Toast.LENGTH_LONG).show();

                }
                else if (e instanceof FirebaseAuthWeakPasswordException)
                {
                    Toast.makeText(getApplicationContext(), "Şifre en az 6 karakter olmalı!", Toast.LENGTH_LONG).show();
                }
                else if (e instanceof FirebaseAuthInvalidCredentialsException)
                {
                    Toast.makeText(getApplicationContext(), "Hatalı mail formatı!", Toast.LENGTH_LONG).show();
                }

            }
        });




    }
    else
        {
                Toast.makeText(getApplicationContext(), "Parolalar eşleşmiyor!", Toast.LENGTH_LONG).show();
        }
    }
    else
        {
            Toast.makeText(getApplicationContext(), "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_LONG).show();
        }
    }}
