package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etxtName,etxtContact;
    Button btnSubmit, btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxtName=findViewById(R.id.etxtName);
        etxtContact=findViewById(R.id.etxtContact);
        btnSubmit=findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etxtName.getText().toString();
                String contact=etxtContact.getText().toString();

                if(getIntent().getExtras()==null)
                {
                    DBHelper dbHelper=new DBHelper(MainActivity.this);
                    dbHelper.insertData(name,contact);
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    DBHelper dbHelper=new DBHelper(MainActivity.this);
                    dbHelper.updateData(id,name,contact);
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
}