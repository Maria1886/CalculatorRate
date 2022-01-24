package com.example.calculatorrate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseSQLIte extends SQLiteOpenHelper {


    private Bitmap btm;
    private static final String DB_NAME = "CalculatorRate";
    private  static final int DB_VERSION = 1;

    private static final String TABLE_INREGISTRARE = "inregistrare";
    private static final String TABLE_AUTENTIFICARE = "autentificare";
    private static final String TABLE_CREDIT = "credit";


    private static final String ID_AUTENTIFICARE = "id";
    private static final String EMAIL_AUTENTIFICARE = "email";
    private static final String PAROLA_AUTENTIFICARE = "parola";



    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PAROLA = "parola";
    private static final String NUME = "nume";
    private static final String IMAGINE = "imagine";


    private static final String ID_CREDIT = "id";
    private static final String SALARIU = "salariu";
    private static final String NR_RATE = "rate";
    private static final String CREDIT = "credit";

    public static final String SQL_TABLE_CREDIT = " CREATE TABLE " + TABLE_CREDIT+
            " ( "
            +ID_CREDIT +" INTEGER PRIMARY KEY ,"
            +SALARIU + " TEXT , "
            +NR_RATE + " TEXT , "
            +CREDIT + " TEXT "
            + " ) ";



    public static final String SQL_TABLE_INREGISTRARE = " CREATE TABLE " + TABLE_INREGISTRARE +
        " ( "
        +ID +" INTEGER PRIMARY KEY ,"
        +EMAIL + " TEXT , "
        +PAROLA + " TEXT , "
        +NUME + " TEXT "
        + " ) ";




public static final String SQL_TABLE_AUTENTIFICARE = " CREATE TABLE "+ TABLE_AUTENTIFICARE+
        " ( "
        + ID_AUTENTIFICARE + " INTEGER PRIMARY KEY,"
        + EMAIL_AUTENTIFICARE + " TEXT, "
        + PAROLA_AUTENTIFICARE + " TEXT "
        + " ) ";


public DatabaseSQLIte(Context context){
    super(context,DB_NAME,null,DB_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABLE_AUTENTIFICARE);
        db.execSQL(SQL_TABLE_INREGISTRARE);
        db.execSQL(SQL_TABLE_CREDIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS '" + TABLE_AUTENTIFICARE +"'");
            db.execSQL(" DROP TABLE IF EXISTS '" + TABLE_INREGISTRARE +"'");
            db.execSQL(" DROP TABLE IF EXISTS '" + TABLE_CREDIT +"'");

    }


    public void adaugareCredit(Credite credite){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues =  new ContentValues();
        contentValues.put(SALARIU,credite.salariu);
        contentValues.put(NR_RATE,credite.nrRate);
        contentValues.put(CREDIT,credite.credit);
        db.insert(TABLE_CREDIT,null,contentValues);

    }


    public void adaugareUtilizator(Utilizator utilizator){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();

        contentValues.put(EMAIL,utilizator.email);
        contentValues.put(PAROLA,utilizator.parola);
        contentValues.put(NUME,utilizator.nume);
       // contentValues.put(IMAGINE,utilizator.imagine);

        db.insert(TABLE_INREGISTRARE,null,contentValues);


        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(EMAIL,utilizator.email);
        contentValues1.put(PAROLA,utilizator.parola);

        db.insert(TABLE_AUTENTIFICARE,null,contentValues1);
    }


    public Utilizator autentificareUtilizator(Utilizator utilizator){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_AUTENTIFICARE,new String[]{ID_AUTENTIFICARE,EMAIL_AUTENTIFICARE,PAROLA_AUTENTIFICARE}
        ,EMAIL_AUTENTIFICARE+"=?",new String[]{utilizator.email,},null,null,null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0){
            Utilizator utilizator1 = new Utilizator(cursor.getString(0),cursor.getString(1));
            return utilizator1;

        }

        return null;

    }


    public Bitmap afisareImagine(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select imagine from inregistrare",null);

        if(c.moveToNext()){
            byte[] image = c.getBlob(0);
            btm = BitmapFactory.decodeByteArray(image,0, image.length);
        }
        return btm;
    }


    public int getCredit(){
        SQLiteDatabase db = this.getReadableDatabase();
        int credit = 0;

        Cursor cursor = db.rawQuery(" select credit from "+TABLE_CREDIT,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            credit = cursor.getInt(cursor.getColumnIndex("credit"));
            cursor.moveToNext();
        }
        cursor.close();
        return credit;
    }


    public int getNrRate2(){
        SQLiteDatabase db = this.getReadableDatabase();


       int nrRate = 0;
        Cursor cursor = db.rawQuery(" select rate from "+TABLE_CREDIT,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            nrRate = cursor.getInt(cursor.getColumnIndex("rate"));
            cursor.moveToNext();
        }

        cursor.close();
        return nrRate;

    }



    public ArrayList getNrRate(){
        SQLiteDatabase db = this.getReadableDatabase();


        ArrayList<Integer> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery(" select rate from "+TABLE_CREDIT+" order by  id desc ",null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
           arrayList.add(cursor.getInt(cursor.getColumnIndex("rate")));
            cursor.moveToNext();
        }

        cursor.close();
        return arrayList;

    }


    public int getSalariu(){
        SQLiteDatabase db = this.getReadableDatabase();

        int salariu = 0;

        Cursor cursor = db.rawQuery(" select salariu from "+TABLE_CREDIT,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            salariu = cursor.getInt(cursor.getColumnIndex("salariu"));
            cursor.moveToNext();
        }
        cursor.close();
        return salariu;
    }
















}
