package com.projectcgpa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.projectcgpa.entities.Grade;
import com.projectcgpa.entities.User;

public class DBHelper extends SQLiteOpenHelper {
    public static final String database_name = "cgpa";

    // user table
    public static final String user_table = "user";
    public static final String user_id = "Student_Id";
    public static final String user_fullname = "Fullname";
    public static final String user_email = "Email";
    public static final String user_password = "Password";

    // grade table
    public static final String grade_table = "grade";
    public static final String grade_id = "gradeId";
    public static final String grade_grade = "gradeName";
    public static final String grade_point = "point";

    // course table
    public static final String course_table = "course";
    public static final String course_id = "courseCode";
    public static final String course_name = "courseName";
    public static final String course_creditHrs = "creditHours";

    // register course table
    public static final String regSem_table = "Semester";
    public static final String regSem_id = "_id";
    public static final String regSem_name = "SemesterName";
    public static final String regSem_StudentId = "Student_Id";
    public static final String regSem_courseCode = "courseCode";
    public static final String regSem_gradeId = "gradeId";


    private final SQLiteDatabase db;

    // constructor
    public DBHelper( Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + user_table
                + "("
                + user_id + " TEXT, "
                + user_fullname + " TEXT, "
                + user_email + " TEXT, "
                + user_password + " TEXT)";

        String query2 = "CREATE TABLE " + grade_table
                + "("
                + grade_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + grade_grade + " TEXT, "
                + grade_point + " REAL)";

        String query3 = "CREATE TABLE " + course_table
                + "("
                + course_id + " TEXT, "
                + course_name + " TEXT, "
                + course_creditHrs + "REAL)";

        String query4 = "CREATE TABLE " + regSem_table
                + "("
                + regSem_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + regSem_name + " TEXT, "
                + regSem_StudentId + " TEXT references " + user_table + "("+user_id+"),"
                + regSem_courseCode + "TEXT references " + course_table + "("+course_id+"),"
                + regSem_gradeId + " INTEGER references " + grade_table + "(" + grade_id +")"
                + ")";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + user_table);
        db.execSQL("DROP TABLE IF EXISTS " + grade_table);
        db.execSQL("DROP TABLE IF EXISTS " + course_table);
        db.execSQL("DROP TABLE IF EXISTS " + regSem_table);
    }

    // Register new User
    public void registerUser(ContentValues values){
        db.insert(user_table, null, values);
    }

    // check User to Login
    public boolean checkUser(String email, String password) {
        String[] columns =  {user_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = user_email + "=?" + " and " + user_password + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(user_table, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }

    // check user after login then save the information to User Java class
    public User login(String email, String password) {
        User user = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor =sqLiteDatabase.rawQuery("select * from " + user_table + " where email = ? AND password = ?", new String[]{email, password});
            if (cursor.moveToFirst()) {
                user = new User();
                user.setStudent_Id(cursor.getLong(0));
                user.setFullname(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));

            }
        }catch (Exception e){
            user = null;
        }
        return user;
    }

}
