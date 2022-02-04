package com.commandcenter.datacollector.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ClientList {
    static Logger log = LogManager.getLogger(ClientList.class.getName());

    private static ArrayList<AfinitiClient> afinitiClients = new ArrayList<AfinitiClient>();

    public static AfinitiClient getClientByName(String clientName) {
        try {

            for (int i = 0; i < afinitiClients.size(); i++) {
                if (afinitiClients.get(i).getClientName().equals(clientName)) {
                    return afinitiClients.get(i);
                }
            }
        } catch (Exception e) {

            log.error("SensorList", e.getMessage());
        }
        log.warn("ClientList", "AfinitiClient with Sensor Title[ " + clientName + " not found in client list");
        return null;
    }

    public static void add(AfinitiClient afinitiClient) {
        if (afinitiClient != null) {
            afinitiClients.add(afinitiClient);
            log.info("ClientList", "AfinitiClient [ " + afinitiClient.getClientName() + " ] has been added to the afinitiClient list. ");
        }
    }

    public static void remove(AfinitiClient afinitiClient) {
        if (afinitiClient != null) {
            afinitiClients.remove(afinitiClient);
            log.info("ClientList", "AfinitiClient [ " + afinitiClient.getClientName() + " ] has been removed.");
        }
    }

    public static ArrayList<AfinitiClient> getAfinitiClients() {
        return afinitiClients;
    }

}
