package com.commandcenter.autocall.configs;

import com.commandcenter.autocall.cclogger.CCLogger;

import java.util.ArrayList;

public class ClientList {

    private static ArrayList<AfinitiClient> afinitiClients = new ArrayList<AfinitiClient>();

    public static AfinitiClient getClientByName(String clientName) {
        try {

            for (int i = 0; i < afinitiClients.size(); i++) {
                if (afinitiClients.get(i).getClientName().equals(clientName)) {
                    return afinitiClients.get(i);
                }
            }
        } catch (Exception e) {

            CCLogger.LogError("SensorList", e.getMessage());
        }
        CCLogger.LogWarn("ClientList", "AfinitiClient with Sensor Title[ " + clientName + " not found in client list");
        return null;
    }

    public static void add(AfinitiClient afinitiClient) {
        if (afinitiClient != null) {
            afinitiClients.add(afinitiClient);
            CCLogger.LogInfo("ClientList", "AfinitiClient [ " + afinitiClient.getClientName() + " ] has been added to the afinitiClient list. ");
        }
    }

    public static void remove(AfinitiClient afinitiClient) {
        if (afinitiClient != null) {
            afinitiClients.remove(afinitiClient);
            CCLogger.LogInfo("ClientList", "AfinitiClient [ " + afinitiClient.getClientName() + " ] has been removed.");
        }
    }

    public static ArrayList<AfinitiClient> getAfinitiClients() {
        return afinitiClients;
    }

}
