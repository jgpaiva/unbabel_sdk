package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonProperty;

public class Topic {
   @JsonProperty
   public String name;
   
   public String toString(){
      return this.name;
   }
}