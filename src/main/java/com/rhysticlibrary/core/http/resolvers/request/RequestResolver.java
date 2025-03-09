package com.rhysticlibrary.core.http.resolvers.request;

import com.rhysticlibrary.core.http.HttpEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Resolver for interacting with the request.
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public final class RequestResolver {

  private final RequestAttributeResolver requestAttributeResolver;

  /**
   * Facade for retrieving the attribute from the request.
   *
   * @param request   The request to retrieve the attribute from.
   * @param attribute The attribute to retrieve.
   * @param <T>       The type of the attribute.
   * @return The attribute retrieved.
   */
  public <T> @NonNull T getAttribute(
      @NonNull HttpServletRequest request,
      @NonNull HttpEntity attribute
  ) {
    return this.requestAttributeResolver.getAttribute(request, attribute);
  }

  /**
   * Facade for setting attributes on the request.
   *
   * @param request The request for setting the attribute on.
   * @param entity  The attribute to set.
   * @param value   The value to set for the attribute.
   * @param <T>     The type of the attribute.
   */
  public <T> void setAttribute(
      @NonNull HttpServletRequest request,
      @NonNull HttpEntity entity,
      T value
  ) {
    this.requestAttributeResolver.setAttribute(request, entity, value);
  }
}
