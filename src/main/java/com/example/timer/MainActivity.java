package com.example.timer;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);

        seekbar.setMax(600);
        seekbar.setProgress(60);//изначально установили на 60

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                int minutes = progress/60;
                int seconds = progress - (minutes*60);

                //если число на таймере будет однозначным, и чтобы не изменился интерфейс
                String minutesString = "";
                String secondsString = "";

                if (minutes < 10){
                    minutesString = "0" + minutes;
                }else {
                    minutesString = String.valueOf(minutes);
                }

                if (seconds < 10){
                    secondsString = "0" + seconds;
                }else {
                    secondsString = String.valueOf(seconds);
                }
                //устанавливаем в textView наши значения
                textView.setText(minutesString + ":" + secondsString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
