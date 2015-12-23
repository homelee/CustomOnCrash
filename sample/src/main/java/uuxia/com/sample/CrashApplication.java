package uuxia.com.sample;

import android.app.Application;

import uuxia.com.library.CustomOnCrashCore;
import uuxia.com.library.mail.Email;

/**
 * Created by Android Studio.
 * Author: uuxia
 * Date: 2015-12-14 20:30
 * Description:
 */
/*
 * -----------------------------------------------------------------
 * Copyright ?2014 clife - 和而泰家居在线网络科技有限公司
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------------------------
 *
 * File: CrashApplication.java
 * Create: 2015/12/14 20:30
 */
public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //You can uncomment any of the lines below, and test the results.
        //If you comment out the install one, the library will not work and the default Android crash dialog will be shown.

        //This makes the library not launch the error activity when the app crashes while it is in background.
//        CustomOnCrashCore.setLaunchErrorActivityWhenInBackground(false);

        //This sets the restart activity.
        //If you set this, this will be used. However, you can also set it with an intent-filter:
        //  <action android:name="cat.ereza.customactivityoncrash.RESTART" />
        //If none are set, the default launch activity will be used.
//        CustomOnCrashCore.setRestartActivityClass(MainActivity.class);

        //This hides the "error details" button in the error activity, thus hiding the stack trace
//        CustomOnCrashCore.setShowErrorDetails(true);

        //This avoids the app from using the "Restart app" button and displaying a "Close app" button directly.
        //Be careful, even with restart app enabled, the Close app can still be displayed if no suitable
        //restart activity is found!
//        CustomOnCrashCore.setEnableAppRestart(false);

        //This shows a different image on the error activity, instead of the default upside-down bug.
        //You may use a drawable or a mipmap.
//        CustomOnCrashCore.setDefaultErrorActivityDrawable(R.mipmap.ic_launcher);

        //This sets a custom error activity class instead of the default one.
        //If you set this, this will be used. However, you can also set it with an intent-filter:
        //  <action android:name="cat.ereza.customactivityoncrash.ERROR" />
        //If none are set, the default launch activity will be used.
        //Comment it (and disable the intent filter) to see the customization effects on the default error activity.
        //Uncomment to use the custom error activity
//        CustomOnCrashCore.setErrorActivityClass(CustomErrorActivity.class);

        //This enables CustomOnCrashCore
        Email mail = Email.create("263996097@qq.com", "xxl84213344..", "263996097@qq.com");
        CustomOnCrashCore.setAutoSendMail(true);
        CustomOnCrashCore.setMail(mail);
        CustomOnCrashCore.install(this);
    }
}
