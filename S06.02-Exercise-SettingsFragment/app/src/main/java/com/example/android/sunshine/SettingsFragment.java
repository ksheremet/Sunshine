package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Created by katarina on 12/1/17.
 */
// Completed (4) Create SettingsFragment and extend PreferenceFragmentCompat
// Do steps 5 - 11 within SettingsFragment
// Completed (10) Implement OnSharedPreferenceChangeListener from SettingsFragment

// Completed (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference

// Completed (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource

// Do step 9 within onCreatePreference
// Completed (9) Set the preference summary on each preference that isn't a CheckBoxPreference

// Completed (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop

// Completed (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart

// Completed (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_general);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        int count = preferenceScreen.getPreferenceCount();
        for (int i=0; i<count; i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (! (preference instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    public void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();
        String key = preference.getKey();

        if (preference instanceof ListPreference) {
            ListPreference listPref = (ListPreference) preference;
            int prefIndex = listPref.findIndexOfValue(stringValue);
            if (prefIndex >=0) {
                preference.setSummary(listPref.getEntries()[prefIndex]);
            }
        } else {
            preference.setSummary(stringValue);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference != null) {
            if (!(preference instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }
    }
}
