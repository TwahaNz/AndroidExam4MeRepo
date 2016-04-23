package com.tnz.app.exam4me.conf.databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tnz.app.exam4me.conf.Utilities.DatabaseTables;

/**
 * Created by Admin on 2016/04/23.
 */

public class DatabaseCreate extends SQLiteOpenHelper {

    //Singleton
    private static DatabaseCreate databaseCreateTables;

    //SQLite
    private static SQLiteDatabase sqLiteDB;

    //STUDENT TABLE
    public static final String TABLE_STUDENT = "student";

    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_NUMBER = "studNum";
    public static final String COLUMN_STUDENT_NAME = "studName";

    // Database Creation
    private static final String CREATE_DATABASE_TABLE_STUDENT = " CREATE TABLE " + TABLE_STUDENT
            + " (" + COLUMN_STUDENT_ID + " INTERGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STUDENT_NUMBER + " TEXT , "
            + COLUMN_STUDENT_NAME + " TEXT NOT NULL );";
    //END OF STUDENT TABLE

    private DatabaseCreate(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        sqLiteDB = super.getWritableDatabase();
    }

    public static DatabaseCreate getInstance(Context context){

        if (databaseCreateTables == null){
            databaseCreateTables = new DatabaseCreate(context);
        }

        return databaseCreateTables;
    }

    private void openWritableDBConnection() throws SQLException{
        sqLiteDB = super.getWritableDatabase();
    }

    private void openReadableDBConnection() throws SQLException{
        sqLiteDB = super.getReadableDatabase();
    }

    public void createTable(DatabaseTables table){
        openWritableDBConnection();

        switch (table){
            case STUDENT: sqLiteDB.execSQL(CREATE_DATABASE_TABLE_STUDENT);
                default:
        }
    }

    public void dropAllTables() throws Exception{
        openWritableDBConnection();
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        databaseCreateTables = null;
    }

    public void dropTable(DatabaseTables table) throws SQLException{

        openWritableDBConnection();

        switch (table){
            case STUDENT:   sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
            default:
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqLiteDB = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public void closeDatabaseConnection(){
        this.close();
    }
}
