package com.hasibezafer.gelecegiyazankadinlar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int PICK_IMAGE_REQUEST =234 ;
    private ImageView imageProfil;
    private TextView tvPhoto;
    private Button btnAdd;
    private Uri filePath;
    private StorageReference storageReference;
    EditText etTitle;
    EditText etNote;
    TextView etUserNameProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageProfil=(ImageView)findViewById(R.id.imageProfil);
        tvPhoto=(TextView)findViewById(R.id.tvPhoto);
        tvPhoto.setOnClickListener(this);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etNote = (EditText) findViewById(R.id.etNote);
        etUserNameProfile = (TextView) findViewById(R.id.etUserNameProfile);

        FirebaseUser fbUser =  FirebaseAuth.getInstance().getCurrentUser();

        String userId = fbUser.getUid();
        String isim = fbUser.getDisplayName();
        etUserNameProfile.setText(isim);


        storageReference=FirebaseStorage.getInstance().getReference();

    }


    private void upload(){
        if(filePath !=null){
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
        StorageReference riversRef = storageReference.child("images/profile.jpg");
            riversRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage(((int)progress)+ "% uploaded");
            }
        });


            String rvTitle = etTitle.getText().toString();
            String rvNote = etNote.getText().toString();

            FirebaseUser fbUser =  FirebaseAuth.getInstance().getCurrentUser();

            String userId = fbUser.getUid();
            String isim = fbUser.getDisplayName();

            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().
                    child("notlar").child(userId);

            dbRef.child("isim").setValue(isim);

            String notId =   dbRef.push().getKey() ;
            dbRef.child("not").child(notId).child("baslik").setValue(rvTitle);
            dbRef.child("not").child(notId).child("detay").setValue(rvNote);

            Intent i = new Intent(this,DetayActivity.class);
            startActivity(i);

            Toast.makeText(this,"Kaydedildi",Toast.LENGTH_SHORT).show();

    }
    else
            Toast.makeText(getApplicationContext(),"İmage dont upload",Toast.LENGTH_SHORT).show();}

    private void showFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select image"),PICK_IMAGE_REQUEST);
        tvPhoto.setText("");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
          filePath=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageProfil.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public void onClick(View view) {
        if(view==btnAdd){
            upload();
        }
        else if(view==tvPhoto){
            showFileChooser();
        }

    }
}
