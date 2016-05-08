package com.tnz.app.exam4me.repository.registration.Implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tnz.app.exam4me.conf.databases.DatabaseConstants;
import com.tnz.app.exam4me.domain.results.StudentExamResults;
import com.tnz.app.exam4me.domain.settings.Registration;
import com.tnz.app.exam4me.domain.student.NonResidentStudent;
import com.tnz.app.exam4me.domain.student.Student;
import com.tnz.app.exam4me.repository.registration.RegistrationRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/05/08.
 */
public class RegistrationRepositoryImpl extends SQLiteOpenHelper implements RegistrationRepository{

    private SQLiteDatabase sqLiteDb;
    private Cursor cursor;
    private ContentValues contentValues;


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

    public RegistrationRepositoryImpl(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    public void openWritableDBConnection() throws SQLException {
        sqLiteDb = super.getWritableDatabase();
    }

    public void openReadableDBConnection() throws SQLException{
        sqLiteDb = super.getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        sqLiteDb = db;
        sqLiteDb.execSQL(CREATE_DATABASE_TABLE_REGISTRATION);
    }

    @Override
    public Registration findById(Long id) {

        openReadableDBConnection();

        cursor = sqLiteDb.query(
                TABLE_REGISTRATION,
                new String[]
                        {COLUMN_REG_ID,
                                COLUMN_REG_NUMBER,
                                COLUMN_REG_EMAIL,
                                COLUMN_REG_CODE},
                COLUMN_REG_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,null,null,null
        );

        if (cursor.moveToFirst()){
            return new Registration.Builder()
                    .assignId(cursor.getLong(cursor.getColumnIndex(COLUMN_REG_ID)))
                    .assignStudentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REG_NUMBER)))
                    .assignStudentEmail(cursor.getString(cursor.getColumnIndex(COLUMN_REG_EMAIL)))
                    .assignSecrectCode(cursor.getString(cursor.getColumnIndex(COLUMN_REG_CODE)))
                    .build();
        }
        else
            return null;
    }

    @Override
    public Student findByDetails(String studNumber, String email) {

        openReadableDBConnection();


        cursor = sqLiteDb.query(
                TABLE_REGISTRATION,
                new String[]
                        {COLUMN_REG_ID,
                                COLUMN_REG_NUMBER,
                                COLUMN_REG_EMAIL,
                                COLUMN_REG_CODE},
                COLUMN_REG_NUMBER + " =? " + " AND " + COLUMN_REG_EMAIL + " =? ",
                new String[]{studNumber, email},
                null,null,null,null
        );

        if (cursor.moveToFirst()){
            return new NonResidentStudent.Builder()
                    .studentName(studNumber)
                    .build();
        }

        return null;
    }

    @Override
    public Registration insert(Registration entity) {

        openWritableDBConnection();

        contentValues = new ContentValues();

        contentValues.put(COLUMN_REG_NUMBER, entity.getStudentNumber());
        contentValues.put(COLUMN_REG_EMAIL, entity.getStudentEmail());
        contentValues.put(COLUMN_REG_CODE, entity.getSecretCode());

        long id = sqLiteDb.insertOrThrow(TABLE_REGISTRATION, null, contentValues);

        return new Registration.Builder()
                .copyRegistration(entity)
                .assignId(id)
                .build();
    }

    @Override
    public Registration update(Registration entity) {

        openWritableDBConnection();

        contentValues = new ContentValues();

        contentValues.put(COLUMN_REG_NUMBER, entity.getStudentNumber());
        contentValues.put(COLUMN_REG_EMAIL, entity.getStudentEmail());
        contentValues.put(COLUMN_REG_CODE, entity.getSecretCode());

        sqLiteDb.update(TABLE_REGISTRATION,contentValues,COLUMN_REG_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Registration delete(Registration entity) {

        openWritableDBConnection();

        sqLiteDb.delete(
                TABLE_REGISTRATION,
                COLUMN_REG_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public Set<Registration> findAll() {
        openReadableDBConnection();

        Set<Registration> registrations = new HashSet<>();

        cursor = sqLiteDb.query(TABLE_REGISTRATION,null,null,null,null,null,null);

        if (cursor.moveToFirst()) {

            do {

                final Registration reg = new Registration.Builder()
                        .assignId(cursor.getLong(cursor.getColumnIndex(COLUMN_REG_ID)))
                        .assignStudentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REG_NUMBER)))
                        .assignStudentEmail(cursor.getString(cursor.getColumnIndex(COLUMN_REG_EMAIL)))
                        .assignSecrectCode(cursor.getString(cursor.getColumnIndex(COLUMN_REG_CODE)))
                        .build();

                registrations.add(reg);

            }while(cursor.moveToNext());
        }

        return registrations;
    }

    @Override
    public int deleteAll() {

        openWritableDBConnection();

        int rowsDeleted = sqLiteDb.delete(
                TABLE_REGISTRATION,
                null,
                null
        );

        closeDBConnection();

        return rowsDeleted;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        onCreate(db);
    }

    public void closeDBConnection() {
        this.close();
    }
}
