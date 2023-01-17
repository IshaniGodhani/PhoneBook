package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    FloatingActionButton fab;
    Button btnDisplay;
   EditText etxtName,etxtContact;
    ListView listView;
    PhoneBookAdapter phonebookAdapter;
    ArrayList<User> userList= new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fab = findViewById(R.id.fab);
        etxtName = findViewById(R.id.etxtName);
        etxtContact = findViewById(R.id.etxtContact);
        listView = findViewById(R.id.list_item);


            DBHelper dbHelper = new DBHelper(MainActivity2.this);
            Cursor cursor = dbHelper.viewData();
            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String contact = cursor.getString(2);
                User user = new User(id, name, contact);
                userList.add(user);
            }

        phonebookAdapter = new PhoneBookAdapter(this, userList);
        listView.setAdapter(phonebookAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity2.this,MainActivity.class);

//                intent.putExtra("name",str1);
//                intent.putExtra("contact",str2);

                startActivity(intent);

            }
        });
    }





}
