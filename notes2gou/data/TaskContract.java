package com.ivanfrescas.notes2gou.data;

import android.provider.BaseColumns;

public class TaskContract {

    //Each of xxxEntry corresponds to a table in the database.
    public class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME ="tasks";
        public static final String COLUMN_TASK = "task";
    }

}
