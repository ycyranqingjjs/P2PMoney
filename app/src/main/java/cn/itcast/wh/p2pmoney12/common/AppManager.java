package cn.itcast.wh.p2pmoney12.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2015/12/11.
 * <p/>
 * AppManager设计成单例模式
 * <p/>
 * 统一app程序当中所有的activity栈管理
 * <p/>
 * 添加、删除指定、删除当前、删除所有、求栈大小....
 */
public class AppManager {

    private Stack<Activity> activityStack = new Stack<>();

    public static AppManager appManager = null;

    private AppManager() {

    }

    public static AppManager getInstance() {
        if (appManager == null) {
            appManager = new AppManager();
        }
        return appManager;
    }


    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }


    public void removeActivity(Activity activity) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity1 = activityStack.get(i);
            if (activity1.getClass().equals(activity.getClass())) {
                activity1.finish();
                activityStack.remove(activity1);
                break;
            }
        }
    }

    public void removeCurrent() {
        Activity lastElement = activityStack.lastElement();
        lastElement.finish();
        activityStack.remove(lastElement);
    }

    public void removeAll() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity1 = activityStack.get(i);
            activity1.finish();
            activityStack.remove(activity1);
        }
    }

    public int getSize(){
        return activityStack.size();
    }
}
