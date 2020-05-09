package org.saozquick.util;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * @ClassName: ScreenUtils
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("can't instantiate");
    }

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return -1;
        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        return point.x;
    }
}
