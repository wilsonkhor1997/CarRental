package com.example.wilsonkhor.project;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wilsonkhor.project.AccountActivity.LoginActivity;
import com.example.wilsonkhor.project.AccountActivity.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText date, time, nric, name, phonenumber, rentHour, address, address1, address2;
    String gend, car;
    int total;
    Button save;
    FirebaseDatabase FireDatabase;
    DatabaseReference databaseReference;
    User user;
    private Button btnRemoveUser, signOut;

    final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        nric=(EditText)findViewById(R.id.nric);
        name=(EditText)findViewById(R.id.name);
        phonenumber=(EditText)findViewById(R.id.phoneNumber);
        address=(EditText)findViewById(R.id.address);
        address1=(EditText)findViewById(R.id.address1);
        address2=(EditText)findViewById(R.id.address2);
        date=(EditText)findViewById(R.id.date);
        time=(EditText)findViewById(R.id.time);
        rentHour=(EditText)findViewById(R.id.rentHour);
        save = (Button)findViewById(R.id.save);
        Button sub = (Button)findViewById(R.id.submit);
        btnRemoveUser = (Button) findViewById(R.id.remove_user_button);
        signOut = (Button) findViewById(R.id.sign_out);


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(user1.getUid());
        FireDatabase = FirebaseDatabase.getInstance();
        user = new User();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nric = (EditText) findViewById(R.id.nric);
                TextView outputNric = (TextView) findViewById(R.id.outputNRIC);
                String noNric = nric.getText().toString();
                outputNric.setText("NRIC: " + noNric);

                EditText nAme = (EditText) findViewById(R.id.name);
                TextView outputName = (TextView) findViewById(R.id.outputName);
                String name = nAme.getText().toString();
                outputName.setText("Name: " + name);

                EditText phoneN = (EditText) findViewById(R.id.phoneNumber);
                TextView outputphoneNumber = (TextView) findViewById(R.id.outputphoneNumber);
                String phonenumber = phoneN.getText().toString();
                outputphoneNumber.setText("Phone Number: " + phonenumber);

                RadioGroup gender = (RadioGroup) findViewById(R.id.gender);
                int jk = gender.getCheckedRadioButtonId();
                TextView outputJk = (TextView) findViewById(R.id.outputGender);
                if (jk == R.id.male) {
                    gend = "Male";
                    outputJk.setText("Gender is Male");
                } else if (jk == R.id.female) {
                    gend = "Female";
                    outputJk.setText("Gender is Female");
                }


                EditText aDdress = (EditText) findViewById(R.id.address);
                EditText aDdress1 = (EditText) findViewById(R.id.address1);
                EditText aDdress2 = (EditText) findViewById(R.id.address2);
                TextView outputAddress = (TextView) findViewById(R.id.outputAddress);
                String address = aDdress.getText().toString();
                String address1 = aDdress1.getText().toString();
                String address2 = aDdress2.getText().toString();
                outputAddress.setText("Address: \n" + address + "\n" + address1 + "\n" + address2);

                EditText rentHour = (EditText) findViewById(R.id.rentHour);
                TextView outputRentHour = (TextView) findViewById(R.id.outputRentHour);
                int rent = Integer.parseInt(rentHour.getText().toString());
                outputRentHour.setText("You will rent this car for " + rent + " hour(s)");

                EditText dAte = (EditText) findViewById(R.id.date);
                TextView outputDate = (TextView) findViewById(R.id.outputDate);
                String date1 = dAte.getText().toString();
                outputDate.setText("The date you selected is " + date1);

                EditText tIme = (EditText) findViewById(R.id.time);
                TextView outputTime = (TextView) findViewById(R.id.outputTime);
                String time1 = tIme.getText().toString();
                outputTime.setText("The time you selected is " + time1);

                int price=0;

                RadioGroup selectionCar = (RadioGroup) findViewById(R.id.selection);
                int selection = selectionCar.getCheckedRadioButtonId();
                TextView outputSelection = (TextView) findViewById(R.id.outputSelection);
                if (selection == R.id.axia) {
                    car = "Produa Axia";
                    outputSelection.setText("The car you selected is Produa Axia");
                    price=5;
                } else if (selection == R.id.myvi) {
                    car = "Produa Myvi";
                    outputSelection.setText("The car you selected is Produa Myvi");
                    price=6;
                } else if (selection == R.id.bezza) {
                    car = "Produa Bezza";
                    outputSelection.setText("The car you selected is Produa Bezza");
                    price=8;
                } else if (selection == R.id.alza) {
                    car = "Produa Alza";
                    outputSelection.setText("The car you selected is Produa Alza");
                    price=10;
                } else if (selection == R.id.hilux) {
                    car = "Toyota Hilux";
                    outputSelection.setText("The car you selected is Toyota Hilux");
                    price=15;
                } else if (selection == R.id.vellfire) {
                    car = "Toyota Vellfire";
                    outputSelection.setText("The car you selected is Toyota Vellfire");
                    price=20;
                }
                total=rent*price;
                TextView outputPrice = (TextView) findViewById(R.id.outputPrice);
                outputPrice.setText("The total price is RM " + total);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addArrayList();
            }
        });

        btnRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1 != null) {
                    user1.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });



    }

    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }

        }


    };
    private void addArrayList(){
        String iD = databaseReference.push().getKey();
        user.setKey(iD);
        user.setNric(nric.getText().toString());
        user.setName(name.getText().toString());
        user.setPhoneNumber(phonenumber.getText().toString());
        user.setAddress(address.getText().toString());
        user.setAddress1(address1.getText().toString());
        user.setAddress2(address2.getText().toString());
        user.setDate(date.getText().toString());
        user.setTime(time.getText().toString());
        user.setRentHour(rentHour.getText().toString());
        user.setGender(gend);
        user.setSelection(car);
        user.setPayment(total);

        if(nric.getText().toString().equals((""))){
            Toast.makeText(this, "Please input your NRIC",Toast.LENGTH_SHORT).show();
        }else if(name.getText().toString().equals((""))){
            Toast.makeText(this, "Please input your NAME",Toast.LENGTH_SHORT).show();
        }else if(phonenumber.getText().toString().equals((""))){
            Toast.makeText(this, "Please input your PHONE NUMBER",Toast.LENGTH_SHORT).show();
        }else if(address.getText().toString().equals((""))){
            Toast.makeText(this, "Please input your ADDRESS",Toast.LENGTH_SHORT).show();
        }else if(address1.getText().toString().equals((""))){
            Toast.makeText(this, "Please input your ADDRESS",Toast.LENGTH_SHORT).show();
        }else if(address2.getText().toString().equals((""))){
            Toast.makeText(this, "Please input your ADDRESS",Toast.LENGTH_SHORT).show();
        }else if(date.getText().toString().equals((""))){
            Toast.makeText(this, "Please input the DATE your want",Toast.LENGTH_SHORT).show();
        }else if(time.getText().toString().equals((""))){
            Toast.makeText(this, "Please input the TIME your want",Toast.LENGTH_SHORT).show();
        }else if(rentHour.getText().toString().equals((""))){
            Toast.makeText(this, "Please input your RENT HOUR",Toast.LENGTH_SHORT).show();
        }else {

            databaseReference.child(iD).setValue(user);
            Toast.makeText(MainActivity.this, "Data Submitted...", Toast.LENGTH_SHORT).show();
            clearText();
        }
    }

    public void clearText(){
        nric.setText("");
        name.setText("");
        phonenumber.setText("");
        address.setText("");
        address1.setText("");
        address2.setText("");
        date.setText("");
        time.setText("");
        rentHour.setText("");
    }



    public void setDate(View v) {
        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int dt=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datepickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                time.setText(hour + ":" + minute);
            }
        }, hOur, mInute, false);
        timePickerDialog.show();
    }

    public void setgallery(View v){
        Intent i = new Intent (MainActivity.this, gallery.class);
        startActivity(i);
    }

    public void viewAll(View v){
        Intent intent = new Intent(MainActivity.this, ViewAll.class);
        startActivity(intent);
    }

    public void signOut() {
        auth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));


// this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

}
