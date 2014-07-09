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
     * @param userId ユーザーID
     * @param password パスワード
     * @throws LoginException 
     */
    public static void login(String userId, String password) throws LoginException {
        User user = Database.getUser(userId);
        if (user == null || !user.getPassword().equals(password)) {
            throw new LoginException();
        }
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute(userId, user.getUserId());
    }
    
    /**
     * 現在のログインユーザーIDを返します。
     * @return ログインユーザーID、もしログイン中でなければnull
     */
    public static String getLoginUserId() {
        return (String) VaadinService.getCurrentRequest().getWrappedSession().getAttribute(ATTRIBUTE_USER_ID);
    }
    
    /**
     * 現在ログインしているかを返します。
     * @return ログインしていればtrue、でなければfalse
     */
    public static boolean isLoggedIn() {
        return VaadinService.getCurrentRequest().getWrappedSession(false) != null;
    }
}
