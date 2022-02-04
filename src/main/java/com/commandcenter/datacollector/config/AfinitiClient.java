package com.commandcenter.datacollector.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class AfinitiClient {
    static Logger log = LogManager.getLogger(AfinitiClient.class.getName());
    String clientName;
    String folderName;
    private String ivrName;
    ArrayList<String> phoneArrayList;
    ArrayList<String> callStringArrayList;

    public ArrayList<String> getCallStringArrayList() {
        return callStringArrayList;
    }

    public void setCallStringArrayList(ArrayList<String> callStringArrayList) {
        this.callStringArrayList = callStringArrayList;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }


    public ArrayList<String> getPhoneArrayList() {
        return phoneArrayList;
    }

    public AfinitiClient() {
        phoneArrayList = new ArrayList<>();
        callStringArrayList = new ArrayList<>();
    }

    public void setPhoneArrayList(ArrayList<String> phoneArrayList) {
        this.phoneArrayList = phoneArrayList;
    }

    public void addCallString(String callString) {

        if (phoneArrayList == null) {
            phoneArrayList = new ArrayList<>();
        }

        phoneArrayList.add(callString);
        log.info("Added the file [ " + callString + " ]");

    }

    public void removeCallString(String callString) {
        if (phoneArrayList != null) {
            phoneArrayList.remove(callString);
            log.info( "Removed the file [ " + callString + " ]");
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setIVRName(String ivrName) {
        this.ivrName = ivrName;
    }

    public String getIVRName() {
        return ivrName;
    }
}
