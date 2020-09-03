package io.quarkus.rest.runtime.jaxrs;

import java.util.Date;
import java.util.Locale;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Variant;
import javax.ws.rs.ext.RuntimeDelegate;

import io.quarkus.rest.runtime.headers.CacheControlDelegate;
import io.quarkus.rest.runtime.headers.CookieHeaderDelegate;
import io.quarkus.rest.runtime.headers.DateDelegate;
import io.quarkus.rest.runtime.headers.EntityTagDelegate;
import io.quarkus.rest.runtime.headers.LocaleDelegate;
import io.quarkus.rest.runtime.headers.MediaTypeHeaderDelegate;
import io.quarkus.rest.runtime.headers.NewCookieHeaderDelegate;

public class QuarkusRestRuntimeDelegate extends RuntimeDelegate {

    @Override
    public UriBuilder createUriBuilder() {
        return new QuarkusRestUriBuilder();
    }

    @Override
    public Response.ResponseBuilder createResponseBuilder() {
        return new QuarkusRestResponseBuilder();
    }

    @Override
    public Variant.VariantListBuilder createVariantListBuilder() {
        return new QuarkusRestVariantListBuilder();
    }

    @Override
    public <T> T createEndpoint(Application application, Class<T> endpointType)
            throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> HeaderDelegate<T> createHeaderDelegate(Class<T> type) throws IllegalArgumentException {
        if (type.equals(MediaType.class)) {
            return MediaTypeHeaderDelegate.INSTANCE;
        } else if (type.equals(Date.class)) {
            return (HeaderDelegate<T>) DateDelegate.INSTANCE;
        } else if (type.equals(CacheControl.class)) {
            return (HeaderDelegate<T>) CacheControlDelegate.INSTANCE;
        } else if (type.equals(NewCookie.class)) {
            return (HeaderDelegate<T>) NewCookieHeaderDelegate.INSTANCE;
        } else if (type.equals(Cookie.class)) {
            return (HeaderDelegate<T>) CookieHeaderDelegate.INSTANCE;
        } else if (type.equals(EntityTag.class)) {
            return (HeaderDelegate<T>) EntityTagDelegate.INSTANCE;
        } else if (type.equals(Locale.class)) {
            return (HeaderDelegate<T>) LocaleDelegate.INSTANCE;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Link.Builder createLinkBuilder() {
        return new QuarkusRestLinkBuilder();
    }
}