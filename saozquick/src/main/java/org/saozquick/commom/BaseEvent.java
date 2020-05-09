package org.saozquick.commom;

/**
 * @ClassName: BaseEvent
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class BaseEvent {

    private int action;

    public BaseEvent(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public static class BaseActionEvent extends BaseEvent {
        public static final int SHOW_LOADING_DIALOG = 1;

        public static final int DISMISS_LOADING_DIALOG = 2;

        private String message;

        public BaseActionEvent(int action) {
            super(action);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
