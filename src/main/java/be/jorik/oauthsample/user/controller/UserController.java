package be.jorik.oauthsample.user.controller;

import be.jorik.oauthsample.user.service.battlenet.BattlenetFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	private BattlenetFacade battlenetFacade;

	public UserController(BattlenetFacade battlenetUrlService) {
		this.battlenetFacade = battlenetUrlService;
	}

	@RequestMapping(value = "/user/login")
	public String login(HttpServletRequest request) {
		return "redirect:" + battlenetFacade.getBattleNetLoginUrl(request);
	}

	@RequestMapping(value = "user/token")
	public String getOAuthTokenAfterLogin(HttpServletRequest request, @RequestParam("state") String state, @RequestParam("code") String code) {
		battlenetFacade.getOAuthTokenAfterLogin(request, state, code);
		return "redirect: /home";
	}
}
