package com.example.pc080.iot_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = SettingsActivity.class.getSimpleName();

    private SeekBar humiSeekbar;
    private SeekBar tempSeekbar;

    private Button btnConfirm;
    private Button btnCancel;

    private TextView humiThrText;
    private TextView tempThrText;

    private int def_humiThr=0;
    private int def_tempThr=0;
    private int humiThr=0;
    private int tempThr=0;
    private String deviceAddress;
    private int humiSeekProgress;
    private int tempSeekProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = this.getIntent();
        def_humiThr = intent.getIntExtra("humiThr", 0);//default
        def_tempThr = intent.getIntExtra("tempThr", 0);//default
        humiThr = intent.getIntExtra("humiThr", 0);
        tempThr = intent.getIntExtra("tempThr", 0);
        deviceAddress = intent.getStringExtra("deviceAddress");

        Log.d(TAG,"humiThr = " + def_humiThr);
        Log.d(TAG,"tempThr = " + def_tempThr);
        Log.d(TAG,"deviceAddress = " + deviceAddress);



        humiSeekbar = (SeekBar) findViewById(R.id.humi_seekbar);
        tempSeekbar = (SeekBar) findViewById(R.id.temp_seekbar);
        humiThrText = (TextView) findViewById(R.id.humi_thr);
        tempThrText = (TextView) findViewById(R.id.temp_thr);

        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("humiThr", humiThr);
                intent.putExtra("tempThr", tempThr);
                intent.putExtra("deviceAddress", deviceAddress);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("humiThr", def_humiThr);
                intent.putExtra("tempThr", def_tempThr);
                intent.putExtra("deviceAddress", deviceAddress);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("humiThr", def_humiThr);
        intent.putExtra("tempThr", def_tempThr);
        intent.putExtra("deviceAddress", deviceAddress);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }
}
