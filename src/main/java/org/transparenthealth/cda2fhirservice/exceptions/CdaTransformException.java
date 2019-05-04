package org.transparenthealth.cda2fhirservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CdaTransformException extends RuntimeException {
  public CdaTransformException() {
    super();
  }
  public CdaTransformException(String message, Throwable cause) {
    super(message, cause);
  }
  public CdaTransformException(String message) {
    super(message);
  }
  public CdaTransformException(Throwable cause) {
    super(cause);
  }
}