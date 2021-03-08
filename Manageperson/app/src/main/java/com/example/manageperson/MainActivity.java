package com.example.manageperson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edID,edName,edAge;
    Button btnAdd, btnUpdate, btnDelete, btnFind, btnFindAll;

    DatabaseReference personDatabase, personChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    intialize();}

    private void intialize() {
        edID = findViewById(R.id.edId);
        edAge = findViewById(R.id.edAge);
        edName = findViewById(R.id.edName);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnFind = findViewById(R.id.btnFind);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnFindAll = findViewById(R.id.btnFindAll);

        btnAdd.setOnClickListener(this);
        btnFindAll.setOnClickListener(this);
        btnFind.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        personDatabase = FirebaseDatabase.getInstance().getReference("Person");

    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch(id){
            case R.id.btnAdd :
                add();
                break;
            case R.id.btnDelete :

                break;
            case R.id.btnFind :
                break;
            case R.id.btnFindAll :
                break;
            case R.id.btnUpdate :
                update();
                break;
        }
    }

    private void update() {
        String name = edName.getText().toString();
        int id = Integer.valueOf(edID.getText().toString());
        int age = Integer.valueOf(edAge.getText().toString());

        Person person = new Person(id, name, age);
        personChild=FirebaseDatabase.getInstance().getReference().child("Person").child(edID.getText().toString());
        personChild.setValue(person);
        Toast.makeText(this,"The Document with the id "+id+" is updated",Toast.LENGTH_LONG).show();
    }

    private void add() {
        try {
            String name = edName.getText().toString();
            int id = Integer.valueOf(edID.getText().toString());
            int age = Integer.valueOf(edAge.getText().toString());

            Person person = new Person(id, name, age);
            personDatabase.child(edID.getText().toString()).setValue(person);
            Toast.makeText(this, "The Document with the identifier " + id + " is Added Successfullly", Toast.LENGTH_LONG).show();
            edID.setText(null);
            edName.setText(null);
            edAge.setText(null);
            edID.requestFocus();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    }
}