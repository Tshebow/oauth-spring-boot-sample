package be.jorik.oauthsample.user.service.battlenet;

import be.jorik.oauthsample.user.service.security.SecurityService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BattlenetFacadeImpl implements BattlenetFacade {

	private BattlenetOAuthRepository battlenetOAuthRepository;
	private BattlenetUrlService battlenetUrlService;
	private SecurityService securityService;

	public BattlenetFacadeImpl(
			BattlenetOAuthRepository battlenetOAuthRepository,
			BattlenetUrlService battlenetUrlService,
			SecurityService securityService
	) {
		this.battlenetOAuthRepository = battlenetOAuthRepository;
		this.battlenetUrlService = battlenetUrlService;
		this.securityService = securityService;
	}

	@Override
	public String getBattleNetLoginUrl(HttpServletRequest request) {
		return battlenetUrlService.getBattleNetLoginUrl(request);
	}

	@Override
	public void getOAuthTokenAfterLogin(HttpServletRequest request, String state, String code) {
		securityService.validateWithSecurityStateOnSession(request, state);
		battlenetOAuthRepository.getOAuthTokenWithCode(code, battlenetUrlService.getBattleNetCallBackUri(request));
	}
}
