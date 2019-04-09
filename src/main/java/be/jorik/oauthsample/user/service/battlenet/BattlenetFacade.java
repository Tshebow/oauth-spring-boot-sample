package be.jorik.oauthsample.user.service.battlenet;

import javax.servlet.http.HttpServletRequest;

public interface BattlenetFacade {
	String getBattleNetLoginUrl(HttpServletRequest request);

	void getOAuthTokenAfterLogin(HttpServletRequest request, String state, String code);
}
