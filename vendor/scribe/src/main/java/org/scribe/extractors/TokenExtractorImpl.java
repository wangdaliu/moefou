package org.scribe.extractors;

import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of {@link RequestTokenExtractor} and {@link AccessTokenExtractor}. Conforms to OAuth 1.0a
 * <p/>
 * The process for extracting access and request tokens is similar so this class can do both things.
 *
 * @author Pablo Fernandez
 */
public class TokenExtractorImpl implements RequestTokenExtractor, AccessTokenExtractor {
    private static final Pattern TOKEN_REGEX = Pattern.compile("oauth_token=([^&]+)");
    private static final Pattern SECRET_REGEX = Pattern.compile("oauth_token_secret=([^&]*)");

    /**
     * {@inheritDoc}
     */
    public Token extract(String response) {
        Preconditions.checkEmptyString(response, "Response body is incorrect. Can't extract a token from an empty string");
        String token = extract(response, TOKEN_REGEX);
        String secret = extract(response, SECRET_REGEX);
        return new Token(token, secret, response);
    }

    private String extract(String response, Pattern p) {
        Matcher matcher = p.matcher(response);
        if (matcher.find() && matcher.groupCount() >= 1) {
            return OAuthEncoder.decode(matcher.group(1));
        } else {
            throw new OAuthException("Response body is incorrect. Can't extract token and secret from this: '" + response + "'", null);
        }
    }
}
