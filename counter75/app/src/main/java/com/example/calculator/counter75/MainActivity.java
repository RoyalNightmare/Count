package com.example.calculator.counter75;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int count=0;
    TextView res;
    volatile boolean stopCounterFlag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = findViewById(R.id.tv_counter);
    }

    public void startCounter(View view){
//        Toast.makeText(this, "Start Button Clicked", Toast.LENGTH_SHORT).show();
        stopCounterFlag=false;
        count=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stopCounterFlag){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count++;
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                           res.setText(String.valueOf(count));
                        }
                    });
                }
            }
        }).start();
    }

    public void stopCounter(View view){
//        Toast.makeText(this, "Stop Button Clicked", Toast.LENGTH_SHORT).show();
        stopCounterFlag=true;
    }
}