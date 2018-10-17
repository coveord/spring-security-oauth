package org.springframework.security.oauth2.provider.token;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.RequestTokenFactory;

/**
 * @author Dave Syer
 *
 */
public abstract class AbstractPersistentDefaultTokenServicesTests extends AbstractDefaultTokenServicesTests
{

    @Test
    public void testOneAccessTokenPerUniqueAuthentication() throws Exception
    {
        getTokenServices().createAccessToken(new OAuth2Authentication(RequestTokenFactory.createOAuth2Request("id",
                                                                                                              false,
                                                                                                              Collections.singleton("read")),
                                                                      new TestAuthentication("test2", false)));
        assertEquals(1, getAccessTokenCount());
        getTokenServices().createAccessToken(new OAuth2Authentication(RequestTokenFactory.createOAuth2Request("id",
                                                                                                              false,
                                                                                                              Collections.singleton("write")),
                                                                      new TestAuthentication("test2", false)));
        assertEquals(2, getAccessTokenCount());
    }

    protected abstract int getAccessTokenCount();

    protected abstract int getRefreshTokenCount();

}
