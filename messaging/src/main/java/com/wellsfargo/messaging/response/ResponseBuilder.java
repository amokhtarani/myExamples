package com.wellsfargo.messaging.response;

import javax.json.JsonArray;
import java.util.List;

/**
 * Created by u554732 on 12/21/16.
 */
public interface ResponseBuilder {

    public String buildResponse(JsonArray jArray);
    public String buildResponse(List<String> list);
}
