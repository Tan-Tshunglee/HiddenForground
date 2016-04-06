package test.tshunglee.forground;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

public class MainService extends Service {
    public static final int FOREGROUND_SERVICE_ID = 1;
    private Notification mNotification;

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        mNotification = builder.build();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startService(new Intent(this, OutterService.class));
        startForeground(FOREGROUND_SERVICE_ID, mNotification);
        return START_STICKY;
    }

    public static class InnerService extends Service {

        private Runnable mCancelInnerServiceForground = new Runnable() {
            @Override
            public void run() {
                stopForeground(true);
            }
        };
        private Notification mNotification;
        private Handler mHandler;

        @Override
        public void onCreate() {
            super.onCreate();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle(getString(R.string.app_name));
            builder.setSmallIcon(R.mipmap.ic_launcher);
            mNotification = builder.build();
            mHandler = new Handler();

            mHandler.postDelayed(mCancelInnerServiceForground, 200);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(FOREGROUND_SERVICE_ID, mNotification);
            return START_STICKY;
        }
    }


}
