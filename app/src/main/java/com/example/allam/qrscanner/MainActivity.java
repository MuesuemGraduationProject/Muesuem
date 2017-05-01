package com.example.allam.qrscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allam.qrscanner.database.DatabaseOperations;
import com.example.allam.qrscanner.model.StatueModel;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity  {
    private FloatingActionButton mScanButton;
    private TextView mScanDataTextView;
    private static final int SCAN_INTENT = 1;
    private DatabaseOperations mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DatabaseOperations(getBaseContext());
        mScanButton = (FloatingActionButton) findViewById(R.id.scan_button);
        mScanDataTextView = (TextView) findViewById(R.id.scan_data);

        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                } else {
                    Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                    startActivityForResult(intent, SCAN_INTENT);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SCAN_INTENT:
                String scanResult = data.getExtras().getString("scan_result");
                String scanFormat = data.getExtras().getString("scan_format");
                if(resultCode != RESULT_OK){
                    mScanDataTextView.setText("No Data Found");
                }else {
//                  mScanDataTextView.setText(scanFormat + " data: ( " + scanResult + " )");
                    StatueModel statueModel = new StatueModel();
                    statueModel.setName(scanResult);
                    statueModel.setDscription(scanResult + ": is an Egyptian statue that found in aswan");
                    mDatabase.insertIntoStatues(statueModel);
                    mScanDataTextView.setText(mDatabase.getStatueDescription(scanResult));
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission allowed successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You need to accept read sms permission first," +
                            " \n go to app settings and allow them.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


}
