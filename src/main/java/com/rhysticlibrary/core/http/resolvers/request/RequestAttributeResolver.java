package com.rhysticlibrary.core.http.resolvers.request;

import com.rhysticlibrary.core.exceptions.http.HttpEntityNotFoundException;
import com.rhysticlibrary.core.http.HttpEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Resolver for setting and retrieving Attributes on the request.
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
public final class RequestAttributeResolver {

  /**
   * Retrieves an attribute on the request.
   *
   * @param request the request containing the attribute.
   * @param entity  The Attribute to retrieve.
   * @param <T>     The type of the attribute retrieved.
   * @return the retrieved attribute.
   */
  @SuppressWarnings("unchecked")
  public <T> @NonNull T getAttribute(
      @NonNull HttpServletRequest request,
      @NonNull HttpEntity entity
  ) {
    Class<T> tClass = (Class<T>) entity.getTClass();
    return tClass.cast(this.getRawAttributeOrThrow(request, entity));
  }

  /**
   * Sets an attribute on the request.
   *
   * @param request the request to set the attribute on.
   * @param entity  The Attribute to set.
   * @param value   The value to set for that given attribute.
   * @param <T>     The type of value that will be set as an attribute.
   */
  public <T> void setAttribute(
      @NonNull HttpServletRequest request,
      @NonNull HttpEntity entity,
      T value
  ) {
    request.setAttribute(entity.getValue(), value);
  }

  private Object getRawAttributeOrThrow(
      HttpServletRequest request,
      HttpEntity entity
  ) {
    return this.getRawAttribute(request, entity).orElseThrow(() -> HttpEntityNotFoundException.forEntity(entity));
  }

  private Optional<Object> getRawAttribute(HttpServletRequest request, HttpEntity entity) {
    Object rawAttribute = request.getAttribute(entity.getValue());
    return rawAttribute != null ? Optional.of(rawAttribute) : Optional.empty();
  }
}
