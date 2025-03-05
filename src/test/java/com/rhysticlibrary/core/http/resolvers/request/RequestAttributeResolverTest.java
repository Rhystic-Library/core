package com.rhysticlibrary.core.http.resolvers.request;

import com.rhysticlibrary.core.BaseComponent;
import com.rhysticlibrary.core.exceptions.http.HttpEntityNotFoundException;
import com.rhysticlibrary.core.http.HttpEntity;
import com.rhysticlibrary.core.http.attributes.GeneralRequestAttribute;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

final class RequestAttributeResolverTest extends BaseComponent {

  private static final HttpEntity ATTRIBUTE = GeneralRequestAttribute.START_TIME;
  @Autowired
  private RequestAttributeResolver requestAttributeResolver;
  @Spy
  private MockHttpServletRequest requestSpy = new MockHttpServletRequest();

  @Test
  public void testGetAttribute() {
    this.setAttribute();

    assertThat((Instant) this.requestAttributeResolver.getAttribute(this.requestSpy, ATTRIBUTE))
        .isEqualTo(super.timestampGenerator.generateFixedTimestamp());
    verify(this.requestSpy, times(1))
        .getAttribute(
            eq(GeneralRequestAttribute.START_TIME.getValue())
        );
  }

  @Test
  public void testGetAttribute_ThrowsWhenNotFound() {
    assertThatThrownBy(() -> this.requestAttributeResolver.getAttribute(this.requestSpy, ATTRIBUTE))
        .isInstanceOf(HttpEntityNotFoundException.class)
        .hasMessage(HttpEntityNotFoundException.forEntity(ATTRIBUTE).getMessage());
  }

  @Test
  public void testSetAttribute() {
    this.requestAttributeResolver.setAttribute(this.requestSpy, ATTRIBUTE, super.timestampGenerator.generateFixedTimestamp());

    assertThat(this.getAttribute())
        .isEqualTo(super.timestampGenerator.generateFixedTimestamp());
    verify(this.requestSpy, times(1))
        .setAttribute(
            eq(ATTRIBUTE.getValue()),
            eq(super.timestampGenerator.generateFixedTimestamp())
        );
  }

  private Object getAttribute() {
    return this.requestSpy.getAttribute(ATTRIBUTE.getValue());
  }

  private void setAttribute() {
    this.requestSpy.setAttribute(ATTRIBUTE.getValue(), super.timestampGenerator.generateFixedTimestamp());
  }
}