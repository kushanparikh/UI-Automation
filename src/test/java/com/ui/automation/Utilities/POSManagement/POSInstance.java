package com.ui.automation.Utilities.POSManagement;

public class POSInstance {
    private final String posUrl;
    private final String wkid;
    private final String ipid;
    private final String cashierId;
    private final String password;

    public POSInstance(String posUrl, String wkid, String ipid, String cashierId, String password) {
        this.posUrl = posUrl;
        this.wkid = wkid;
        this.ipid = ipid;
        this.cashierId = cashierId;
        this.password = password;
    }

    public String getPosUrl() {
        return posUrl;
    }

    public String getWkid() {
        return wkid;
    }

    public String getIpid() {
        return ipid;
    }

    public String getCashierId() {
        return cashierId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "POSInstance{" +
                "posUrl='" + posUrl + '\'' +
                ", wkid='" + wkid + '\'' +
                ", ipid='" + ipid + '\'' +
                ", cashierId='" + cashierId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
} 