package com.tnz.app.exam4me.repository.studentExamResults.Implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tnz.app.exam4me.conf.databases.DatabaseConstants;
import com.tnz.app.exam4me.domain.results.StudentExamResults;
import com.tnz.app.exam4me.repository.studentExamResults.StudentExamResultsRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class StudentExamResultsRepositoryImpl extends SQLiteOpenHelper implements StudentExamResultsRepository{

    //SQLite Objects
    private SQLiteDatabase sqliteDB;
    private Cursor cursor;
    private ContentValues contentValues;

    //CREATE TABLE
    private final String TABLE_EXAM = "exam";

    private final String COLUMN_ID = "id";
    private final String COLUMN_STUDENT_NUMBER = "studNum";
    private final String COLUMN_EXAM_TERM = "term";
    private final String COLUMN_TERM_ONE = "term_one";
    private final String COLUMN_TERM_TWO = "term_two";
    private final String COLUMN_TERM_THREE = "term_three";
    private final String COLUMN_TERM_FOUR = "term_four";

    private final String CREATE_TABLE_EXAM = " CREATE TABLE "
            + TABLE_EXAM
            + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STUDENT_NUMBER + " TEXT, "
            + COLUMN_EXAM_TERM + " TEXT, "
            + COLUMN_TERM_ONE + " TEXT, "
            + COLUMN_TERM_TWO + " TEXT, "
            + COLUMN_TERM_THREE + " TEXT, "
            + COLUMN_TERM_FOUR + " TEXT);";
    //END OF CREATE TABLE



    public StudentExamResultsRepositoryImpl(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    public void openWritableDBConnection() throws SQLException {
        sqliteDB = super.getWritableDatabase();
    }

    public void openReadableDBConnection() throws SQLException{
        sqliteDB = super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqliteDB = db;
        sqliteDB.execSQL(CREATE_TABLE_EXAM);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        onCreate(db);
    }

    @Override
    public StudentExamResults findById(Long id) {

        openReadableDBConnection();

        cursor = sqliteDB.query(
                TABLE_EXAM,
                new String[]
                        {COLUMN_ID,
                        COLUMN_STUDENT_NUMBER,
                        COLUMN_EXAM_TERM,
                        COLUMN_TERM_ONE,
                        COLUMN_TERM_TWO,
                        COLUMN_TERM_THREE,
                        COLUMN_TERM_FOUR},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,null,null,null
        );

        if (cursor.moveToFirst()){
            return new StudentExamResults.Builder()
                    .assignID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .assignStudentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NUMBER)))
                    .assignTerm(cursor.getInt(cursor.getColumnIndex(COLUMN_EXAM_TERM)))
                    .assignTermOne(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_ONE)))
                    .assignTermTwo(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_TWO)))
                    .assignTermThree(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_THREE)))
                    .assignTermFour(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_FOUR)))
                    .build();
        }
        else
            return null;
    }

    @Override
    public StudentExamResults findByDetails(String studentNumber) {

        openReadableDBConnection();

        cursor = sqliteDB.query(
                TABLE_EXAM,
                new String[]
                        {COLUMN_ID,
                                COLUMN_STUDENT_NUMBER,
                                COLUMN_EXAM_TERM,
                                COLUMN_TERM_ONE,
                                COLUMN_TERM_TWO,
                                COLUMN_TERM_THREE,
                                COLUMN_TERM_FOUR},
                COLUMN_STUDENT_NUMBER + " =? ",
                new String[]{studentNumber},
                null,null,null,null
        );

        if (cursor.moveToFirst()){
            return new StudentExamResults.Builder()
                    .assignID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .assignStudentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NUMBER)))
                    .assignTerm(cursor.getInt(cursor.getColumnIndex(COLUMN_EXAM_TERM)))
                    .assignTermOne(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_ONE)))
                    .assignTermTwo(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_TWO)))
                    .assignTermThree(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_THREE)))
                    .assignTermFour(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_FOUR)))
                    .build();
        }
        else
            return null;
    }

    @Override
    public StudentExamResults insert(StudentExamResults entity) {
        openWritableDBConnection();

        contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_NUMBER, entity.getStudentNumber());
        contentValues.put(COLUMN_EXAM_TERM, entity.getExamTerm());
        contentValues.put(COLUMN_TERM_ONE, entity.getTermOne());
        contentValues.put(COLUMN_TERM_TWO, entity.getTermTwo());
        contentValues.put(COLUMN_TERM_THREE, entity.getTermThree());
        contentValues.put(COLUMN_TERM_FOUR, entity.getTermFour());

        long id = sqliteDB.insertOrThrow(TABLE_EXAM, null, contentValues);

        return new StudentExamResults.Builder()
                .copyStudentExamResults(entity)
                .assignID(id)
                .build();
    }

    @Override
    public StudentExamResults update(StudentExamResults entity) {

        openWritableDBConnection();

        contentValues = new ContentValues();

        contentValues.put(COLUMN_STUDENT_NUMBER, entity.getStudentNumber());
        contentValues.put(COLUMN_EXAM_TERM, entity.getExamTerm());
        contentValues.put(COLUMN_TERM_ONE, entity.getTermOne());
        contentValues.put(COLUMN_TERM_TWO, entity.getTermTwo());
        contentValues.put(COLUMN_TERM_THREE, entity.getTermThree());
        contentValues.put(COLUMN_TERM_FOUR, entity.getTermFour());

        sqliteDB.update(TABLE_EXAM,contentValues,COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public StudentExamResults delete(StudentExamResults entity) {

        openWritableDBConnection();
        sqliteDB.delete(
                TABLE_EXAM,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public Set<StudentExamResults> findAll() {
        openReadableDBConnection();

        Set<StudentExamResults> studentExamResults = new HashSet<>();

        cursor = sqliteDB.query(TABLE_EXAM,null,null,null,null,null,null);

        if (cursor.moveToFirst()) {

            do {

                final StudentExamResults studentExamResult = new StudentExamResults.Builder()
                        .assignID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .assignStudentNumber(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NUMBER)))
                        .assignTerm(cursor.getInt(cursor.getColumnIndex(COLUMN_EXAM_TERM)))
                        .assignTermOne(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_ONE)))
                        .assignTermTwo(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_TWO)))
                        .assignTermThree(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_THREE)))
                        .assignTermFour(cursor.getInt(cursor.getColumnIndex(COLUMN_TERM_FOUR)))
                        .build();

                studentExamResults.add(studentExamResult);

            }while(cursor.moveToNext());
        }

        return studentExamResults;
    }

    @Override
    public int deleteAll() {

        openWritableDBConnection();

        int rowsDeleted = sqliteDB.delete(
                TABLE_EXAM,
                null,
                null
        );

        closeDBConnection();

        return rowsDeleted;
    }

    public void closeDBConnection(){
        this.close();
    }
}
