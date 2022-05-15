package com.design.mvp.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by syl on 2018/10/26.
 */

public class StatusBarUtil {

    public static int getStatusBarHeight(Context context) {
        int h = getStatusBar1(context);
        if (h == 0) {
            h = getStatusBar2(context);
        }
        return h;
    }

    private static int getStatusBar1(Context context){
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getStatusBar2(Context context) {
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            int statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getStatusBarHeight(Window window) {
        View view = window.getDecorView();
        // 获取状态栏高度
        Rect frame = new Rect();
        // 测量屏幕宽和高
        view.getWindowVisibleDisplayFrame(frame);
        int result = frame.top;
        return result;
    }

    /**
     * 设置状态栏上面的字体颜色
     *
     * @param activity
     * @param isDarkFont true-表示黑色，false-表示白色
     */
    public static void setStatusBarDarkFont(Activity activity, boolean isDarkFont) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (StatusBarUtil.setMeiZuStatusBarDarkFont(isDarkFont, activity.getWindow())) {
                return;
            }
            if (StatusBarUtil.setXiaoMiStatusBarDarkFont(isDarkFont, activity)) {

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isDarkFont) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }
            }
        }
    }


    /**
     * 设置小米手机状态栏
     *
     * @param isDarkFont
     * @param activity
     */
    public static boolean setXiaoMiStatusBarDarkFont(boolean isDarkFont, Activity activity) {
        boolean result = false;
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), isDarkFont ? darkModeFlag : 0, darkModeFlag);
            result = true;
        } catch (Exception e) {
//            Logger.e("XiaoMi", "setStatusBarDarkIcon: failed");
        }
        return result;
    }

    /**
     * 设置魅族手机状态栏
     *
     * @param isDarkFont
     * @param window
     * @return
     */
    public static boolean setMeiZuStatusBarDarkFont(boolean isDarkFont, Window window) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (isDarkFont) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
//                Logger.e("MeiZu", "setStatusBarDarkIcon: failed");
            }
        }
        return result;
    }
}
