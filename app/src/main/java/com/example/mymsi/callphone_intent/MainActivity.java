package com.example.mymsi.callphone_intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button)findViewById(R.id.callPhoneButton);
        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(MainActivity.this,"没有权限！",Toast.LENGTH_SHORT).show();
                            return;
                }
                EditText editText = (EditText)findViewById(R.id.textPhoneNumber);
                String phoneNumber = editText.getText().toString();
                String encodedPhoneNumber = null;
                try {
                    encodedPhoneNumber = URLEncoder.encode(phoneNumber, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent();
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + encodedPhoneNumber)));
            }
        });
    }
}
