package com.tnz.app.exam4me.repository.lecturer.Implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tnz.app.exam4me.conf.databases.DatabaseConstants;
import com.tnz.app.exam4me.domain.institute.Faculty;
import com.tnz.app.exam4me.domain.institute.Lecturer;
import com.tnz.app.exam4me.repository.lecturer.LecturerRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class LecturerRepositoryImpl extends SQLiteOpenHelper implements LecturerRepository {

    //SQLite object
    private SQLiteDatabase sqliteDB;
    private Cursor cursor;
    private ContentValues contentValues;

    //CREATE TABLE
    private final String TABLE_LECTURER = "lecturer";
    private final String COLUMN_ID = "id";
    private final String COLUMN_LECTURER_NAME = "lecName";
    private final String COLUMN_LECTURER_ROOM = "lecRoom";
    private final String COLUMN_LECTURER_FACULTY = "lecFaculty";

    //CREATE DATABASE
    private final String CREATE_LECTURER_TABLE = " CREATE TABLE "
            + TABLE_LECTURER + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LECTURER_NAME + " TEXT NOT NULL, "
            + COLUMN_LECTURER_ROOM + " TEXT NOT NULL, "
            + COLUMN_LECTURER_FACULTY + " TEXT NOT NULL);";

    public LecturerRepositoryImpl(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    public void openWritableDBConnection() throws SQLException {
        sqliteDB = super.getWritableDatabase();
    }

    public void openReadableDBConnection() throws SQLException {
        sqliteDB = super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LECTURER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public Lecturer findById(Long id) {

        openReadableDBConnection();

        cursor = sqliteDB.query(
                TABLE_LECTURER,
                new String[]{
                        COLUMN_ID,
                        COLUMN_LECTURER_NAME,
                        COLUMN_LECTURER_ROOM,
                        COLUMN_LECTURER_FACULTY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            return new Lecturer.Builder()
                    .assignName(cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER_NAME)))
                    .assignRoomNumber(cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER_ROOM)))
                    .assignFaculty(new Faculty.Builder().assignFacultyName(cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER_FACULTY))).build())
                    .assignId(id)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public Lecturer insert(Lecturer entity) {
        openWritableDBConnection();

        contentValues = new ContentValues();

        contentValues.put(COLUMN_LECTURER_NAME, entity.getName());
        contentValues.put(COLUMN_LECTURER_ROOM, entity.getRoomNumber());
        contentValues.put(COLUMN_LECTURER_FACULTY, entity.getFaculty().getFacultyName());

        long id = sqliteDB.insertOrThrow(TABLE_LECTURER, null, contentValues);

        return new Lecturer.Builder()
                .copyLecturer(entity)
                .assignId(id)
                .build();
    }

    @Override
    public Lecturer update(Lecturer entity) {

        openWritableDBConnection();

        contentValues = new ContentValues();

        contentValues.put(COLUMN_LECTURER_NAME, entity.getName());
        contentValues.put(COLUMN_LECTURER_ROOM, entity.getRoomNumber());
        contentValues.put(COLUMN_LECTURER_FACULTY, entity.getFaculty().getFacultyName());

        sqliteDB.update(
                TABLE_LECTURER,
                contentValues,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public Lecturer delete(Lecturer entity) {
        openWritableDBConnection();

        sqliteDB.delete(
                TABLE_LECTURER,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public Set<Lecturer> findAll() {

        openReadableDBConnection();

        Set<Lecturer> lecturers = new HashSet<>();

        cursor = sqliteDB.query(
                TABLE_LECTURER, null, null, null, null, null, null
        );

        if (cursor.moveToFirst()) {

            do {

                Lecturer lecturer = new Lecturer.Builder()
                        .assignName(cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER_NAME)))
                        .assignRoomNumber(cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER_ROOM)))
                        .assignFaculty(new Faculty.Builder().assignFacultyName(cursor.getString(cursor.getColumnIndex(COLUMN_LECTURER_FACULTY))).build())
                        .build();

                lecturers.add(lecturer);

            } while (cursor.moveToNext());

        }

        return lecturers;
    }

    @Override
    public int deleteAll() {

        openWritableDBConnection();

        int rowsDeleted = sqliteDB.delete(
                TABLE_LECTURER, null, null
        );

        closeDBConnection();

        return rowsDeleted;
    }

    public void closeDBConnection(){
        this.close();
    }
}
