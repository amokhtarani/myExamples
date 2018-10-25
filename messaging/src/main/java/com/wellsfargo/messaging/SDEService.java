package com.wellsfargo.messaging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.messaging.request.SDERequest;
import com.wellsfargo.messaging.response.AbstractResponse;
import com.wellsfargo.messaging.response.ResponseBuilder;
import com.wellsfargo.messaging.response.ResponseFactory;
import com.wellsfargo.messaging.response.ResponseHelper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import javax.json.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by u554732 on 12/20/16.
 */

@Configuration
public class SDEService {

    private final static Logger logger = LoggerFactory.getLogger(SDEService.class);

    private final static String COMPANY_ID = "gateway-company-id";
    private final static String AUTHORIZATION = "Authorization";
    private final static String END_USER_AUTHORIZATION = "end-user-authorization";
    private final static String REQUEST_ID = "request-id";

//    @Resource
//    private ResponseBuilder botResponse;

    @Value("${botName}")
    private String botName;

    @Value("${account.list.uri}")
    private String accountsUri;

    @Value("${account.detail.uri}")
    private String accountUri;

    @Value("${sandbox.authorization}")
    private String authorization;

    @Value("${gateway.company.id}")
    private String gatewayCompanyId;

    @Value("${end.user.authorization}")
    private String endUserAuthorization;

 //   public HttpResponse getAccountDetails(SDERequest request) {
        public String getAccountDetails(SDERequest request) {

        /*
        need to make a call to get list of accounts for this user and then
        loop over account ids to details for each account
         */

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = createHttpGet(accountsUri);

        String message = "";
        List<String> accountIds = new ArrayList<>();
        CloseableHttpResponse sdeResponse = null;
        try {
            sdeResponse = httpClient.execute(httpGet);

            HttpEntity entity = sdeResponse.getEntity();
            message = EntityUtils.toString(entity);
            logger.info(message);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(message);

            JsonNode accountsNode = rootNode.path("accounts");

            Iterator<JsonNode> iter = accountsNode.elements();
            while(iter.hasNext()) {
                JsonNode account = iter.next();
                JsonNode accountId = account.get("account_id");
                String id = accountId.textValue();
                accountIds.add(id);
            }



        } catch (IOException ie) {
            logger.error("Error getting response from SDEService", ie.getMessage());

        }finally {
            if(sdeResponse != null)
                try {
                    sdeResponse.close();
                } catch (IOException ie) {

                }
     //       sdeResponse.close();
        }
        CloseableHttpResponse sdeAccountResponse = null;
        List<String> accounts = new ArrayList<>();
     try {
        for(String id : accountIds) {
            HttpGet httpGetAccount = createHttpGet(accountUri.replace("{account_id}",  id));
            sdeAccountResponse = httpClient.execute(httpGetAccount);
            int statusCode = sdeAccountResponse.getStatusLine().getStatusCode();
            logger.info("Status code {}", statusCode  );
            HttpEntity entity = sdeAccountResponse.getEntity();
            message = EntityUtils.toString(entity);
            if(statusCode == 200) {
                logger.info(message);
                accounts.add(message);
            }

        }

    } catch (IOException ie) {
        logger.error("Error getting account detail response from SDEService", ie.getMessage());


    } finally {
         if (sdeAccountResponse != null)
             try {
                 sdeAccountResponse.close();
             } catch (IOException ie) {

             }
     }
//     JsonArray jsonArray = ResponseHelper.getResponse(accounts);
     ResponseBuilder botResponse = ResponseFactory.createResponseBuilder(botName);
//
//        String resp = botResponse.buildResponse(jsonArray);
        String resp = botResponse.buildResponse(accounts);
        logger.info(resp);
        return resp;

    }


    private HttpGet createHttpGet(String uri) {
         HttpGet httpGet = new HttpGet(uri);
        httpGet.addHeader(COMPANY_ID, gatewayCompanyId);
        httpGet.addHeader(REQUEST_ID, generateUUID());
        httpGet.addHeader(AUTHORIZATION, "Bearer " + authorization);
        httpGet.addHeader(END_USER_AUTHORIZATION, endUserAuthorization);
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        return httpGet;
    }
    private String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
