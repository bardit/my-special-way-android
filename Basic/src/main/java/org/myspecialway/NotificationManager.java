package org.myspecialway;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import org.myspecialway.android.ListExamplesActivity;
import org.myspecialway.android.R;

public class NotificationManager {

    NotificationCompat.Builder mBuilder;
    Context context;
    private int NOTIFICATIONID = 1452673;

    public NotificationManager(Context cont){
        context = cont;
    }

    /**
     * Show a notification to the user
     * @param title
     * @param text
     */
    public void showNotification(String title, String text){
        Notification notification = createNotification(title, text);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(NOTIFICATIONID, notification);
    }

    /**
     * Show a navigation notification to the user
     * @param navigateTo
     */
    public void showNavigationNotification(ClassCoordinates navigateTo) {
        String userName = getUserName();
        showNotification(context.getString(R.string.notification_navigation_title, navigateTo.getClassName()),
                context.getString(R.string.notification_navigation_text, navigateTo.getClassName(), userName));

    }

    private String getUserName() {
        //TODO - get the user private name to show in the notificaiton. Get the name from login
        return "אלי";
    }

    private Notification createNotification(String title, String text){

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(context, ListExamplesActivity.class); // TODO - need to start navigation
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


        mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC);

        return mBuilder.build();
    }

}
