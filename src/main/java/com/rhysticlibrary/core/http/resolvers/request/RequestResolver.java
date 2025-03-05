package com.rhysticlibrary.core.http.resolvers.request;

import com.rhysticlibrary.core.http.HttpEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public final class RequestResolver {

  private final RequestAttributeResolver requestAttributeResolver;

  public <T> void setAttribute(
      @NonNull HttpServletRequest request,
      @NonNull HttpEntity attribute,
      T value
  ) {
    this.requestAttributeResolver.setAttribute(request, attribute, value);
  }
}
