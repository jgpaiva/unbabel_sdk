package com.unbabel.sdk;


public class UnauthorizedException extends RuntimeException{
   private static final long serialVersionUID = 4728622267489005744L;
   
   public UnauthorizedException(Throwable cause) {
      super(cause);
   }
}
