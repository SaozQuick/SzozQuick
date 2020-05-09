package org.saozquick.commom;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.saozquick.R;
import org.saozquick.util.ScreenUtils;
import org.saozquick.util.SizeUtils;

/**
 * @ClassName: BaseDialog
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 * <p>
 * * 基于 DialogFragment 的基础 Dialog
 * * BaseDialog.init()
 * * .setLayoutId(R.layout.dialog)     //设置dialog布局文件
 * * .setTheme(R.style.MyDialog) // 设置dialog主题，默认主题继承自Theme.AppCompat.Light.Dialog
 * * .setConvertListener(new ViewConvertListener() {     //进行相关View操作的回调
 * * @Override public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
 * * <p>
 * * }
 * * })
 * * .setDimAmount(0.3f)     //调节灰色背景透明度[0-1]，默认0.5f
 * * .setGravity()     //可选，设置dialog的位置，默认居中，可通过系统Gravity的类的常量修改，例如Gravity.BOTTOM（底部），Gravity.Right（右边），Gravity.BOTTOM|Gravity.Right（右下）
 * * .setMargin()     //dialog左右两边到屏幕边缘的距离（单位：dp），默认0dp
 * * .setWidth()     //dialog宽度（单位：dp），默认为屏幕宽度，-1代表WRAP_CONTENT
 * * .setHeight()     //dialog高度（单位：dp），默认为WRAP_CONTENT
 * * .setOutCancel(false)     //点击dialog外是否可取消，默认true
 * * .setAnimStyle(R.style.EnterExitAnimation)     //设置dialog进入、退出的自定义动画；根据设置的Gravity，默认提供了左、上、右、下位置进入退出的动画
 * * .show(getSupportFragmentManager());     //显示dialog
 * * <p>
 * * 注意：
 * * setMargin()和setWidth()选择一个即可
 * * <p>
 * * dismiss(FragmentManager manager)
 */
public abstract class BaseDialog extends AppCompatDialogFragment {
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String GRAVITY = "gravity";
    private static final String CANCEL = "out_cancel";
    private static final String THEME = "theme";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";

    private int margin;//左右边距
    private int width;//宽度
    private int height;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private int gravity = Gravity.CENTER;//显示的位置
    private boolean outCancel = true;//是否点击外部取消
    @StyleRes
    protected int theme = R.style.BaseDialogStyle; // dialog主题
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;

    public abstract int intLayoutId();

    public abstract void convertView(BaseDialogViewHolder holder, BaseDialog dialog);

    public int initTheme() {
        return theme;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BaseDialog.STYLE_NO_TITLE, initTheme());

        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            gravity = savedInstanceState.getInt(GRAVITY);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            theme = savedInstanceState.getInt(THEME);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutId = intLayoutId();
        View view = inflater.inflate(layoutId, container, false);
        convertView(BaseDialogViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MARGIN, margin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putInt(GRAVITY, gravity);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(THEME, theme);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, layoutId);
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            if (gravity != 0) {
                lp.gravity = gravity;
            }
            switch (gravity) {
                case Gravity.LEFT:
                case (Gravity.LEFT | Gravity.BOTTOM):
                case (Gravity.LEFT | Gravity.TOP):
                    if (animStyle == 0) {
                        animStyle = R.style.LeftAnimation;
                    }
                    break;
                case Gravity.TOP:
                    if (animStyle == 0) {
                        animStyle = R.style.TopAnimation;
                    }
                    break;
                case Gravity.RIGHT:
                case (Gravity.RIGHT | Gravity.BOTTOM):
                case (Gravity.RIGHT | Gravity.TOP):
                    if (animStyle == 0) {
                        animStyle = R.style.RightAnimation;
                    }
                    break;
                case Gravity.BOTTOM:
                    if (animStyle == 0) {
                        animStyle = R.style.BottomAnimation;
                    }
                    break;
                default:
                    break;

            }

            //设置dialog宽度
            if (width == 0) {
                lp.width = ScreenUtils.getScreenWidth(getContext()) - 2 * SizeUtils.dp2px(margin);
            } else if (width == -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.width = SizeUtils.dp2px(width);
            }

            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.height = SizeUtils.dp2px(height);
            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            window.setAttributes(lp);
        }
        setCancelable(outCancel);
    }

    public BaseDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BaseDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BaseDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public BaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public BaseDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public BaseDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public BaseDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public BaseDialog show(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
    }

    public BaseDialog dismiss(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        return this;
    }

}
