package com.example.pc080.iot_proj;

import android.provider.BaseColumns;

/**
 * Created by pc0805 on 2017/5/4.
 */

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_HUMI_THR = "humi_thr";
        public static final String COLUMN_TEMP_THR = "temp_thr";
    }
}
