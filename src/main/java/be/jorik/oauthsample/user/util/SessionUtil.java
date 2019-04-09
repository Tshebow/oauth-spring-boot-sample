package be.jorik.oauthsample.user.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	public static String getSessionAttribute(HttpServletRequest request, String attributeName) {
		HttpSession session = request.getSession();
		return session == null ? null : (String) session.getAttribute(attributeName);
	}

	public static void clearSessionAttribute(HttpServletRequest request, String attributeName) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute(attributeName);
		}
	}
}
