package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.text.StringEscapeUtils;

// Handler value: example.HandlerString
public class HandlerString implements RequestHandler<String, String>{
  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  @Override
  public String handleRequest(String event, Context context)
  {
    LambdaLogger logger = context.getLogger();

    String eventAfterUnescape = StringEscapeUtils.unescapeJava(event);
    logger.log(eventAfterUnescape + "\n");

    // process event
    //logger.log("EVENT: " + gson.toJson(eventAfterUnescape) + "\n");
    //logger.log("EVENT TYPE: " + event.getClass().toString() + "\n");

    String name = JsonParser
            .parseString(eventAfterUnescape)
            .getAsJsonObject()
            .get("name")
            .getAsString();

    //logger.log("Hello " + name + "\n");
    //logger.log(event);
    //return context.getRemainingTimeInMillis() ;
    return "Hello " + name;
  }
}