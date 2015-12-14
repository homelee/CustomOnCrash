package uuxia.com.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uuxia.com.library.CustomOnCrashCore;

/**
 * Created by Android Studio.
 * Author: uuxia
 * Date: 2015-12-14 20:31
 * Description:
 */
/*
 * -----------------------------------------------------------------
 * Copyright ?2014 clife - 和而泰家居在线网络科技有限公司
 * Shenzhen H&T Intelligent Control Co.,Ltd.
 * -----------------------------------------------------------------
 *
 * File: CustomErrorActivity.java
 * Create: 2015/12/14 20:31
 */
public class CustomErrorActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_error);

        //**IMPORTANT**
        //The custom error activity in this sample is uglier than the default one and just
        //for demonstration purposes, please don't copy it to your project!
        //We recommend taking the original library's DefaultErrorActivity as a basis.
        //Of course, you are free to implement it as you wish in your application.

        //These three methods are available for you to use:
        //CustomOnCrashCore.getStackTraceFromIntent(getIntent()): gets the stack trace as a string
        //CustomOnCrashCore.getAllErrorDetailsFromIntent(context, getIntent()): returns all error details including stacktrace as a string
        //CustomOnCrashCore.getRestartActivityClassFromIntent(getIntent()): returns the class of the restart activity to launch, or null if none

        //Now, treat here the error as you wish. If you allow the user to restart or close the app,
        //don't forget to call the appropriate methods.
        //Otherwise, if you don't finish the activity, you will get the CustomErrorActivity on the activity stack and it will be visible again under some circumstances.
        //Also, you will get multiprocess problems in API<17.

        TextView errorDetailsText = (TextView) findViewById(R.id.error_details);
        errorDetailsText.setText(CustomOnCrashCore.getStackTraceFromIntent(getIntent()));

        Button restartButton = (Button) findViewById(R.id.restart_button);

        final Class<? extends Activity> restartActivityClass = CustomOnCrashCore.getRestartActivityClassFromIntent(getIntent());

        if (restartActivityClass != null) {
            restartButton.setText(R.string.restart_app);
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomErrorActivity.this, restartActivityClass);
                    CustomOnCrashCore.restartApplicationWithIntent(CustomErrorActivity.this, intent);
                }
            });
        } else {
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomOnCrashCore.closeApplication(CustomErrorActivity.this);
                }
            });
        }
    }
}
