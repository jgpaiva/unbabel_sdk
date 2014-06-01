package com.unbabel.sdk;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class LangPair {
   @JsonProperty("source_language")
	public Language sourceLanguage;
   @JsonProperty("target_language")
	public Language targetLanguage;

	public String toString() {
		return String.format("%s_%s", sourceLanguage, targetLanguage);
	}
	
   @JsonCreator
   public static LangPair fromJSON(String raw_response) throws MarshalingException{
      ObjectMapper mapper = new ObjectMapper();
      try {
         LangPair langPair = mapper.readValue(raw_response, LangPair.class);
         return langPair;
      } catch (JsonParseException e) {
         throw new MarshalingException(e);
      } catch (JsonMappingException e) {
         throw new MarshalingException(e);
      } catch (IOException e) {
         throw new MarshalingException(e);
      }
   }
}