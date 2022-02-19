package com.bicode.craft;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sketchware.remod.Resources;

public class AboutActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.layout.about);
		
    }
}
