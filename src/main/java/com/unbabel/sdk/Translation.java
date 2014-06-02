package com.unbabel.sdk;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class Translation {
   @JsonProperty
   public String uid = null;
   @JsonProperty
   public String text = null;
   @JsonProperty
   public String translatedText = null;
   @JsonProperty("target_language")
   public String targetLanguage = null;
   @JsonProperty("source_language")
   public String sourceLanguage = null;
   @JsonProperty
   public String status = null;
   @JsonProperty
   public ArrayList<String> translators = null;
   @JsonProperty
   public ArrayList<String> topics = null;
   @JsonProperty
   public Integer price = null;


   @JsonProperty
   public String client = null;
   @JsonProperty("resource_uri")
   public String resourceUri = null;
   @JsonProperty
   public String balance = null;

   public Translation() {
   }

   public String toString() {
      return String.format("%s %s %s_%s", this.uid, this.status,
            this.sourceLanguage, this.targetLanguage);
   }

   public static Translation fromJSON(String raw_response)
         throws MarshalingException {
      ObjectMapper mapper = new ObjectMapper();
      try {
         Translation translation = mapper.readValue(raw_response,
               Translation.class);
         return translation;
      } catch (JsonParseException e) {
         throw new MarshalingException(e);
      } catch (JsonMappingException e) {
         throw new MarshalingException(e);
      } catch (IOException e) {
         throw new MarshalingException(e);
      }
   }

   public String toJSON() throws MarshalingException {
      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setSerializationInclusion(Inclusion.NON_NULL);
         return mapper.writeValueAsString(this);
      } catch (JsonGenerationException e) {
         throw new MarshalingException(e);
      } catch (JsonMappingException e) {
         throw new MarshalingException(e);
      } catch (IOException e) {
         throw new MarshalingException(e);
      }
   }

   public boolean equals(Object other){
      if(!(other instanceof Translation))
         return false;
      Translation o = (Translation)other;
      return o.uid == this.uid;
   }
}
