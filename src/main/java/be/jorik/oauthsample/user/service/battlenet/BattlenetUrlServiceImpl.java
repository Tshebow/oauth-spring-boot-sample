package be.jorik.oauthsample.user.service.battlenet;

import be.jorik.oauthsample.user.service.security.SecurityService;
import be.jorik.oauthsample.user.util.UrlUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

@Repository
public class BattlenetUrlServiceImpl implements BattlenetUrlService {

	private String battleNetAuthUrl;
	private SecurityService securityService;

	public BattlenetUrlServiceImpl(@Value("${blizzard.auth.url}") String battleNetAuthUrl, SecurityService securityService) {
		this.battleNetAuthUrl = battleNetAuthUrl;
		this.securityService = securityService;
	}

	@Override
	public String getBattleNetLoginUrl(HttpServletRequest request) {
		String securityState = securityService.getRandomizedState();
		securityService.storeAsSecurityStateOnSession(request, securityState);
		return battleNetAuthUrl.replace("{security.state}", securityState).replace("{redirect.uri}", getBattleNetCallBackUri(request));
	}

	@Override
	public String getBattleNetCallBackUri(HttpServletRequest request) {
		return UrlUtil.getBaseUrlWithPath(request, "/user/token");
	}
}
