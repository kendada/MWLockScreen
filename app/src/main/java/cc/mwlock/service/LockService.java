package cc.mwlock.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import cc.mwlock.LockActivity;

/**
 * User: 山野书生(1203596603@qq.com)
 * Date: 2015-11-09
 * Time: 15:58
 * Version 1.0
 *
 * 监听锁屏状态服务
 */

public class LockService extends Service{


    private String tag = LockService.class.getSimpleName();

    private Intent lockIntent;

    private String onScreen = "android.intent.action.SCREEN_ON";

    private String offScreen = "android.intent.action.SCREEN_OFF";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        lockIntent = new Intent(this, LockActivity.class);
        lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //注册广播，唤醒屏幕
        IntentFilter mScreenOnFilter = new IntentFilter(onScreen);
        LockService.this.registerReceiver(mScreenOnReceiver, mScreenOnFilter);

        //注册广播，关闭屏幕
        IntentFilter mScreenOffFilter = new IntentFilter(offScreen);
        LockService.this.registerReceiver(mScreenOffReceiver, mScreenOffFilter);

    }

    //唤醒屏幕广播
    private BroadcastReceiver mScreenOnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(onScreen)){
                Log.i(tag, "----60----唤醒屏幕--广播---");
            }
        }
    };

    //关闭屏幕广播
    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(offScreen)){
                Log.i(tag, "----70---关闭屏幕--广播---");
                startActivity(lockIntent);
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY; //回收之后重新启动【第三方应用无法启动】
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LockService.this.unregisterReceiver(mScreenOnReceiver);
        LockService.this.unregisterReceiver(mScreenOffReceiver);

        startService(new Intent(this, LockService.class)); //重启启动，一般系统回收会执行，第三方销毁无法执行
    }

}
