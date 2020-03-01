package com.example.wilsonkhor.project;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Update extends AppCompatActivity {
    EditText tnric,tName,tphonenumber, taddress,taddress1,taddress2, date, time, renthour,rid;
    String gend, car;
    int total1,price;
    Button btnUpdate,btnCancel;
    DatabaseReference databaseReference;
    Module module;
    RadioGroup selectionCar;
    int selection;
    RadioGroup gender1;
    int jk;

    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        tnric=(EditText) findViewById(R.id.nric);
        tName=(EditText) findViewById(R.id.name);
        tphonenumber=(EditText) findViewById(R.id.phonenumber);
        taddress=(EditText) findViewById(R.id.address);
        taddress1=(EditText) findViewById(R.id.address1);
        taddress2=(EditText) findViewById(R.id.address2);
        date=(EditText) findViewById(R.id.date);
        time=(EditText) findViewById(R.id.time);
        renthour=(EditText) findViewById(R.id.rentHour);
        selectionCar=(RadioGroup)findViewById(R.id.selection);
        selection = selectionCar.getCheckedRadioButtonId();
        gender1 = (RadioGroup) findViewById(R.id.gender);
        jk = gender1.getCheckedRadioButtonId();
        rid=(EditText) findViewById(R.id.rid);


        btnUpdate=(Button) findViewById(R.id.update);
        btnCancel= (Button) findViewById(R.id.cancel) ;
        databaseReference= FirebaseDatabase.getInstance().getReference("Customer").child(user1.getUid());;
        module=((Module)getApplicationContext());
        final String str = module.getGvalue_Nric();
        rid.setText(str);
        rid.setEnabled(false);

        gender1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.male:
                        gend = "Male";
                        break;
                    case R.id.female:
                        gend = "Female";
                        break;
                }
            }
        });

        selectionCar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.axia:
                        car = "Produa Axia (1 hour RM 5)";
                        price=5;
                        break;
                    case R.id.myvi:
                        car = "Produa Myvi (1 hour RM 6)";
                        price=6;
                        break;
                    case R.id.bezza:
                        car = "Produa Bezza (1 hour RM 8)";
                        price=8;
                        break;
                    case R.id.alza:
                        car = "Produa Alza (1 hour RM 10)";
                        price=10;
                        break;
                    case R.id.hilux:
                        car = "Toyota Hilux (1 hour RM 15)";
                        price=15;
                        break;
                    case R.id.vellfire:
                        car = "Toyota Vellfire (1 hour RM 20)";
                        price=20;
                        break;
                }
                String rentHour = renthour.getText().toString().trim();
                total1=Integer.parseInt(rentHour)*price;
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(str).getValue(User.class);
                tnric.setText(user.getNric());
                tName.setText(user.getName());
                taddress.setText(user.getAddress());
                taddress1.setText(user.getAddress1());
                taddress2.setText(user.getAddress2());
                tphonenumber.setText(user.getPhoneNumber());
                date.setText(user.getDate());
                time.setText(user.getTime());
                renthour.setText(user.getRentHour());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArrayList();
                Cleartxt();
                Intent intphto =new Intent(getApplicationContext(),ViewAll.class);
                startActivity(intphto);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cleartxt();
            }
        });
    }
    public void Cleartxt(){
        tnric.setText("");
        tName.setText("");
        tphonenumber.setText("");
        taddress.setText("");
        taddress1.setText("");
        taddress2.setText("");
        date.setText("");
        time.setText("");
        renthour.setText("");
    }

    private void  updateArrayList() {
        final String nric = tnric.getText().toString().trim();
        final String name = tName.getText().toString().trim();
        final String phonenumber = tphonenumber.getText().toString().trim();
        final String address = taddress.getText().toString().trim();
        final String address1 = taddress1.getText().toString().trim();
        final String address2 = taddress2.getText().toString().trim();
        final String date1 = date.getText().toString().trim();
        final String time1 = time.getText().toString().trim();
        final String rentHour = renthour.getText().toString().trim();
        final int total = total1;
        final String gender = gend;
        final String car1 = car;

        if (TextUtils.isEmpty(nric)) {
            tnric.setError("Please enter your NRIC!");
        } else if (TextUtils.isEmpty(name)) {
            tName.setError("Please enter your Name!");
        } else if (TextUtils.isEmpty(phonenumber)) {
            tphonenumber.setError("Please enter your Phone Number!");
        } else if (TextUtils.isEmpty(address)) {
            taddress.setError("Please enter your Address!");
        } else if (TextUtils.isEmpty(address1)) {
            taddress1.setError("Please enter your Address!");
        } else if (TextUtils.isEmpty(address2)) {
            taddress2.setError("Please enter your Address!");
        } else if (TextUtils.isEmpty(date1)) {
            date.setError("Please enter the DATE you want to rent!");
        } else if (TextUtils.isEmpty(time1)) {
            time.setError("Please enter the TIME you want to rent!");
        } else if (TextUtils.isEmpty(rentHour)) {
            renthour.setError("Please enter Rent Hour!");
        } else {

            User cus = new User(nric, name, phonenumber, date1, time1, rentHour, address, address1, address2, gender, car1, total);
            databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("nric").setValue(nric);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("name").setValue(name);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("phoneNumber").setValue(phonenumber);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("address").setValue(address);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("address1").setValue(address1);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("address2").setValue(address2);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("date").setValue(date1);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("time").setValue(time1);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("rentHour").setValue(rentHour);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("selection").setValue(car1);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("gender").setValue(gender);
                    databaseReference.child("Customer").child(user1.getUid()).child(module.getGvalue_Nric()).child("payment").setValue(total);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Toast.makeText(this, "Data is updated", Toast.LENGTH_LONG).show();
            Cleartxt();
        }
    }

    public void setDate(View v) {
        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int dt=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datepickerDialog = new DatePickerDialog(Update.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yEar, int mOnth, int dat) {
                date.setText(dat + "-" + (mOnth + 1) + "-" + yEar);

            }
        }, year, month, dt);
        datepickerDialog.show();
    }

    public void setTime(View v) {
        final Calendar d=Calendar.getInstance();
        int hOur=d.get(Calendar.HOUR_OF_DAY);
        int mInute=d.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Update.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                time.setText(hour + ":" + minute);
            }
        }, hOur, mInute, false);
        timePickerDialog.show();
    }
}
