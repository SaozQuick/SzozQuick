package org.saozquick.commom;

import org.saozquick.R;

/**
 * @ClassName: LoadingDialog
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class LoadingDialog extends BaseDialog {

    public static LoadingDialog with() {
        return new LoadingDialog();
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_progress;
    }

    @Override
    public void convertView(BaseDialogViewHolder holder, BaseDialog dialog) {

    }
}
