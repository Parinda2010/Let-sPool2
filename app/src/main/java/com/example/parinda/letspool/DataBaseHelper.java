package com.example.parinda.letspool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Parinda on 01-01-2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="user_det1.db";
    private static final String TABLE_NAME="REGISTER";
    private static final int DATABASE_VERSION=2;

    private static final String _ID="id";
    private static final String NAME="Name";
    private static final String EMAIL="Email";
    private static final String CNO="Contact_No";
    private static final String DOB="dob";
    private static final String PASSWORD="password";

    //SQLiteDatabase db ;

//    private static final String CREATE_TABLE="CREATE TABLE " +TABLE_NAME+ "("+_ID+" INTEGER PRIMARY KEY ,"+NAME+" TEXT ,"
   //         +EMAIL+" TEXT,"+CNO+" TEXT,"+ DOB +" TEXT,"+PASSWORD+" TEXT);";
    private static final String CREATE_TABLE="Create table REGISTER(id integer primary key auto increment,Name text,Email text,Contact_No text,dob text,password text)";

    private static final String DROP_TABLE="DROP TABLE IF EXIST "+TABLE_NAME;

    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertData(String u_name,String u_email,String u_cno,String u_dob,String u_password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(NAME,u_name);
        contentValues.put(EMAIL,u_email);
        contentValues.put(CNO,u_cno);
        contentValues.put(DOB,u_dob);
        contentValues.put(PASSWORD,u_password);

        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }


    public String search(String email)
    {
      SQLiteDatabase db=this.getWritableDatabase();
        String SELECT_QUERY="SELECT "+EMAIL+","+PASSWORD+" FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(SELECT_QUERY,null);

        String e,p;
        p="not found";
        if(cursor.moveToFirst()){
            do{
                e=cursor.getString(0);
                if(e.equals(email)) {
                    p = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return p;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(DROP_TABLE);
            this.onCreate(db);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
