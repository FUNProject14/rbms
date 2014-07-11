package org.rootbeer.rbms.view.util;

import com.vaadin.server.VaadinService;
import org.rootbeer.rbms.model.User;
import org.rootbeer.rbms.util.Database;

/**
 * ログインに関係したユーティリティクラスです。
 */
public class LoginSession {

    private static final String ATTRIBUTE_USER_ID = "userid";

    /**
     * ログインします。セッションを使います。
     *
     * @param userId ユーザーID
     * @param password パスワード
     * @throws LoginException
     */
    public static void login(String userId, String password) throws LoginException {
        User user = Database.getUser(userId);
        if (user == null || !user.getPassword().equals(password)) {
            throw new LoginException();
        }
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute(ATTRIBUTE_USER_ID, user.getUserId());
    }
    
    /**
     * ログアウトします。
     */
    public static void logout() {
        VaadinService.getCurrentRequest().getWrappedSession().removeAttribute(ATTRIBUTE_USER_ID);
    }

    /**
     * 現在のログインユーザーIDを返します。
     *
     * @return ログインユーザーID、もしログイン中でなければnull
     */
    public static String getLoginUserId() {
        return (String) VaadinService.getCurrentRequest().getWrappedSession().getAttribute(ATTRIBUTE_USER_ID);
    }
    
    /**
     * 現在のログインユーザーをUserオブジェクトとして返します。
     * ユーザーIDだけが必要な場合はgetLoginUserIdを使用して下さい。
     * 
     * @return ログインユーザー、もしログイン中でなければnull
     */
    public static User getLoginUser() {
        String userId = getLoginUserId();
        if (userId == null) {
            return null;
        }
        return Database.getUser(userId);
    }

    /**
     * 現在ログインしているかを返します。
     *
     * @return ログインしていればtrue、でなければfalse
     */
    public static boolean isLoggedIn() {
        return VaadinService.getCurrentRequest().getWrappedSession().getAttribute(ATTRIBUTE_USER_ID) != null;
    }
}
