package com.example.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //первый срособ для таймера
//        final Handler handler = new Handler();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Runnable : ", "Two seconds are passed");
//                handler.postDelayed(this,2000);//posttDelayed значит отослать с задержкой
//                                                          //this означает передаем runnable
//            }
//        };
//        handler.post(runnable);

        //Второй способ - таймер обратного отчета
        CountDownTimer myTimer = new CountDownTimer(10000,
                1000) {
            @Override
            public void onTick(long millisUntilUntilFinished) {
                Log.d("myTimer : ",String.valueOf(millisUntilUntilFinished/1000)+
                        "seconds left");
            }

            @Override
            public void onFinish() {
                Log.d("myTimer", "Finish");
            }
        };
        myTimer.start();
    }
}
