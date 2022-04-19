package com.example.midterm2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper myDB;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Button fData=(Button) findViewById(R.id.fetchBtn);
        Button delete=(Button) findViewById(R.id.Delbtn);
        EditText valID=(EditText) findViewById(R.id.editTextTextPersonName) ;
        fData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = myDB.ViewData();
                StringBuffer buffer = new StringBuffer();

                while (cur.moveToNext()){

                    buffer.append("ID: "+ cur.getString(0) + "\n");
                    buffer.append("Name: "+ cur.getString(1) + "\n");
                    buffer.append("Surname: "+ cur.getString(2) + "\n\n");
                    buffer.append("National ID: "+ cur.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setCancelable(true);
                builder.setTitle("All Data");
                builder.setMessage(buffer.toString());
                builder.show();

                Toast.makeText(MainActivity3.this, "Successful View", Toast.LENGTH_LONG).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = valID.getText().toString();
                myDB.DeleteEmployees(id);

                Toast.makeText(MainActivity3.this, "Successful Delete", Toast.LENGTH_LONG).show();

            }
        });

        Button prev=(Button) findViewById(R.id.back);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
            }
        });
    }
}