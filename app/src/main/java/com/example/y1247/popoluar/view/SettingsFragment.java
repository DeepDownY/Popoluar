package com.example.y1247.popoluar.view;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

import com.example.y1247.popoluar.R;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String SORT_KEY = "SortType";
    public static final String LIST_KEY = "ListType";

    public static final int STAGGEREDGRID = 1;
    public static final int VERTICALLINEAR = 2;

    private ListPreference lp_ListType;
    private ListPreference lp_sortType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_setting);
        initPreferences();
    }



    private void initPreferences() {
        lp_ListType = (ListPreference) findPreference(LIST_KEY);
        lp_sortType = (ListPreference) findPreference(SORT_KEY);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Setup the initial values
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        lp_sortType.setSummary(sharedPreferences.getString(SORT_KEY, "1"));
        lp_ListType.setSummary(sharedPreferences.getString(LIST_KEY,"1"));
        // Set up a listener whenever a key changes
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
//        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(SettingsFragment.LIST_KEY)) {
            lp_ListType.setSummary(
                    sharedPreferences.getString(key, "1"));
        } else if(key.equals(SettingsFragment.SORT_KEY)) {
            lp_sortType.setSummary(sharedPreferences.getString(key, "1"));
        }
    }
}