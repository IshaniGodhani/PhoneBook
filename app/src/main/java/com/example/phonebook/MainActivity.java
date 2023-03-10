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
    int id;
    String name;
    String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxtName=findViewById(R.id.etxtName);
        etxtContact=findViewById(R.id.etxtContact);
        btnSubmit=findViewById(R.id.btnSubmit);
        if(getIntent().getExtras()!=null) {
            int id = getIntent().getIntExtra("Id", 0);
            String name = getIntent().getStringExtra("Name");
            String contact = getIntent().getStringExtra("Contact");
            System.out.println("id=" + id);
            etxtName.setText("" + name);
            etxtContact.setText("" + contact);

            findViewById(R.id.btnDisplay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(getIntent().getExtras()!=null)
                    {
                        String txt1 = etxtName.getText().toString();
                        String txt2 = etxtContact.getText().toString();
                        DBHelper dbHelper=new DBHelper(MainActivity.this);
                        dbHelper.updateData(id, txt1, txt2);
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }

                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);

                }
            });

        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etxtName.getText().toString();
                String contact = etxtContact.getText().toString();
                DBHelper dbHelper=new DBHelper(MainActivity.this);
                if(getIntent().getExtras()==null)
                {
                    dbHelper.insertData(name,contact);
                    if(name==null && contact==null)
                    {

                    }
                    else if(name!=null && contact==null)
                    {

                    }
                    else if(name==null && contact!=null)
                    {

                    }
                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}