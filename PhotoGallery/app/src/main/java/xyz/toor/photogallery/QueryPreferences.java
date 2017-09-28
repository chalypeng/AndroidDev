package xyz.toor.photogallery;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by chalypeng on 2017/9/28.
 */

public class QueryPreferences {
    private static final String PREF_SEARCH_QUERY = "searchQuery";

    public static String getStoredQuery(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_QUERY,null);

    }
}