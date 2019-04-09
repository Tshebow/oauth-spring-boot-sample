package be.jorik.oauthsample.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthToken {

    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "scope")
    private String scope;
    @JsonProperty(value = "expires_in")
    private String expirationDate;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
