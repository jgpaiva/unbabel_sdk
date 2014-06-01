package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonProperty;

public class Language {
   @JsonProperty("shortname")
   public String shortName;
   @JsonProperty
   public String name;

   public String toString() {
      return name;
   }
}