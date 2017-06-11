package com.hasibezafer.gykkarabuk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeekActivity extends AppCompatActivity implements View.OnClickListener {

   TextView tvResult;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        tvResult = (TextView) findViewById(R.id.tvResult);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        Integer rvResult = getIntent().getIntExtra("AMOUNT",0);
        tvResult.setText(rvResult + " ml");


    }

    @Override
    public void onClick(View view) {

        if (view == btnBack) {

            Intent intent = new Intent(getApplicationContext(), WaterCount.class);
            startActivity(intent);
        }

    }
}
