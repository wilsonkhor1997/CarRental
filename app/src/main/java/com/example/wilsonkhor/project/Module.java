package com.example.wilsonkhor.project;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Wilson Khor on 16/12/2018.
 */

public class Module extends Application{
    public ArrayList <String> garrlist=new ArrayList<>();
    public ArrayAdapter <String> garrAdp;
    public String gvalue_Nric;
    public String gvalue_Name;

    public String getGvalue_Nric(){
        return gvalue_Nric;
    }

    public void setGvalue_Nric(String gvalue_Nric){
        this.gvalue_Nric=gvalue_Nric;
    }

    public String getGvalue_Name(){
        return gvalue_Name;
    }

    public void setGvalue_Name(String gvalue_name){
        this.gvalue_Name=gvalue_name;
    }
}
