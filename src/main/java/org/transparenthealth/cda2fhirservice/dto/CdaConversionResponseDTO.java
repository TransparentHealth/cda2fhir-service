package org.transparenthealth.cda2fhirservice.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.transparenthealth.cda2fhirservice.error.ApiWarning;

import java.util.List;

@Slf4j
@Data
public class CdaConversionResponseDTO {
  private String fhir;
  private List<ApiWarning> warnings;
}
