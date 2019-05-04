package org.transparenthealth.cda2fhirservice.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.transparenthealth.cda2fhirservice.service.CdaConversionService;

@RestController
@Data
@Slf4j
@RequestMapping("/api")
public class FileConversionController {

  @Autowired
  private CdaConversionService cdaConversionService;

  @GetMapping("/convert")
  public ResponseEntity<String> convertCdaToFhirGet() {
    String greeting = "Welcome to the CDA to FHIR Conversion Service";

    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }

  @PostMapping(value = "/convert", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> convertCdaToFhirPost(@RequestBody String cdaXML) throws RuntimeException {
    log.debug("XMl input: {}", cdaXML);

    String fhir = cdaConversionService.getFhirJsonFromCdaXMLString(cdaXML);

    return new ResponseEntity<>(fhir, HttpStatus.OK);
  }

}
