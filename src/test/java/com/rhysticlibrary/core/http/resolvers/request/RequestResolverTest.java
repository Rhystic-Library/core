package com.rhysticlibrary.core.http.resolvers.request;

import com.rhysticlibrary.core.BaseComponent;
import com.rhysticlibrary.core.http.HttpEntity;
import com.rhysticlibrary.core.http.attributes.GeneralRequestAttribute;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

final class RequestResolverTest extends BaseComponent {

  private static final HttpEntity ATTRIBUTE = GeneralRequestAttribute.START_TIME;
  @Autowired
  private RequestResolver requestResolver;
  @MockitoBean
  private RequestAttributeResolver requestAttributeResolverMock;
  @Spy
  private MockHttpServletRequest requestSpy;

  @Test
  void testGetAttribute() {
    when(this.requestAttributeResolverMock.getAttribute(
        any(HttpServletRequest.class),
        eq(ATTRIBUTE)
    ))
        .thenReturn(super.timestampGenerator.generateFixedTimestamp());
    Instant instant = this.requestResolver.getAttribute(this.requestSpy, ATTRIBUTE);

    verify(this.requestAttributeResolverMock, times(1))
        .getAttribute(
            any(HttpServletRequest.class),
            eq(ATTRIBUTE)
        );
    
    assertThat(instant)
        .isEqualTo(super.timestampGenerator.generateFixedTimestamp());
  }

  @Test
  void testSetAttribute() {
    this.requestResolver.setAttribute(this.requestSpy, ATTRIBUTE, super.timestampGenerator.generateFixedTimestamp());

    verify(this.requestAttributeResolverMock, times(1))
        .setAttribute(
            any(HttpServletRequest.class),
            eq(ATTRIBUTE),
            eq(super.timestampGenerator.generateFixedTimestamp())
        );
  }
}