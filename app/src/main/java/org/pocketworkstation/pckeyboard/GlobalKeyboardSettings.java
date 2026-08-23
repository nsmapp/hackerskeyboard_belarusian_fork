package org.pocketworkstation.pckeyboard;

import android.content.SharedPreferences;
import android.content.res.Resources;

import java.util.Locale;

public final class GlobalKeyboardSettings {
    protected static final String TAG = "HK/Globals";

    public int popupKeyboardFlags = 0x1;
    public float topRowScale = 1.0f;
    public boolean showTouchPos = false;
    public String suggestedPunctuation = "!?,.";
    public int keyboardModePortrait = 0;
    public int keyboardModeLandscape = 2;
    public boolean compactModeEnabled = true;
    public int ctrlAOverride = 0;
    public int chordingCtrlKey = 0;
    public int chordingAltKey = 0;
    public int chordingMetaKey = 0;
    public float keyClickVolume = 0.0f;
    public int keyClickMethod = 0;
    public boolean capsLock = true;
    public boolean shiftLockModifiers = false;
    public float labelScalePref = 1.0f;
    public float candidateScalePref = 1.0f;
    public int sendSlideKeys = 0;
    public int keyboardMode = 0;
    public boolean useExtension = false;
    public float keyboardHeightPercent = 40.0f;
    public int hintMode = 0;
    public int renderMode = 1;
    public int longpressTimeout = 400;
    public String editorPackageName;
    public String editorFieldName;
    public int editorFieldId;
    public int editorInputType;
    public Locale inputLocale = Locale.getDefault();

    public static final int FLAG_PREF_NONE = 0;
    public static final int FLAG_PREF_NEED_RELOAD = 0x1;
    public static final int FLAG_PREF_NEW_PUNC_LIST = 0x2;
    public static final int FLAG_PREF_RECREATE_INPUT_VIEW = 0x4;
    public static final int FLAG_PREF_RESET_KEYBOARDS = 0x8;
    public static final int FLAG_PREF_RESET_MODE_OVERRIDE = 0x10;
    private int mCurrentFlags = 0;

    public void initPrefs(SharedPreferences prefs, Resources resources) {
        keyboardModePortrait = Integer.parseInt(prefs.getString("pref_keyboard_mode_portrait", resources.getString(R.string.default_keyboard_mode_portrait)));
        keyboardModeLandscape = Integer.parseInt(prefs.getString("pref_keyboard_mode_landscape", resources.getString(R.string.default_keyboard_mode_landscape)));
        sendSlideKeys = Integer.parseInt(prefs.getString("pref_slide_keys_int", "0"));
        showTouchPos = prefs.getBoolean("pref_touch_pos", false);
        popupKeyboardFlags = Integer.parseInt(prefs.getString("pref_popup_content", resources.getString(R.string.default_popup_content)));
        suggestedPunctuation = prefs.getString("pref_suggested_punctuation", resources.getString(R.string.suggested_punctuations_default));
        labelScalePref = Float.parseFloat(prefs.getString("pref_label_scale_v2", "1.0"));
        candidateScalePref = Float.parseFloat(prefs.getString("pref_candidate_scale", "1.0"));
        topRowScale = Float.parseFloat(prefs.getString("pref_top_row_scale", "1.0"));
        ctrlAOverride = Integer.parseInt(prefs.getString("pref_ctrl_a_override", resources.getString(R.string.default_ctrl_a_override)));
        chordingCtrlKey = Integer.parseInt(prefs.getString("pref_chording_ctrl_key", resources.getString(R.string.default_chording_ctrl_key)));
        chordingAltKey = Integer.parseInt(prefs.getString("pref_chording_alt_key", resources.getString(R.string.default_chording_alt_key)));
        chordingMetaKey = Integer.parseInt(prefs.getString("pref_chording_meta_key", resources.getString(R.string.default_chording_meta_key)));
        keyClickVolume = Float.parseFloat(prefs.getString("pref_click_volume", resources.getString(R.string.default_click_volume)));
        keyClickMethod = Integer.parseInt(prefs.getString("pref_click_method", resources.getString(R.string.default_click_method)));
        capsLock = prefs.getBoolean("pref_caps_lock", resources.getBoolean(R.bool.default_caps_lock));
        shiftLockModifiers = prefs.getBoolean("pref_shift_lock_modifiers", resources.getBoolean(R.bool.default_shift_lock_modifiers));
    }

    public void sharedPreferenceChanged(SharedPreferences prefs, String key) {
        mCurrentFlags = FLAG_PREF_NONE;
        switch (key) {
            case "pref_keyboard_mode_portrait":
                keyboardModePortrait = Integer.parseInt(prefs.getString(key, "0"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS | FLAG_PREF_RESET_MODE_OVERRIDE;
                break;
            case "pref_keyboard_mode_landscape":
                keyboardModeLandscape = Integer.parseInt(prefs.getString(key, "2"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS | FLAG_PREF_RESET_MODE_OVERRIDE;
                break;
            case "pref_slide_keys_int":
                sendSlideKeys = Integer.parseInt(prefs.getString(key, "0"));
                break;
            case "pref_touch_pos":
                showTouchPos = prefs.getBoolean(key, false);
                break;
            case "pref_popup_content":
                popupKeyboardFlags = Integer.parseInt(prefs.getString(key, "1"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS;
                break;
            case "pref_suggested_punctuation":
                suggestedPunctuation = prefs.getString(key, "!?,.");
                mCurrentFlags |= FLAG_PREF_NEW_PUNC_LIST;
                break;
            case "pref_label_scale_v2":
                labelScalePref = Float.parseFloat(prefs.getString(key, "1.0"));
                mCurrentFlags |= FLAG_PREF_RECREATE_INPUT_VIEW;
                break;
            case "pref_candidate_scale":
                candidateScalePref = Float.parseFloat(prefs.getString(key, "1.0"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS;
                break;
            case "pref_top_row_scale":
                topRowScale = Float.parseFloat(prefs.getString(key, "1.0"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS;
                break;
            case "pref_ctrl_a_override":
                ctrlAOverride = Integer.parseInt(prefs.getString(key, "0"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS;
                break;
            case "pref_chording_ctrl_key":
                chordingCtrlKey = Integer.parseInt(prefs.getString(key, "0"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS;
                break;
            case "pref_chording_alt_key":
                chordingAltKey = Integer.parseInt(prefs.getString(key, "0"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS;
                break;
            case "pref_chording_meta_key":
                chordingMetaKey = Integer.parseInt(prefs.getString(key, "0"));
                mCurrentFlags |= FLAG_PREF_RESET_KEYBOARDS;
                break;
            case "pref_click_volume":
                keyClickVolume = Float.parseFloat(prefs.getString(key, "0.0"));
                break;
            case "pref_click_method":
                keyClickMethod = Integer.parseInt(prefs.getString(key, "0"));
                break;
            case "pref_caps_lock":
                capsLock = prefs.getBoolean(key, true);
                break;
            case "pref_shift_lock_modifiers":
                shiftLockModifiers = prefs.getBoolean(key, false);
                break;
        }
    }

    public boolean hasFlag(int flag) {
        if ((mCurrentFlags & flag) != 0) {
            mCurrentFlags &= ~flag;
            return true;
        }
        return false;
    }

    public int unhandledFlags() {
        return mCurrentFlags;
    }
}
