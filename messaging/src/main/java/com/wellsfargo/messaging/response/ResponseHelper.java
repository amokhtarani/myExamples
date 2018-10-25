package com.wellsfargo.messaging.response;

import com.wellsfargo.messaging.config.Configuration;
import com.wellsfargo.messaging.util.AccountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonArray;
import java.util.List;

/**
 * Created by u554732 on 12/20/16.
 */
public class ResponseHelper {

    private final static Logger logger = LoggerFactory.getLogger(ResponseHelper.class);

    public static JsonArray getResponse() {
        AccountUtil util = new AccountUtil();
        JsonArray jsonArray = util.createSDEResponse();

        return jsonArray;
    }

    public static JsonArray getResponse(List<String> accounts) {
        AccountUtil util = new AccountUtil();
        JsonArray jsonArray = util.createSDEResponseForKasisto(accounts);

        return jsonArray;
    }

    public static String getBotName() {

        return Configuration.getProp("botName");

    }
}
