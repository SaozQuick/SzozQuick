package org.saozquick.util;

import android.content.res.Resources;

/**
 * @ClassName: SizeUtils
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class SizeUtils {

    private SizeUtils() {
        throw new UnsupportedOperationException("can't instantiate");
    }

    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
