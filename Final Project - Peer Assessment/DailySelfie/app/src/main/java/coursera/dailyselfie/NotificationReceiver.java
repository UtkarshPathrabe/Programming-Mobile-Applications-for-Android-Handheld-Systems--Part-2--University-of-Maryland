package coursera.dailyselfie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver{
    private static final int MY_NOTIFICATION_ID = 1;
    private final CharSequence tickerText = "Time for a new selfie...";
    private final CharSequence contentTitle = "Daily Selfie Notification";
    private final CharSequence contentText = "Touch for capturing a new selfie!!!";
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    @Override
    public void onReceive (Context context, Intent intent) {
        Log.d (MainActivity.TAG, "Notification Received!");
        // The Intent to be used when the user clicks on the Notification View
        mNotificationIntent = new Intent (context, MainActivity.class);
        // The PendingIntent that wraps the underlying Intent
        mContentIntent = PendingIntent.getActivity (context, 0, mNotificationIntent, PendingIntent.FLAG_ONE_SHOT);
        // Build the Notification
        Notification.Builder notificationBuilder = new Notification.Builder (context).setTicker (tickerText).setSmallIcon (android.R.drawable.ic_menu_camera).setAutoCancel (true).setContentTitle (contentTitle).setContentText (contentText).setContentIntent (mContentIntent);
        // Get the NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService (Context.NOTIFICATION_SERVICE);
        // Pass the Notification to the NotificationManager:
        mNotificationManager.notify (MY_NOTIFICATION_ID, notificationBuilder.build ());
    }
}