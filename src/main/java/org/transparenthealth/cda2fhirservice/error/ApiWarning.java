package org.transparenthealth.cda2fhirservice.error;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ApiWarning {
  private String message;
}
