package cc.mwlock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cc.mwlock.service.LockService;

/**
 * User: 山野书生(1203596603@qq.com)
 * Date: 2015-11-09
 * Time: 16:16
 * Version 1.0
 */

public class BootCompletedReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        //开机完成启动广播
        Intent bootCompletedIntent = new Intent(context, LockService.class);
        context.startService(intent);
    }


}
