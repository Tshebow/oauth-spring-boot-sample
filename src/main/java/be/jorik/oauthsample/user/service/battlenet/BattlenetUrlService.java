package be.jorik.oauthsample.user.service.battlenet;

import javax.servlet.http.HttpServletRequest;

public interface BattlenetUrlService {
	String getBattleNetLoginUrl(HttpServletRequest request);

	String getBattleNetCallBackUri(HttpServletRequest request);
}
