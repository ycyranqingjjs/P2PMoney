package cn.itcast.wh.p2pmoney12.common;

import android.content.Context;
import android.os.*;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/12/13.
 * <p/>
 * CrashHandler设计为单例模式
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler crashHandler = null;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }


    private Context mContext;
//    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public void init(Context context) {
        //将CrashHandler作为系统的默认异常处理器
        this.mContext = context;
//        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    /**
     * 把这个提示信息汉化，记录一下日志信息，反馈到后台
     *
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        Log.e("zoubo", "CrashHandler----uncaughtException....");
        handleException(thread, ex);
    }

    /**
     * 判断是否需要自己处理
     *
     * @param ex
     * @return
     */
    public boolean isHandle(Throwable ex) {
        if (ex == null) {
            return false;
        } else {
            return true;
        }
    }

    ;

    /**
     * 自定义异常处理
     *
     * @param thread
     * @param ex
     */
    private void handleException(Thread thread, Throwable ex) {
        new Thread() {
            @Override
            public void run() {
                //Android系统当中，默认情况下，线程是没有开启looper消息处理的，但是主线程除外
                Looper.prepare();
                Toast.makeText(mContext, "抱歉，系统出现未知异常，即将退出....", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collectionException(ex);
        try {
            thread.sleep(2000);
            AppManager.getInstance().removeAll();
            android.os.Process.killProcess(android.os.Process.myPid());
            //关闭虚拟机，释放所有内存
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收集一下崩溃异常信息
     *
     * @param ex
     */
    private void collectionException(Throwable ex) {
        final String deviceInfo = Build.DEVICE + Build.VERSION.SDK_INT + Build.MODEL + Build.PRODUCT;
        final String errorInfo = ex.getMessage();
        new Thread() {
            @Override
            public void run() {
                Log.e("zoubo", "deviceInfo---" + deviceInfo + ":errorInfo" + errorInfo);
            }
        }.start();
    }
}
