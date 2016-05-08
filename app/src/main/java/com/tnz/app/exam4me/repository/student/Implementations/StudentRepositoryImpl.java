package com.tnz.app.exam4me.repository.student.Implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tnz.app.exam4me.conf.databases.DatabaseConstants;
import com.tnz.app.exam4me.domain.student.NonResidentStudent;
import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.repository.student.StudentRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/23.
 */

public class StudentRepositoryImpl extends SQLiteOpenHelper implements StudentRepository {

    private SQLiteDatabase sqLiteDB;
    private Cursor cursor;
    public static final String TABLE_STUDENT = "student";

    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_NUMBER = "studNum";
    public static final String COLUMN_STUDENT_NAME = "studName";
    public static final String COLUMN_STUDENT_FACULTY = "studFaculty";

    // Database Creation
    private static final String CREATE_DATABASE = " CREATE TABLE " + TABLE_STUDENT
            + " (" + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STUDENT_NUMBER + " TEXT UNIQUE NOT NULL, "
            + COLUMN_STUDENT_NAME + " TEXT NOT NULL, "
            + COLUMN_STUDENT_FACULTY + " TEXT);";

    public StudentRepositoryImpl(Context context){
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    public void openWritableDBConnection() throws SQLException{
        sqLiteDB = super.getWritableDatabase();
    }

    public void openReadableDBConnection() throws SQLException{
        sqLiteDB = super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    @Override
    public Student findById(Long id) {

        openReadableDBConnection();

        cursor = sqLiteDB.query(
                TABLE_STUDENT,
                new String[]
                        {COLUMN_STUDENT_ID,
                                COLUMN_STUDENT_NUMBER,
                                COLUMN_STUDENT_NAME},
                COLUMN_STUDENT_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,null,null,null
        );

        if (cursor.moveToFirst()){
            return new NonResidentStudent.Builder()
                    .studentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NUMBER)))
                    .studentName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_STUDENT_ID)))
                    .build();
        }
        else
            return null;
    }

    @Override
    public Set<Student> findAll() {
        openReadableDBConnection();

        Set<Student> students = new HashSet<>();

        cursor = sqLiteDB.query(TABLE_STUDENT,null,null,null,null,null,null);

        if (cursor.moveToFirst()) {

            do {

                final Student student = new NonResidentStudent.Builder()
                        .studentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NUMBER)))
                        .studentName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)))
                        .build();

                students.add(student);

            }while(cursor.moveToNext());
        }

        return students;
    }

    @Override
    public int deleteAll() {

        openWritableDBConnection();

        int rowsDeleted = sqLiteDB.delete(
                TABLE_STUDENT,
                null,
                null
        );

        closeDBConnection();

        return rowsDeleted;
    }

    @Override
    public Student delete(Student entity) {

        openWritableDBConnection();
        sqLiteDB.delete(
                TABLE_STUDENT,
                COLUMN_STUDENT_NUMBER + " =? ",
                new String[]{String.valueOf(entity.getStudentNumber())}
        );

        return entity;
    }

    @Override
    public Student update(Student entity) {
        openWritableDBConnection();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_NUMBER, String.valueOf(entity.getStudentNumber()));
        contentValues.put(COLUMN_STUDENT_NAME, entity.getStudentName());
        //contentValues.put(COLUMN_STUDENT_FACULTY, entity.getFacultyName());

        sqLiteDB.update(
                TABLE_STUDENT,
                contentValues,
                COLUMN_STUDENT_NUMBER + " =? ",
                new String[]{String.valueOf(entity.getStudentNumber())}
        );

        return entity;
    }

    @Override
    public Student insert(Student entity) {

        openWritableDBConnection();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_NUMBER, entity.getStudentNumber());
        contentValues.put(COLUMN_STUDENT_NAME, entity.getStudentName());
        //contentValues.put(COLUMN_STUDENT_FACULTY, entity.getFacultyName());

        long id = sqLiteDB.insertOrThrow(TABLE_STUDENT, null, contentValues);

        return new NonResidentStudent.Builder()
                .copyNonResidentStudent((NonResidentStudent) entity)
                .id(id)
                .build();
    }

    public void closeDBConnection() throws SQLException{
        this.close();
    }

}
