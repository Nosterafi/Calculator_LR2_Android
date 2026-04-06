package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.start_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.start_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DisplayMetrics display = getResources().getDisplayMetrics();
        boolean stop = (display.heightPixels < 1920 || display.widthPixels < 1080) &&
                (display.heightPixels < 1080 || display.widthPixels < 1920);
        if (stop) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    public void handleClick(View view) {
        CharSequence butText = ((Button)view).getText();

        if (butText.equals(getString(R.string.start_calculator_button_signature))){
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
        }
        else if (butText.equals(getString(R.string.start_exit_button_signature)))
            finishAffinity();
    }
}