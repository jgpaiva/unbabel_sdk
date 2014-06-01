package com.unbabel.sdk;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.wink.client.ClientWebException;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class UnbabelApi {
   public static final String API_URL = "https://www.unbabel.co/tapi/v2/";
   public static final String SANDBOX_API_URL = "http://sandbox.unbabel.com/tapi/v2/";
   private final String username;
   private final String apiKey;
   protected String contact_url;
   private final RestClient client;
   
   public UnbabelApi(String username, String apiKey, boolean sandbox) {
      this.username = username;
      this.apiKey = apiKey;
      if (sandbox) {
         this.contact_url = UnbabelApi.SANDBOX_API_URL;
      } else {
         this.contact_url = UnbabelApi.API_URL;
      }
      this.client = new RestClient();
   }

   /**
    * Posts a translation
    */
   public Translation postTranslation(Translation translation) {
      String raw_response = post(contact_url + "/translation/",
            translation.toJSON());
      return Translation.fromJSON(raw_response);
   }
   
   /**
    * Bulk posts translations
    */
   public List<Translation> postTranslations(List<Translation> translations) {
      String raw_response = post(contact_url + "/translation/",
            Utils.encodeObjectArray(translations));
      return Utils.decodeObjectArray(raw_response, "", Translation.class);
   }

   /**
    * Returns the translations requested by the user
    */
   public List<Translation> getTranslations() {
      String raw_response = this.get(contact_url + "/translation/");
      return Utils.decodeObjectArray(raw_response, "", Translation.class);
   }

   /**
    * Returns a translation with the given id
    */
   public Translation getTranslation(String uid) {
      String raw_response = this.get(contact_url + "/translation/" + uid);
      return Translation.fromJSON(raw_response);
   }

   /**
    * Returns the language pairs available on unbabel
    */
   public List<LangPair> getLanguagePairs() {
      return getLanguagePairs(null);
   }

   public List<LangPair> getLanguagePairs(String trainLangs)
         throws MarshalingException {
      String resourceURL = null;
      if (trainLangs == null)
         resourceURL = this.contact_url + "language_pair/";
      else
         resourceURL = String.format("%slanguage_pair/?train_langs=%s",
               this.contact_url, trainLangs);
      String raw_response = this.get(resourceURL);
      return Utils.decodeObjectArray(raw_response, "lang_pair", LangPair.class);
   }

   /**
    * Returns the tones available on unbabel
    */
   public List<Tone> getTones() {
      String raw_response = this.get(contact_url + "/tone/");
      return Utils.decodeObjectArray(raw_response, "tone", Tone.class);
   }

   /**
    * Returns the tones available on unbabel
    */
   public List<Topic> getTopics() {
      String raw_response = this.get(contact_url + "/topic/");
      return Utils.decodeObjectArray(raw_response, "topic", Topic.class);
   }

   protected String get(String resourceURL) {
      try {
         Resource resource = client.resource(resourceURL);
         this.setHeaders(resource);
         return resource.get(String.class);
      } catch (ClientWebException e) {
         throw processException(e);
      }
   }

   protected String post(String resourceURL, String json) {
      try {
         Resource resource = client.resource(resourceURL);
         this.setHeaders(resource);
         return resource.post(String.class, json);
      } catch (ClientWebException e) {
         throw processException(e);
      }
   }

   private void setHeaders(Resource resource) {
      resource.header("Authorization", String.format("ApiKey %s:%s", this.username,
            this.apiKey));
      resource.contentType(MediaType.APPLICATION_JSON);
      resource.accept(MediaType.APPLICATION_JSON);
   }

   private RuntimeException processException(ClientWebException e) {
      int statusCode = e.getResponse().getStatusCode();
      if (statusCode == 401) {
         return new UnauthorizedException(e);
      }
      if (statusCode == 400) {
         return new BadRequestException(e);
      } else
         return new ApiException("Status code " + statusCode,e);
   }
}
