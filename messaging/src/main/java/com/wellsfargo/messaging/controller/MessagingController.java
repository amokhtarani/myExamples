package com.wellsfargo.messaging.controller;

/**
 * Created by u554732 on 12/16/16.
 */

import com.wellsfargo.messaging.SDEService;
import com.wellsfargo.messaging.request.SDERequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
//import net.sf.json.JSONObject;
import org.springframework.boot.json.JsonParser;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MessagingController {

    @Resource
    private SDEService sdeService;

    @RequestMapping(value="accounts", method= RequestMethod.POST, headers="Accept=application/json")
    public @ResponseBody
//    HttpResponse getAccounts(@RequestBody String body, @RequestHeader HttpHeaders headers) {
        String getAccounts(@RequestBody String body, @RequestHeader HttpHeaders headers) {
        String companyId = headers.getFirst("gateway-company-id");
        String requestId = headers.getFirst("request-id");
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = parser.parseMap(body);

        String userAuth = (String)map.get("user_id");

        SDERequest request = new SDERequest();
        request.setRequestId(requestId);
        request.setGatewayCompanyId(companyId);
        request.setUserAuth(userAuth);

//        HttpResponse response = sdeService.getAccountDetails(request);
        String response = sdeService.getAccountDetails(request);



        return response;

    }

    @RequestMapping(value="transactions", method= RequestMethod.POST, headers="Accept=application/json")
    public @ResponseBody String getTransactions(@RequestBody String body, @RequestHeader HttpHeaders headers) {
        String companyId = headers.getFirst("gateway-company-id");
        String requestId = headers.getFirst("request-id");
        String secret = headers.getFirst("secret");
        String token = headers.getFirst("token");

        return null;
    }
    @RequestMapping(value="hello", method= RequestMethod.GET)
    public @ResponseBody String greetings() {
        return "Greetings. \n";
    }


}
