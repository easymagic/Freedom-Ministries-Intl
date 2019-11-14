package com.r2soft.freedoministriesinternational.packages.registry;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.r2soft.freedoministriesinternational.packages.application.BaseApplication;

public class RegistryAdapter extends SQLiteOpenHelper implements RegistryPort{

    private static String DB_NAME = "RegistryDB";
    private static String TABLE_NAME = "regit";
    private SQLiteDatabase mydatabase = null;
    private static RegistryAdapter instance = null;
    private Cursor dbResult = null;

    public RegistryAdapter() {
        // TODO Auto-generated constructor stub
        super(BaseApplication.getInstance(),DB_NAME,null,1);
        //initRegistry();
    }

    void InitRegistry(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,content TEXT);");
        //mydatabase.execSQL("INSERT INTO regit VALUES('app_core_version','1.0.0');");
    }

    public static RegistryAdapter GetInstance(){
        if (instance == null){
            instance = new RegistryAdapter();
        }
        return instance;
    }

    public void setKey_(String key, String val){
        if (!keyExists(key)){
            addKeyVal(key, val);
        }else{
            updateKeyVal(key, val);
        }
    }

    public String getKey_(String key){
        if (keyExists(key)){
            dbResult.moveToFirst();
            return dbResult.getString(2);
        }else{
            return null;
        }
    }

    void addKeyVal(String key, String val){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", key);
        cv.put("content", val);
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    void updateKeyVal(String key, String val){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put("name", key);
        cv.put("content", val);
        //db.insert("regit", null, cv);
        db.update(TABLE_NAME, cv, "name=?",
                (new String[]{key}));
        db.close();
    }

    boolean keyExists(String key){
        SQLiteDatabase db = getReadableDatabase();
        dbResult = db.rawQuery("select * from " + TABLE_NAME + " where (name='" + key + "')",null);
        if (dbResult != null){
            dbResult.moveToFirst();
            //return dbResult.getString(1);
            return (dbResult.getCount() > 0);
        }else{
            return false;
        }
    }

    void closeInit(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        InitRegistry(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        closeInit(db);
        onCreate(db);
    }

    public boolean hasKey_(String ky){
        return (getKey(ky) != null);
    }

    @Override
    public void setKey(String key, String value) {
       setKey_(key,value);
    }

    @Override
    public String getKey(String key) {
        return getKey_(key);
    }

    @Override
    public boolean hasKey(String key) {
        return hasKey_(key);
    }

}