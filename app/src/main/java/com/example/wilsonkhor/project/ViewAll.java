package com.example.wilsonkhor.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAll extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arraylist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Button btnView,btnDelete, btnUpdate, btnBack;
    Module module;

    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(user1.getUid());
        listView = (ListView)findViewById(R.id.listview);
        btnUpdate=(Button)findViewById(R.id.update);
        btnView=(Button)findViewById(R.id.view);
        btnDelete=(Button)findViewById(R.id.delete);
        btnBack=(Button)findViewById(R.id.btn_back);
        module=((Module)getApplicationContext());
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arraylist);
        listView.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value=dataSnapshot.getValue(User.class).toString();
                arraylist.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                module.setGvalue_Nric(arraylist.get(position));
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(module.getGvalue_Nric()==null){
                    Toast.makeText(ViewAll.this,"Please Select Items",Toast.LENGTH_LONG).show();
                }else {
                    Intent intphto =new Intent(getApplicationContext(),VIewActivity.class);
                    startActivity(intphto);
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(module.getGvalue_Nric()==null){
                    Toast.makeText(ViewAll.this,"Please Select Items",Toast.LENGTH_LONG).show();
                }else {
                    Intent intphto =new Intent(getApplicationContext(),Update.class);
                    startActivity(intphto);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (module.getGvalue_Nric()==null){
                    Toast.makeText(ViewAll.this,"Please Select Item Before Delete",Toast.LENGTH_LONG).show();
                }else {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            databaseReference.child(module.getGvalue_Nric()).removeValue();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(ViewAll.this,"DATA DELETED !!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ViewAll.class);
                    startActivity(intent);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAll.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
