package com.example.pc080.iot_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

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


        humiSeekbar = (SeekBar) findViewById(R.id.humi_seekbar);
        tempSeekbar = (SeekBar) findViewById(R.id.temp_seekbar);
        humiThrText = (TextView) findViewById(R.id.humi_thr);
        tempThrText = (TextView) findViewById(R.id.temp_thr);

        humiSeekbar.setMax(60);//20+60 = 80, so 80 is max humidity
        humiSeekbar.setOnSeekBarChangeListener(new humiSeekBarListener());
        tempSeekbar.setMax(50);
        tempSeekbar.setOnSeekBarChangeListener(new tempSeekBarListener());

    }

    private class humiSeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser)
            {

                    humiSeekProgress = progress;
                humiThrText.setText(""+ (humiSeekProgress+20) + "%");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            humiThrText.setText(""+ (humiSeekProgress+20 + "%"));
        }
    }

    private class tempSeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser)
            {
                tempSeekProgress = progress;
                tempThrText.setText(""+ tempSeekProgress + " Celsius");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            tempThrText.setText(""+ tempSeekProgress + " Celsius");
        }
    }




}
