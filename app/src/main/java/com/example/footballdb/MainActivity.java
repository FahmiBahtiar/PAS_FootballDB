package com.example.footballdb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    EditText txtusername;
    EditText txtpassword;
    TextView tvpasswordsalah;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        tvpasswordsalah = (TextView) findViewById(R.id.tvpasswordsalah);
        btnlogin = (Button) findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtusername.getText().toString().equals("Hamba Allah") && txtpassword.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, loadingactivity.class);
                    intent.putExtra("email", txtusername.getText().toString());
                    startActivity(intent);

                    editor = pref.edit();
                    editor.putString("userid", txtusername.getText().toString());
                    editor.apply();
                    finish();
                } else if (txtusername.getText().toString().equals("Hamba Allah") && !txtpassword.getText().toString().equals("admin")) {
                    tvpasswordsalah.setText("Password Salah.");
                } else if (!txtusername.getText().toString().equals("Hamba Allah") && !txtpassword.getText().toString().equals("admin")) {
                    tvpasswordsalah.setText("Kombinasi Email Dan Password Salah.");
                }

            }

        });
    }
}