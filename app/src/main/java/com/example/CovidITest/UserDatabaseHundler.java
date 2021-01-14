package com.example.CovidITest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDatabaseHundler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager";
    private static final String TABLE_USER = "User";
    private static final String CG = "genre";
    private static final String CA = "age";
    private static final String Q1 = "Q1";
    private static final String Q2 = "Q2";
    private static final String Q3 = "Q3";
    private static final String Q4 = "Q4";
    private static final String Q5 = "Q5";
    private static final String R_C_DB = "create table "+TABLE_USER+" ("+CG+" integer not null, "+CA+" integer not null, "+Q1+" integer not null, "+Q2+" integer not null, "+Q3+" integer not null, "+Q4+" integer not null, "+Q5+" integer not null);";

    public UserDatabaseHundler(Context context, String nom, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, nom, cursorFactory, version);
    }

    public UserDatabaseHundler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(R_C_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_USER+";");
        onCreate(db);
    }

    public void insertSurvey(User user){
        SQLiteDatabase maDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CG, user.getGenre());
        values.put(CA, user.age);
        values.put(Q1, user.q1);
        values.put(Q2, user.q2);
        values.put(Q3, user.q3);
        values.put(Q4, user.q4);
        values.put(Q5, user.q5);

        maDB.insert(TABLE_USER, null, values);
        maDB.close();
    }

    public ArrayList<User> getAllUsers(){
        SQLiteDatabase maDB = this.getReadableDatabase();

        Cursor c = maDB.query(TABLE_USER, new String[]{CG,CA,Q1,Q2,Q3,Q4,Q5}, null, null, null, null, null);
        return cursorToUsers(c);
    }


    private ArrayList<User> cursorToUsers(Cursor c){
        if (c.getCount()==0){
            return new ArrayList<User>(0);
        }
        ArrayList<User> retUser = new ArrayList<User>(c.getCount());
        c.moveToFirst();
        do {
            User user = new User();
            user.setGenre(c.getInt(0));
            user.setAge(c.getInt(1));
            user.setQ1(c.getInt(2));
            user.setQ2(c.getInt(3));
            user.setQ3(c.getInt(4));
            user.setQ4(c.getInt(5));
            user.setQ5(c.getInt(6));
            retUser.add(user);
        }while (c.moveToNext());
        c.close();
        return retUser;
    }

    public String statics (){
        int infected = 0, fInfected = 0, age15 = 0, age49 = 0, age64 = 0, age65 = 0;
        ArrayList<User> myArrayList = getAllUsers();
        for (User user: myArrayList) {
            int i = 0;
            if (user.q1==1){
                i++;
            }
            if (user.q2==1){
                i++;
            }
            if (user.q3==1){
                i++;
            }
            if (user.q4==1){
                i++;
            }
            if (user.q5==1){
                i++;
            }
            if (i>2){
                infected++;
                if (user.genre==1){
                    fInfected++;
                }
                if (user.age==0){
                    age15++;
                }else if (user.age==1){
                    age49++;
                }else if (user.age==2){
                    age64++;
                }else {
                    age65++;
                }
            }
        }

        return "\nNombre des personnes infecte: "+infected+"/"+myArrayList.size()+"\nSelon le sexe : Homme: "+(infected-fInfected)+"/ Femme: "+fInfected+"\nSelon l'age:: \n"+age15+" = 15- ans\n"+age49+" = 15-49 ans\n"+age64+" = 50-64 ans\n"+age65+" = 64++ ans\n";
    }




}
