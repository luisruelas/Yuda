package com.example.ruelas.yuda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ruelas on 20/02/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private final String tablename="downloadedArts";
    private final String tablenamemimagia="miMagia";
    private final String[]columns=new String[]{
            "_id","nombre","descripcion","precio","codigo","codigodescarga"
    };
    private String sql;
    private String sql2;
    public DatabaseHelper(Context context) {
        super(context, "Juda",null, 1);
        sql="CREATE TABLE "+tablename+" ("+columns[0]+ " INTEGER PRIMARY KEY, ";
        for(int i=1;i<columns.length;i++){
            if(i!=columns.length-1) {
                sql = sql + columns[i] + " TEXT, ";
            }else{
                sql = sql + columns[i] + " TEXT)";
            }
        }

        sql2="CREATE TABLE "+tablenamemimagia+" ("+columns[0]+ " INTEGER PRIMARY KEY, ";
        for(int i=1;i<columns.length;i++){
            if(i!=columns.length-1) {
                sql2 = sql2 + columns[i] + " TEXT, ";
            }else{
                sql2 = sql2 + columns[i] + " TEXT)";
            }
        }
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("xyzONCREATE",sql2);
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+tablename);
        onCreate(getWritableDatabase());
    }
    public void addRegister(RowCatalogo rc){
        ContentValues cv=new ContentValues();
        cv.put(columns[1],rc.getNombre());
        cv.put(columns[2],rc.getDescripcion());
        cv.put(columns[3],rc.getPrecio());
        cv.put(columns[4],rc.getCodigo());
        cv.put(columns[5],rc.getCodigodescarga());
        getWritableDatabase().insert(tablename,null,cv);
    }
    public RowCatalogo getProductById(Integer id){
        String[]args=new String[]{Integer.toString(id)};
        //Cursor c=getReadableDatabase().query(tablename,null,columns[0]+"=?",args,null,null,null);
        Cursor c=getReadableDatabase().query(tablename,columns,"_id=?",args,null,null,null);
        c.moveToFirst();
        String nombre=c.getString(c.getColumnIndex(columns[1]));
        String descripcion=c.getString(c.getColumnIndex(columns[2]));
        String precio=c.getString(c.getColumnIndex(columns[3]));
        String codigo=c.getString(c.getColumnIndex(columns[4]));
        String codigodescarga=c.getString(c.getColumnIndex(columns[5]));
        close();
        return new RowCatalogo(nombre,descripcion,precio,codigo,codigodescarga);
    }
    public ArrayList<RowCatalogo> allproducts(){
        ArrayList<RowCatalogo>products=new ArrayList<>();
        Cursor c=getWritableDatabase().query(tablename,null,null,null,null,null,null);
        int i=1;
        while(c.moveToNext()){
            products.add(getProductById(i));
            i++;
        }
        return products;
    }

    public void deletedb() {
        getWritableDatabase().execSQL("DROP TABLE "+tablename);
        onCreate(getWritableDatabase());
    }
    public boolean codeisAlready(String code){
        String[] column= new String[]{columns[0]};
        String[]args=new String[]{code};
        Cursor c=getReadableDatabase().query(tablename,column,"codigo=?",args,null,null,null);
        if(c.getCount()!=0)
            return true;
        else
            return false;

    }

    public RowCatalogo getProductByCode(String code) {
        String[]column= new String[]{columns[0]};
        String[]args= new String[]{code};
        Cursor c=getReadableDatabase().query(tablename,column,"codigodescarga=?",args,null,null,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            Integer id=c.getInt(c.getColumnIndex(columns[0]));
            return getProductById(id);
        }
            return null;
    }

    public ArrayList<RowCatalogo> getMimagia() {
        ArrayList<RowCatalogo> rows=new ArrayList<>();
        Cursor c=getReadableDatabase().query(tablenamemimagia,null,null,null,null,null,null);
        int i=0;
        while(c.moveToNext()){
            rows.add(new RowCatalogo(c.getString(c.getColumnIndex(columns[1])),
                    c.getString(c.getColumnIndex(columns[2])),
                    c.getString(c.getColumnIndex(columns[3])),
                    c.getString(c.getColumnIndex(columns[4])),
                    c.getString(c.getColumnIndex(columns[5]))
            ));
        }
        return rows;
    }

    public Boolean addMagia(RowCatalogo rc) {
            ContentValues cv=new ContentValues();
            if(getMimagiaByCode(rc.getCodigo())==null){
                cv.put(columns[1],rc.getNombre());
                cv.put(columns[2],rc.getDescripcion());
                cv.put(columns[3],rc.getPrecio());
                cv.put(columns[4],rc.getCodigo());
                cv.put(columns[5],rc.getCodigodescarga());
                getWritableDatabase().insert(tablenamemimagia,null,cv);
                return true;
            }
            return false;
    }

    public RowCatalogo getMimagiaByCode(String code) {
        String[]column= new String[]{columns[0]};
        String[]args= new String[]{code};
        Cursor c=getReadableDatabase().query(tablenamemimagia,column,"codigo=?",args,null,null,null);
        if(c.getCount()!=0){
            c.moveToFirst();
            Integer id=c.getInt(c.getColumnIndex(columns[0]));
            return getProductById(id);
        }
        return null;
    }
}
