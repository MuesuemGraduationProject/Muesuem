package com.example.allam.qrscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        String scanResult = result.getText();
        String scanFormat = result.getBarcodeFormat().toString();
        Toast.makeText(this, scanResult, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("scan_result", scanResult);
        intent.putExtra("scan_format", scanFormat);
        setResult(RESULT_OK, intent);
        mScannerView.stopCamera();
        finish();
    }


}
