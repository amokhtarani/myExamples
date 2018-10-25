package com.wellsfargo.messaging.response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.json.JsonArray;

/**
 * Created by u554732 on 12/13/16.
 */
@Configuration
public abstract class AbstractResponse {

    public String buildResponse(JsonArray jArray) { return null;}

}
