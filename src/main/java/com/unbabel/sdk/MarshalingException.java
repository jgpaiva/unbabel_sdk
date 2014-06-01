package com.unbabel.sdk;

public class MarshalingException extends RuntimeException {
   private static final long serialVersionUID = 7282487627306007198L;

   public MarshalingException(Exception e) {
      super(e);
   }
}
