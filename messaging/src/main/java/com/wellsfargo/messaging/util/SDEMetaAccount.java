package com.wellsfargo.messaging.util;

import java.util.List;

/**
 * Created by u554732 on 12/22/16.
 */
public class SDEMetaAccount {

    private String account_id;
    private String account_type;
    private String display_name;
    private String status;
    private String description;
    private List<FiidAttributes> fiid_attributes;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FiidAttributes> getFiid_attributes() {
        return fiid_attributes;
    }

    public void setFiid_attributes(List<FiidAttributes> fiid_attributes) {
        this.fiid_attributes = fiid_attributes;
    }

    public class FiidAttributes {
        private String name;
        private String value;
    }
}
