package com.ivanfrescas.notes2gou;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ivanfrescas.notes2gou.utlidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {




    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_TABLA_ALUMNO);
        db.execSQL(Utilidades.CREAR_TABLA_GRUPOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS alumnos");
        db.execSQL("DROP TABLE IF EXISTS grupos");
        onCreate(db);

    }
}
