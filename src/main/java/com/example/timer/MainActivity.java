package com.example.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private TextView textView;
    private boolean isTimerOn;
    private Button button;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);

        seekbar.setMax(600);
        seekbar.setProgress(30);//изначально установили на 30
        isTimerOn = false;

        button = findViewById(R.id.button);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

              long progressInMillis = progress * 1000;
              updateTimer(progressInMillis);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void start(View view) {

        if (!isTimerOn){
            button.setText("Stop");
            seekbar.setEnabled(false);//чтобы пользователь не смог двигать ползунок сам
            isTimerOn = true;

            CountDownTimer countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000,1000){
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer( millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer  = MediaPlayer.create(getApplicationContext(),R.raw.bell_sound);
                    mediaPlayer.start();
                    resetTimer();
                }
            };
        }else {
            resetTimer();
        }
    }

    private void updateTimer(long millisUntilFinished){

        int minutes = (int)millisUntilFinished/1000/60;
        int seconds = (int)millisUntilFinished/1000 - (minutes*60);

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

    private void resetTimer(){
        countDownTimer.cancel();//останавливаем таймер
        textView.setText("00:30");
        button.setText("Start");
        seekbar.setEnabled(true);
        seekbar.setProgress(30);
        isTimerOn = false;
    }
}
