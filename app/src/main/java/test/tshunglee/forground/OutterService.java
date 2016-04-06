package test.tshunglee.forground;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

public class OutterService extends Service {

    private Runnable mCancelInnerServiceForground = new Runnable() {
        @Override
        public void run() {
            stopForeground(true);
        }
    };
    private Notification mNotification;
    private Handler mHandler;
    public OutterService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        mNotification = builder.build();
        mHandler = new Handler();

        mHandler.postDelayed(mCancelInnerServiceForground, 200);
        startForeground(1, mNotification);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
