# cda2fhir-service
CDA To Fhir (JSON) Service uses the cda2fhir library by amida-tech to provide a service that accepts a CDA formatted XML 
document and returns the data converted to fhir in JSON format.  It supports FHIR 3.

It is built using the code called CDA2FHIR.  Its custodian has changed hands a few times. The current custodian is 
UPMC Enterprises  https://enterprises.upmc.com/  The source code is here: https://github.com/upmc-enterprises/cda2fhir


# Binary Download and Install 

The download allows for install in Tomcat and other tools. This service is a good 
candidate for container services such as AWS Elastic Beanstalk.

Download the WAR directly from the master branch. See `cda2fhir-service-0.0.1.war`.

This service is a good candidate for container services such as AWS Elastic Beanstalk.


## Build Instructions Installation

Apache Maven is required to build cda2fhir-service. Please visit http://maven.apache.org/ in order to install Maven on your system.

Under the root directory of the cda2fhir-service project run the following:

	$ cda2fhir-service> mvn install

In order to make a clean install run the following:

	$ cda2fhir-service> mvn clean install
	
To run the server locally:

	$ cda2fhir-service> mvn spring-boot:run

	
## API Documentation

Perform an HTTP POST of the CCDA you wish to convert.  The response is in JSON.
A couple other GET commands say hello and report health.



### Hello
##### Request GET /
###### Response

    
    {"status":"UP"}



### Hello  
##### GET /
###### Response

     {"message" : "Welcome to the CDA to FHIR Conversion Service"}
    

### Convert
##### Request POST /api/convert consumes `application/xml`

###### Response




###### Sample Request (partial for brevity)




      <?xml version="1.0" encoding="UTF-8"?>
      <ClinicalDocument xmlns="urn:hl7-org:v3">
          <realmCode code="US"/>
          <typeId root="2.16.840.1.113883.1.3" extension="POCD_HD000040"/>
          ...
          ...
       </ClinicalDocument>



###### Sample `curl` Request
```
curl http://localhost:8080/api/convert -X POST -d @cda-filename.xml -H "Content-Type: application/xml"
```

###### Sample Response (partial)
```
  {
    "entry": [
      {
        "resource": {
          "id": "1",
          "subject": {
            "display": "MICKEY M MOUSE, MICKEY MOUSE",
            "reference": "Patient/2"
          },
          "author": [
            {
              "reference": "Device/3"
            }
          ],
          "resourceType": "Composition",
          ...
          ...
        }
      }
    ]
  }  
```




