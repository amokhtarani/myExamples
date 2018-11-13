package com.wellsfargo.messaging.response;

/**
 * Created by u554732 on 12/13/16.
 */
public class ResponseFactory {

  //  public static AbstractResponse createResponseBuilder(String name) {
        public static ResponseBuilder createResponseBuilder(String name) {

        if(name.equals("kasisto"))
            return new KasistoResponse();
        return null;
    }
}
