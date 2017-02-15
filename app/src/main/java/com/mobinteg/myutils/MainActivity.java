package com.mobinteg.myutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mobinteg.utilslib.MyUtils;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.tv);

        tv.setText(MyUtils.convertDate("Qui Nov 03 2016", "EEE MMM dd yyyy", "EEE dd-MM-yyyy", new Locale("pt", "PT"), Locale.US, true));

    }
}
