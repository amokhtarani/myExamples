package com.wellsfargo.messaging.util;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

/**
 * Created by u554732 on 12/13/16.
 */
public class AccountUtil {

    final  String[] accountIds = {"8734245", "8734246", "8734247", "8734248", "8734249"};
    final  String[] accountNumbers = {"8734245546", "87342460864", "12348734247", "8464734248", "873844249"};
    final  String[] accountNames = {"Personale","Corporate", "Family"};
    final  String[] accountTypes = {"CHECKING","SAVING", "Credit Card"};
    final  String[] availableBalances = {"10000.0", "21000.0", "15000.0", "35000.0", "30000.0"};
    final  String[] currentBalances = {"1005", "3504", "530", "5056", "409"};

    public JsonArray createSDEResponse() {

        JsonArrayBuilder accountBuilder = Json.createArrayBuilder();
        for(int i=0; i<3; i++) {
            JsonObject account = Json.createObjectBuilder()
                    .add("accountId", accountIds[i])
                    .add("accountNumber", accountNumbers[i])
                    .add("accountName", accountNames[i])
                    .add("accountType", accountTypes[i])
                    .add("availableBalance", availableBalances[i])
                    .add("currentBalance", currentBalances[i])
                    .add("currencyCode", "USD")
                    .build();
            accountBuilder.add(account);
        }
        JsonArray accountArray = accountBuilder.build();


        return accountArray;
    }
    public JsonArray createSDEResponseForKasisto(List<String > accounts) {

        JsonArrayBuilder accountBuilder = Json.createArrayBuilder();
        for(int i=0; i<3; i++) {
            JsonObject account = Json.createObjectBuilder()
                    .add("accountId", accountIds[i])
                    .add("accountNumber", accountNumbers[i])
                    .add("accountName", accountNames[i])
                    .add("accountType", accountTypes[i])
                    .add("availableBalance", availableBalances[i])
                    .add("currentBalance", currentBalances[i])
                    .add("currencyCode", "USD")
                    .build();
            accountBuilder.add(account);
        }
        JsonArray accountArray = accountBuilder.build();


        return accountArray;
    }
}
