package com.wellsfargo.messaging.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * Created by u554732 on 12/20/16.
 */
public class SDERequest {
    private final static Logger logger = LoggerFactory.getLogger(SDERequest.class);

    private String gatewayCompanyId;
    private String requestId;
    private String userAuth;
    private Calendar startDate;
    private Calendar endDate;


    public String getGatewayCompanyId() {
        return gatewayCompanyId;
    }

    public void setGatewayCompanyId(String gatewayCompanyId) {
        this.gatewayCompanyId = gatewayCompanyId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
}
