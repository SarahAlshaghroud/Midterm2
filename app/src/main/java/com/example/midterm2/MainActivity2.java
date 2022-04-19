package com.example.midterm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView id = (TextView) findViewById(R.id.id); //pid

        EditText name1 = (EditText) findViewById(R.id.name);

        EditText surname1 = (EditText) findViewById(R.id.surName);

        EditText NaID1 = (EditText) findViewById(R.id.NationalID);

        Button btnAdd = (Button) findViewById(R.id.Addbtn);

        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name1.getText().toString();
                String surname = surname1.getText().toString();
                String NaID = NaID1.getText().toString();

                if (name.equals("") || surname.equals("") || NaID.equals(""))
                    Toast.makeText(MainActivity2.this, "make sure all filed are filled",
                            Toast.LENGTH_SHORT).show();
                else if (!myDB.addData(name, surname, NaID))
                    Toast.makeText(MainActivity2.this, "Insertion Failed",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity2.this, "Insertion Complete",
                            Toast.LENGTH_SHORT).show();
            }
        });
        Button btnN = (Button) findViewById(R.id.button5);
        Button btnP = (Button) findViewById(R.id.button4);

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });

        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });

    }
}