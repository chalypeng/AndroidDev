package xyz.toor.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by chalypeng on 2017/9/21.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
