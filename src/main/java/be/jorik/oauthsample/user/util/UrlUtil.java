package be.jorik.oauthsample.user.util;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

	public static String getUrlForError(HttpServletRequest request, int errorCode) {
		return getBaseUrl(request) + "/error?code=" + errorCode;
	}

	public static String getBaseUrlWithPath(HttpServletRequest request, String path) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}

	public static String getBaseUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}
}
