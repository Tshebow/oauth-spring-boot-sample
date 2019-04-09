package be.jorik.oauthsample.user.service.battlenet;

import be.jorik.oauthsample.user.model.OAuthToken;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

@Repository
public class BattlenetOAuthRepositoryImpl implements BattlenetOAuthRepository {

	private String tokenUrl;
	private String clientSecret;
	private String clientId;

	public BattlenetOAuthRepositoryImpl(
			@Value("${blizzard.token.url}") String tokenUrl,
			@Value("${blizzard.client.secret}") String clientSecret,
			@Value("${blizzard.client.id}") String clientId
	) {
		this.tokenUrl = tokenUrl;
		this.clientSecret = clientSecret;
		this.clientId = clientId;
	}

	@Override
	public OAuthToken getOAuthTokenWithCode(String code, String validationUrl) {
		String credentials = createCredentials();
		String parameters = createParameters(code, validationUrl);
		return requestOauth2AccessToken(credentials, parameters);
	}

	private String createCredentials() {
		return "Basic " + new String(Base64.encodeBase64((clientId + ":" + clientSecret).getBytes()), Charset.forName("UTF-8"));
	}

	private String createParameters(String code, String validationUrl) {
		return "grant_type=authorization_code&redirect_uri=" + validationUrl + "&code=" + code;
	}

	private OAuthToken requestOauth2AccessToken(String credentials, String params) {
		URI url = fromHttpUrl(tokenUrl).build().toUri();
		try {
			return new RestTemplate().exchange(url, HttpMethod.POST, createHttpEntityWithCredentials(credentials, params), OAuthToken.class).getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new RuntimeException("url:" + url + " exception:" + e.getMessage(), e);
		}
	}

	private HttpEntity<String> createHttpEntityWithCredentials(String credentials, String data) {
		HttpHeaders headers = createHttpHeadersWithAuthorization(credentials, data);
		return new HttpEntity<>(data, headers);
	}

	private HttpHeaders createHttpHeadersWithAuthorization(String credentials, String data) {
		HttpHeaders headers = createHttpHeaders(data);
		headers.set(HttpHeaders.AUTHORIZATION, credentials);
		return headers;
	}

	private HttpHeaders createHttpHeaders(String data) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
		headers.setConnection("Keep-Alive");
		if (data != null) {
			headers.setContentLength(data.getBytes().length);
		}
		return headers;
	}
}
