package com.tnz.app.exam4me.conf.databases;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.tnz.app.exam4me.conf.Utilities.DatabaseTables;

import java.io.File;

/**
 * Created by Admin on 2016/04/23.
 */

public class DatabaseCreate extends SQLiteOpenHelper {

    //Singleton
    private static DatabaseCreate databaseCreateTables;

    private Context context;

    //SQLite
    private static SQLiteDatabase sqLiteDB;


    //STUDENT REGISTRATION
    public static final String TABLE_REGISTRATION = "registration";

    public static final String COLUMN_REG_ID = "id";
    public static final String COLUMN_REG_NUMBER = "studNum";
    public static final String COLUMN_REG_EMAIL = "studEmail";
    public static final String COLUMN_REG_CODE = "secretCode";

    // Database Creation
    private static final String CREATE_DATABASE_TABLE_REGISTRATION = " CREATE TABLE " + TABLE_REGISTRATION
            + " (" + COLUMN_REG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_REG_NUMBER + " TEXT UNIQUE NOT NULL, "
            + COLUMN_REG_EMAIL + " TEXT NOT NULL, "
            + COLUMN_REG_CODE + " TEXT );";
    //END OF REGISTRATION TABLE

    //STUDENT TABLE
    public static final String TABLE_STUDENT = "student";

    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_NUMBER = "studNum";
    public static final String COLUMN_STUDENT_NAME = "studName";
    public static final String COLUMN_STUDENT_FACULTY = "studFaculty";

    // Database Creation
    private static final String CREATE_DATABASE_TABLE_STUDENT = " CREATE TABLE " + TABLE_STUDENT
            + " (" + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STUDENT_NUMBER + " TEXT UNIQUE NOT NULL, "
            + COLUMN_STUDENT_NAME + " TEXT NOT NULL, "
            + COLUMN_STUDENT_FACULTY + " TEXT );";
    //END OF STUDENT TABLE

    //LECTURER TABLE

    //CREATE TABLE
    private final String TABLE_LECTURER = "lecturer";
    private final String COLUMN_ID = "id";
    private final String COLUMN_LECTURER_NAME = "lecName";
    private final String COLUMN_LECTURER_ROOM = "lecRoom";
    private final String COLUMN_LECTURER_FACULTY = "lecFaculty";

    //CREATE DATABASE
    private final String CREATE_DATABASE_TABLE_LECTURER = " CREATE TABLE "
            + TABLE_LECTURER + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LECTURER_NAME + " TEXT NOT NULL, "
            + COLUMN_LECTURER_ROOM + " TEXT NOT NULL, "
            + COLUMN_LECTURER_FACULTY + " TEXT NOT NULL);";

    //END OF LECTURER TABLE

    //CREATE TABLE
    private final String TABLE_EXAM = "exam";

    private final String COLUMN_EXAM_TERM = "term";
    private final String COLUMN_TERM_ONE = "term_one";
    private final String COLUMN_TERM_TWO = "term_two";
    private final String COLUMN_TERM_THREE = "term_three";
    private final String COLUMN_TERM_FOUR = "term_four";

    private final String CREATE_DATABASE_TABLE_EXAM = " CREATE TABLE "
            + TABLE_EXAM
            + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STUDENT_NUMBER + " TEXT NOT NULL, "
            + COLUMN_EXAM_TERM + " TEXT, "
            + COLUMN_TERM_ONE + " TEXT, "
            + COLUMN_TERM_TWO + " TEXT, "
            + COLUMN_TERM_THREE + " TEXT, "
            + COLUMN_TERM_FOUR + " TEXT);";
    //END OF CREATE TABLE

    private DatabaseCreate(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        sqLiteDB = super.getWritableDatabase();
        this.context = context;
    }

    public static DatabaseCreate getInstance(Context context) {

        if (databaseCreateTables == null) {
            databaseCreateTables = new DatabaseCreate(context);
        }

        return databaseCreateTables;
    }

    private void openWritableDBConnection() throws SQLException {
        sqLiteDB = super.getWritableDatabase();
    }

    private void openReadableDBConnection() throws SQLException {
        sqLiteDB = super.getReadableDatabase();
    }

    public void createAllTables() {
        openWritableDBConnection();

        dropAllTables();

        sqLiteDB.execSQL(CREATE_DATABASE_TABLE_STUDENT);
        sqLiteDB.execSQL(CREATE_DATABASE_TABLE_LECTURER);
        sqLiteDB.execSQL(CREATE_DATABASE_TABLE_EXAM);
        sqLiteDB.execSQL(CREATE_DATABASE_TABLE_REGISTRATION);
    }

    public void createTable(DatabaseTables table) {
        openWritableDBConnection();

        switch (table) {
            case STUDENT:
                sqLiteDB.execSQL(CREATE_DATABASE_TABLE_STUDENT);
                break;
            case LECUTURER:
                sqLiteDB.execSQL(CREATE_DATABASE_TABLE_LECTURER);
                break;
            case RESULTS:
                sqLiteDB.execSQL(CREATE_DATABASE_TABLE_EXAM);
                break;
            case REGISTRATION:
                sqLiteDB.execSQL(CREATE_DATABASE_TABLE_REGISTRATION);
                break;
            default:
        }
    }

    public void dropAllTables() {
        openWritableDBConnection();
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_LECTURER);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        databaseCreateTables = null;
    }

    public void dropTable(DatabaseTables table) throws SQLException {

        openWritableDBConnection();

        switch (table) {
            case STUDENT:
                sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
                break;
            case LECUTURER:
                sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_LECTURER);
                break;
            case RESULTS:
                sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
                break;
            case REGISTRATION:
                sqLiteDB.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
            default:
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void deleteDatabase(){
        String fileLocation = context.getApplicationInfo().dataDir + "/databases/" + DatabaseConstants.DATABASE_NAME;

        if(new File(fileLocation).exists())
            SQLiteDatabase.deleteDatabase(new File(fileLocation));
        else{
            fileLocation += ".db";
            SQLiteDatabase.deleteDatabase(new File(fileLocation));
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LECTURER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        onCreate(db);
    }

    public void closeDatabaseConnection() {
        this.close();
    }
}
