package com.unbabel.sdk;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class Utils {
   protected static <T> ArrayList<T> decodeObjectArray(String raw_response,
         String class_name, Class<T> toLoad) throws MarshalingException {
      try {
         ArrayList<T> retVal = new ArrayList<T>();

         ObjectMapper mapper = new ObjectMapper();
         JsonNode rootNode = mapper.readTree(raw_response);
         JsonNode objects = rootNode.get("objects");
         for (JsonNode obj : objects) { // go through array
            JsonNode json_pair = null;
            if (class_name != "")
               json_pair = obj.get(class_name);
            else
               json_pair = obj;
            T pair = mapper.readValue(json_pair, toLoad);
            retVal.add(pair);
         }
         return retVal;
      } catch (JsonParseException e) {
         throw new MarshalingException(e);
      } catch (IOException e) {
         throw new MarshalingException(e);
      }
   }

   protected static String encodeObjectArray(List<?> objArray)
         throws MarshalingException {
      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setSerializationInclusion(Inclusion.NON_NULL);
         JsonFactory f = new JsonFactory();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         JsonGenerator g = f.createJsonGenerator(baos);
         g.setCodec(mapper);
         g.writeStartObject();
         g.writeObjectField("objects", objArray);
         g.writeEndObject();
         g.close();
         return baos.toString();
      } catch (JsonParseException e) {
         throw new MarshalingException(e);
      } catch (IOException e) {
         throw new MarshalingException(e);
      }
   }

   protected static String fileToString(String filename) throws IOException {
      FileInputStream fis;
      fis = new FileInputStream(ClassLoader.getSystemResource(filename)
            .getFile());
      BufferedReader br = new BufferedReader(new InputStreamReader(fis,
            Charset.forName("UTF-8")));
      String line = "";
      String retVal = "";
      String delimiter = "";
      while ((line = br.readLine()) != null) {
         retVal = retVal + delimiter + line;
         delimiter = "\n";
      }
      br.close();
      return retVal;
   }
}
