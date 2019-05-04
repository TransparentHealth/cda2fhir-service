package org.transparenthealth.cda2fhirservice.service;

import ca.uhn.fhir.parser.IParser;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Identifier;
import org.openhealthtools.mdht.uml.cda.consol.ConsolPackage;
import org.openhealthtools.mdht.uml.cda.consol.ContinuityOfCareDocument;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.springframework.stereotype.Service;
import org.transparenthealth.cda2fhirservice.exceptions.CdaTransformException;
import tr.com.srdc.cda2fhir.conf.Config;
import tr.com.srdc.cda2fhir.transform.CCDTransformerImpl;
import tr.com.srdc.cda2fhir.transform.ICDATransformer;
import tr.com.srdc.cda2fhir.util.IdGeneratorEnum;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

@Service
@Data
@Slf4j
public class CdaConversionService {
  public String getFhirJsonFromCdaXMLString(@NonNull String cdaXML) throws CdaTransformException {
    InputStream cdaStringToInputStream = new ByteArrayInputStream(cdaXML.getBytes(Charset.forName("UTF-8")));

    ContinuityOfCareDocument ccd;
    String fhir = "";
    try {
      ccd = (ContinuityOfCareDocument) CDAUtil.loadAs(cdaStringToInputStream, ConsolPackage.eINSTANCE.getContinuityOfCareDocument());

      ICDATransformer ccdTransformer = new CCDTransformerImpl(IdGeneratorEnum.COUNTER);
      Config.setGenerateDafProfileMetadata(true);

      /**
       *  NOTE: this may not be necessary, its in their online docs, but it forces to the search for a
       *        customnarrative.properties file, which i just added as a blank file to solve the problem
       */
      //Config.setGenerateNarrative(true);

      Identifier identifier = new Identifier();
      identifier.setValue("Data Processing Engine");

      Bundle bundle = ccdTransformer.transformDocument(ccd, cdaXML, identifier);
      IParser jsonParser = Config.getFhirContext().newJsonParser();
      fhir = jsonParser.encodeResourceToString(bundle);
    } catch (Exception e) {
      log.error("Caught an exception trying to deserialize the cda xml.", e);
      throw new CdaTransformException("Error trying to parse CDA XML", e.getCause());
    }

    return fhir;
  }
}
