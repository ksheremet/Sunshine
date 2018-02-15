package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String FORECAST_PARAM = "forecast";

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView forecast = (TextView) findViewById(R.id.forecast_tv);

        if (getIntent().hasExtra(FORECAST_PARAM)) {
            forecast.setText(getIntent().getStringExtra(FORECAST_PARAM));
        }
        // Completed (2) Display the weather forecast that was passed from MainActivity
    }
}
