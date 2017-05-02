package com.example.pc080.iot_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = SettingsActivity.class.getSimpleName();

    private SeekBar humiSeekbar;
    private SeekBar tempSeekbar;

    private TextView humiThrText;
    private TextView tempThrText;

    private int humiThr=0;
    private int tempThr=0;
    private int humiSeekProgress;
    private int tempSeekProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = this.getIntent();
        humiThr = intent.getIntExtra("humiThr", 0);
        tempThr = intent.getIntExtra("tempThr", 0);

        Log.d(TAG,"humiThr = " + humiThr);
        Log.d(TAG,"tempThr = " + tempThr);



        humiSeekbar = (SeekBar) findViewById(R.id.humi_seekbar);
        tempSeekbar = (SeekBar) findViewById(R.id.temp_seekbar);
        humiThrText = (TextView) findViewById(R.id.humi_thr);
        tempThrText = (TextView) findViewById(R.id.temp_thr);

        humiSeekbar.setMax(60);//20+60 = 80, so 80 is max humidity
        humiSeekbar.setOnSeekBarChangeListener(new humiSeekBarListener());
        humiSeekbar.setProgress(humiThr-20);//change actual value to seekBar progress
        humiThrText.setText((humiThr) + "%");
        tempSeekbar.setMax(50);
        tempSeekbar.setProgress(tempThr);
        tempSeekbar.setOnSeekBarChangeListener(new tempSeekBarListener());
        tempThrText.setText(tempThr + "℃");

    }

    private class humiSeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser)
            {

                    humiSeekProgress = progress;    //0-60
                    humiThr = humiSeekProgress + 20;//20-80(actual range)
                humiThrText.setText(humiThr + "%");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            humiThr = humiSeekProgress + 20;//20-80(actual range)
            humiThrText.setText(humiThr + "%");
        }
    }

    private class tempSeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser)
            {
                tempSeekProgress = progress;
                tempThr = tempSeekProgress;
                tempThrText.setText(tempThr + "℃");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            tempThr = tempSeekProgress;
            tempThrText.setText(tempThr + "℃");
        }
    }




}
