package com.example.wilsonkhor.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VIewActivity extends AppCompatActivity {
    TextView tnric,tName,tphonenumber, taddress,taddress1,taddress2, date, time, renthour,rid,selection,gender,payment;
    DatabaseReference databaseReference;
    Module module;
    Button btnBack;

    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        rid=findViewById(R.id.rid);
        tnric=findViewById(R.id.nric1);
        tName=findViewById(R.id.name1);
        tphonenumber=findViewById(R.id.phonenumber1);
        gender=findViewById(R.id.gender1);
        taddress=findViewById(R.id.address0);
        taddress1=findViewById(R.id.address01);
        taddress2=findViewById(R.id.address02);
        date=findViewById(R.id.date1);
        time=findViewById(R.id.time1);
        selection=findViewById(R.id.selection1);
        renthour=findViewById(R.id.rentHour1);
        payment=findViewById(R.id.payment2);

        btnBack=findViewById(R.id.btn_back);

        module=((Module)getApplicationContext());
        final String str = module.getGvalue_Nric();

        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(user1.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(str).getValue(User.class);
                rid.setText(user.getKey().toString());
                tnric.setText(user.getNric().toString());
                tName.setText(user.getName().toString());
                taddress.setText(user.getAddress().toString());
                taddress1.setText(user.getAddress1().toString());
                taddress2.setText(user.getAddress2().toString());
                tphonenumber.setText(user.getPhoneNumber().toString());
                date.setText(user.getDate().toString());
                time.setText(user.getTime().toString());
                renthour.setText(user.getRentHour().toString());
                selection.setText(user.getSelection().toString());
                gender.setText(user.getGender().toString());
                payment.setText(String.format("RM %d",user.getPayment()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VIewActivity.this, ViewAll.class);
                startActivity(intent);
            }
        });
    }
}

