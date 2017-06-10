package com.hasibezafer.gykkarabuk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WaterCount extends AppCompatActivity implements View.OnClickListener{

    EditText etAmount;
    Button btnAdd;
    Button btnWeek;
    Integer result=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_count);

        etAmount = (EditText) findViewById(R.id.etAmount);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnWeek = (Button) findViewById(R.id.btnWeek);
        btnWeek.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String amount = etAmount.getText().toString();
        etAmount.setText(null); // EditText'e her veri girdiğimizde butona tıklayınca edittext temizleniyor.


        if(view == btnAdd)
        {
            if(!amount.isEmpty()) { //Miktar verisi boş mu değil mi ?
            Integer rvAmount = Integer.parseInt(amount); //String Integer dönüştür
            result = result + rvAmount;

            Toast.makeText(getApplicationContext(), rvAmount + "ml eklendi" , Toast.LENGTH_SHORT).show();

        } }

            else
        {
            Toast.makeText(getApplicationContext(),"Miktar boş olamaz.",Toast.LENGTH_SHORT).show();
        }

        if( view ==btnWeek)
        {
            Intent intent = new Intent(getApplicationContext(),WeekActivity.class);
            intent.putExtra("AMOUNT",result);
            startActivity(intent);
        }



    }
}
