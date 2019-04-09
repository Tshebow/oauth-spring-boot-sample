package be.jorik.oauthsample.user.service.battlenet;

import be.jorik.oauthsample.user.model.OAuthToken;

public interface BattlenetOAuthRepository {
	OAuthToken getOAuthTokenWithCode(String code, String validationUrl);
}
