package com.wellsfargo.messaging.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.messaging.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by u554732 on 12/13/16.
 */
@org.springframework.context.annotation.Configuration
public class KasistoResponse implements ResponseBuilder { //extends AbstractResponse {

    private final static Logger logger = LoggerFactory.getLogger(KasistoResponse.class);

//    @Value("${templateDir}")
//    private String templateDir;

    @Override
    public String buildResponse(JsonArray jArray) {
        List<String> accounts = new ArrayList<String>();
        String templateDir = Configuration.getProp("templateDir");
        STGroup stg = new STGroupFile(templateDir + "/accounts.stg");
        if(stg == null) {
            logger.error("Could not find group file");
        }
        logger.info("Loading template for Kasisto");

        for(int i=0; i < jArray.size(); i++) {
            ST st = stg.getInstanceOf("account");
            JsonObject obj = jArray.getJsonObject(i);


            st.add("accountId", obj.getJsonString("accountId"));
            st.add("accountNumber", obj.getJsonString("accountNumber"));
            st.add("accountName", obj.getJsonString("accountName"));
            st.add("accountType", obj.getJsonString("accountType"));
            st.add("currencyCode", obj.getJsonString("currencyCode"));
            st.add("availableBalance", obj.getJsonString("availableBalance"));
            st.add("currentBalance", obj.getJsonString("currentBalance"));
            accounts.add(st.render());

        }
        return accounts.toString();

    }
    @Override
    public String buildResponse(List<String> accountList)  {
        List<String> accounts = new ArrayList<String>();
        String templateDir = Configuration.getProp("templateDir");
        STGroup stg = new STGroupFile(templateDir + "/accounts.stg");
        if(stg == null) {
            logger.error("Could not find group file");
        }
        logger.info("Loading template for Kasisto");

        try {
            for (String acc : accountList) {
                ST st = stg.getInstanceOf("account");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(acc);
                JsonNode descNode = rootNode.path("descriptor");

                JsonNode balanceNode = rootNode.get("available_balance") != null ?
                        rootNode.get("available_balance") :
                        rootNode.get("available_credit");
                Long available_balance = balanceNode.asLong();
                st.add("accountId", descNode.get("account_id"));
                st.add("accountNumber", rootNode.get("account_number").textValue());
                st.add("accountName", descNode.get("display_name").textValue());
                st.add("accountType", descNode.get("account_type").textValue());
                st.add("currencyCode", rootNode.get("currency_code").textValue());
                st.add("availableBalance", available_balance);
                st.add("currentBalance", rootNode.get("current_balance").asLong());
                accounts.add(st.render());

            }
        } catch (IOException e) {
            logger.error("Error  creating kasisto response");
        }
        return accounts.toString();

    }
}
/*
Sample SDE account detail
{
  "descriptor": {
    "account_id": "fc933eac8bd4b30456b9465e43644563128a41fab3235d42f3e182bc901bb3d7",
    "account_type": "CREDITCARD",
    "display_name": "abc ...7759",
    "status": "OPEN",
    "description": "Retail Sales Finance"
  },
  "account_number": "...7759",
  "line_of_business": "CONSUMER",
  "nickname": "abc",
  "balance_type": "LIABILITY",
  "currency_code": "USD",
  "fiid_attributes": [
    {
      "name": "lastStatementBalance",
      "value": "20400.00"
    },
    {
      "name": "lastStatementBalanceAsOf",
      "value": "2016-11-18"
    }
  ],
  "current_balance": 20400.00,
  "balance_as_of": "2016-11-20",
  "credit_line": 50000,
  "available_credit": 29600,
  "next_payment_amount": 680.00,
  "next_payment_date": "2016-12-13",
  "minimum_payment_amount": 680.00,
  "last_payment_amount": 15.00,
  "last_payment_date": "2016-02-09"
}
 */
