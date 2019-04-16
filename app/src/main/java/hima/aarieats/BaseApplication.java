package hima.aarieats;

import android.app.Application;

import com.onesignal.OneSignal;

public class BaseApplication extends Application {

    public void onCreate() {
        super.onCreate();
        // TODO: Add OneSignal initialization here
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
