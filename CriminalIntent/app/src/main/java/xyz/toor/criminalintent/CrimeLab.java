package xyz.toor.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xyz.toor.criminalintent.database.CrimeBaseHelper;
import xyz.toor.criminalintent.database.CrimeCursorWrapper;
import xyz.toor.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by chalypeng on 2017/9/21.
 */
// 单例
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    // Hashtable 比 ArrayList 快，故改用HashTable
//    private Hashtable<UUID, Crime> mCrimes; // 改用SQLite 存数据

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

//        mCrimes = new Hashtable<>();
//        for (int i = 0; i < 100; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.put(crime.getId(),crime);
//        }
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public void addCrime(Crime c) {
//        mCrimes.put(c.getId(), c);
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public List<Crime> getCrimes() {
//        return new ArrayList<>(mCrimes.values());
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
//        // foreach
//        for (Crime crime : mCrimes) {
//            if (crime.getId().equals(id)) {
//                return crime;
//            }
//        }
//        // stream 方案
//        return null;
//        return mCrimes.get(id);
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + "=?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }

    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME,
                values,
                CrimeTable.Cols.UUID + "=?",
                new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null, // columns = null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new CrimeCursorWrapper(cursor);

    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT,crime.getSuspect());
        return values;

    }
}
