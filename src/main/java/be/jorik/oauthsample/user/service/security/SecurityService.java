package be.jorik.oauthsample.user.service.security;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
	String getRandomizedState();

	void storeAsSecurityStateOnSession(HttpServletRequest request, String state);

	void validateWithSecurityStateOnSession(HttpServletRequest request, String dataBlob);
}
