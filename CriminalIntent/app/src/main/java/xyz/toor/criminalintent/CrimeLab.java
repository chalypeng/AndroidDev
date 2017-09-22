package xyz.toor.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

/**
 * Created by chalypeng on 2017/9/21.
 */
// 单例
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    // Hashtable 比 ArrayList 快，故改用HashTable
    private Hashtable<UUID,Crime> mCrimes;

    private CrimeLab(Context context) {
        mCrimes = new Hashtable<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.put(crime.getId(),crime);
        }
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
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
        return mCrimes.get(id);
    }
}
