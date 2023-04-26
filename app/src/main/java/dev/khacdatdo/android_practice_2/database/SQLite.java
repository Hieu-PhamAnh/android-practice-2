package dev.khacdatdo.android_practice_2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dev.khacdatdo.android_practice_2.model.CongViec;
import dev.khacdatdo.android_practice_2.model.TinhTrang;

public class SQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CoVanAnhXinhGai.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cong_viec";

    public SQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand =
                "CREATE TABLE " + TABLE_NAME
                + "(id interger primary key autoincrement, ten text,"
                + " noiDung text, ngayHoanThanh date, tinhTrang int, congTac int)";
        sqLiteDatabase.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("Method is not implemented");
    }

    public void insert(CongViec congViec) {
        String sql = "INSERT INTO " + TABLE_NAME
                     + "(ten, noiDung, ngayHoanThanh, tinhTrang, congTac) VALUES (?, ?, ?, ?, ?)";
        String[] args = {
                congViec.getTen(),
                congViec.getNoiDung(),
                congViec.getNgayHoanThanh(),
                congViec.getTinhTrang().toString(),
                String.valueOf(congViec.isCongTac() ? 1 : 0),
                };

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, args);
    }

    @NonNull
    private List<CongViec> serializeResult(Cursor cursor) {
        List<CongViec> result = new ArrayList<CongViec>();
        while (cursor != null && cursor.moveToNext()) {
            result.add(
                    new CongViec(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            TinhTrang.valueOf(cursor.getString(4)),
                            cursor.getInt(5) == 1
                    )
            );
        }

        cursor.close();
        return result;
    }

    public List<CongViec> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        return this.serializeResult(cursor);
    }

    public List<CongViec> search(String keyword) {
        String sql = "SELECT * FROM " + TABLE_NAME
                     + " WHERE ten LIKE ? OR noiDung LIKE ?"
                     + " ORDER BY ngayHoanThanh ASC";
        String keywordLike = "%" + keyword + "%";
        String[] args = {keywordLike, keywordLike};

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);

        return this.serializeResult(cursor);
    }

    public List<CongViec> search(String keyword, TinhTrang tinhTrang) {
        String sql = "SELECT * FROM " + TABLE_NAME
                     + " WHERE (ten LIKE ? OR noiDung LIKE ?) AND tinhTrang = ?"
                     + " ORDER BY ngayHoanThanh ASC";
        String keywordLike = "%" + keyword + "%";
        String[] args = {keywordLike, keywordLike, tinhTrang.name()};

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, args);

        return this.serializeResult(cursor);
    }

    public int update(CongViec congViec) {
        ContentValues values = new ContentValues();
        values.put("ten", congViec.getTen());
        values.put("noiDung", congViec.getNoiDung());
        values.put("ngayHoanThanh", congViec.getNgayHoanThanh());
        values.put("tinhTrang", congViec.getTinhTrang().name());
        values.put("congTac", congViec.isCongTac() ? 1 : 0);

        String where = "id = ?";
        String[] whereArgs = {Integer.toString(congViec.getMa())};

        SQLiteDatabase db = getReadableDatabase();
        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int delete(int ma) {
        String where = "id = ?";
        String[] whereArgs = {Integer.toString(ma)};

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, where, whereArgs);
    }

}
