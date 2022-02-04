package com.commandcenter.datacollector.config;

import com.commandcenter.datacollector.logger.Logger;

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

            Logger.LogError("SensorList", e.getMessage());
        }
        Logger.LogWarn("ClientList", "AfinitiClient with Sensor Title[ " + clientName + " not found in client list");
        return null;
    }

    public static void add(AfinitiClient afinitiClient) {
        if (afinitiClient != null) {
            afinitiClients.add(afinitiClient);
            Logger.LogInfo("ClientList", "AfinitiClient [ " + afinitiClient.getClientName() + " ] has been added to the afinitiClient list. ");
        }
    }

    public static void remove(AfinitiClient afinitiClient) {
        if (afinitiClient != null) {
            afinitiClients.remove(afinitiClient);
            Logger.LogInfo("ClientList", "AfinitiClient [ " + afinitiClient.getClientName() + " ] has been removed.");
        }
    }

    public static ArrayList<AfinitiClient> getAfinitiClients() {
        return afinitiClients;
    }

}
