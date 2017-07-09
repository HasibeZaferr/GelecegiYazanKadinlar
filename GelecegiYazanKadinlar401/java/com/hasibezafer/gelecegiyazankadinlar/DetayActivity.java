package com.hasibezafer.gelecegiyazankadinlar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetayActivity extends AppCompatActivity {

    TextView rvUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        rvUserName = (TextView) findViewById(R.id.rvUserName);

        FirebaseUser fbUser =  FirebaseAuth.getInstance().getCurrentUser();

        String userId = fbUser.getUid();
        String isim = fbUser.getDisplayName();
        rvUserName.setText(isim + " Notları");









    }
}
