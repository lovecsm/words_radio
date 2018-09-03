package com.word.radio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class MediaButtonReceiver extends BroadcastReceiver {

    private static String TAG = "MediaButtonReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        // 获得Action
        String intentAction = intent.getAction();
        // 获得KeyEvent对象
        KeyEvent keyEvent = (KeyEvent) intent
                .getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        // 按下 / 松开 按钮
        int keyAction = keyEvent.getAction();

        if (Intent.ACTION_MEDIA_BUTTON.equals(intentAction)
                && (KeyEvent.ACTION_DOWN == keyAction)) {
            // 获得按键字节码
            int keyCode = keyEvent.getKeyCode();
            // 获得事件的时间
//            downtime = keyEvent.getDownTime();
            // 获取按键码 keyCode
//			StringBuilder sb = new StringBuilder();
//			// 这些都是可能的按键码 ， 打印出来用户按下的键
//			if (KeyEvent.KEYCODE_MEDIA_NEXT == keyCode) {
//				sb.append("KEYCODE_MEDIA_NEXT");
//			}
            // 说明：当我们按下MEDIA_BUTTON中间按钮时，实际出发的是 KEYCODE_HEADSETHOOK 而不是
            // KEYCODE_MEDIA_PLAY_PAUSE
            if (KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE == keyCode) {
//				sb.append("KEYCODE_MEDIA_PLAY_PAUSE");

            }
            if (KeyEvent.KEYCODE_HEADSETHOOK == keyCode) {
                Intent intent1 = new Intent("ok");
                context.sendBroadcast(intent1);
            }
            if (KeyEvent.KEYCODE_MEDIA_PREVIOUS == keyCode) {
//				sb.append("KEYCODE_MEDIA_PREVIOUS");
            }
            if (KeyEvent.KEYCODE_MEDIA_STOP == keyCode) {
//				sb.append("KEYCODE_MEDIA_STOP");
            }
            // 输出点击的按键码
//			Log.i(TAG, sb.toString());
//			Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
        } else if (KeyEvent.ACTION_UP == keyAction) {
            //Log.d("reajfkaej","down:"+String.valueOf(keyEvent.getDownTime()));
            //Log.d("reajfkaej","up:"+String.valueOf(keyEvent.getEventTime()));
            if (keyEvent.getEventTime() - keyEvent.getDownTime() > 100) {
                //Log.d("reajfkaej","restart-> distance:"+String.valueOf(keyEvent.getEventTime()-keyEvent.getDownTime()));
                Intent intent1 = new Intent("restart");
                context.sendBroadcast(intent1);
            }
        }
    }
}
