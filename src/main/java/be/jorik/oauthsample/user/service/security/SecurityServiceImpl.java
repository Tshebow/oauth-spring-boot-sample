package be.jorik.oauthsample.user.service.security;

import be.jorik.oauthsample.user.util.SessionUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class SecurityServiceImpl implements SecurityService {

	private static final int STATE_BIT_LENGTH = 130;
	private static final int STATE_RANDOMIZE_RADIX = 64;

	@Override
	public String getRandomizedState() {
		return new BigInteger(STATE_BIT_LENGTH, new SecureRandom()).toString(STATE_RANDOMIZE_RADIX);
	}

	@Override
	public void storeAsSecurityStateOnSession(HttpServletRequest request, String state) {
		HttpSession session = request.getSession(true);
		session.setAttribute("securityState", state);
	}

	@Override
	public void validateWithSecurityStateOnSession(HttpServletRequest request, String dataBlob) {
		String state = SessionUtil.getSessionAttribute(request, "securityState");
		if (state == null || !state.equals(dataBlob)) {
			throw new SecurityException("Unable to login user: invalid security state.");
		}
		SessionUtil.clearSessionAttribute(request, "securityState");
	}
}
