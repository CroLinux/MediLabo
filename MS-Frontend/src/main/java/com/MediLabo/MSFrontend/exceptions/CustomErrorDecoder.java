package com.MediLabo.MSFrontend.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder defaultErrorDecoder = new Default();

@Override
  public Exception decode(String invoqueur, Response reponse) {
      if(reponse.status() == 404 ) {
        return new PatientBadRequestException(
              "Requête incorrecte."
        );
      }
      if(reponse.status() == 409 ) {
          return new PatientBadRequestException(
                "Valeurs déjà existante."
          );
        }
      if(reponse.status() == 500 ) {
          return new PatientBadRequestException(
                "Veuillez contacter l'administrateur de l'application."
          );
        }
      return defaultErrorDecoder.decode(invoqueur, reponse);
  }
}
