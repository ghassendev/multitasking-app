package com.example.myapplicationtp4;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.ls.LSOutput;

public class MainActivity extends AppCompatActivity {


   Button callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Build.VERSION.SDK_INT >= 23){
            if(checkPermission()) {
                Toast.makeText(getApplicationContext(),
                        "Permission granted", Toast.LENGTH_SHORT).show();
            }else {
                requestPermission();
            }


        }

        //input = (EditText) findViewById(R.id.input);
        //callButton = (Button) findViewById(R.id.call);

    }

    public boolean checkPermission(){
        int CallPermissionResult= ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE);

        return  CallPermissionResult == PackageManager.PERMISSION_GRANTED;
    }




    public void requestPermission(){
       ActivityCompat.requestPermissions
               (MainActivity.this,new String[]{
                Manifest.permission.CALL_PHONE
        },1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                callButton =findViewById(R.id.call);
                if (grantResults.length >0) {
                    boolean CallPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (CallPermission) {
                        Toast.makeText(MainActivity.this, "Permission accepted !",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Permission denied !",
                                Toast.LENGTH_LONG).show();
                        callButton.setEnabled(false);

                    }
                    break;

                }
                }
        }

@RequiresApi(api = Build.VERSION_CODES.M)
public void call(View view) {
    final EditText phoneNumber = findViewById(R.id.input);
    String phoneNum = phoneNumber.getText().toString();
    if (!TextUtils.isEmpty(phoneNum)) {
        String dial = "tel:" + phoneNum;

    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
        return;
    }
    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
} else {
        Toast.makeText(MainActivity.this, "Please put number",
                Toast.LENGTH_SHORT).show();
    }
    }
}




