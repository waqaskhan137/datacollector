package com.commandcenter.datacollector.config;

import com.commandcenter.datacollector.logger.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

public class Configs {
    ApplicationConfigurations applicationConfigurations;

    static ClientList clientList;

    public Configs() {
        applicationConfigurations = new ApplicationConfigurations();
    }

//    public static void main(String[] args) {
//        Configs configs = new Configs();
//        configs.getJSON();
//    }

    public void getJSON() {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("config.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // getting address
            Map test = jsonObject;

            Iterator<Map.Entry> totalClient = test.entrySet().iterator();
            while (totalClient.hasNext()) {
                Map.Entry clients = totalClient.next();
                // getting client details
                Map address = ((Map) jsonObject.get(clients.getKey()));
                // iterating address Map
                Iterator<Map.Entry> client = address.entrySet().iterator();
                while (client.hasNext()) {
                    Map.Entry pair = client.next();
                    //set the client details
                    String key = (String) pair.getKey();
                    switch (key) {
                        case "email":
                            applicationConfigurations.setEmail(pair.getValue().toString());
                            break;
                        case "password":
                            applicationConfigurations.setPassword(pair.getValue().toString());
                            break;
                        case "recipients":
                            applicationConfigurations.setRecipients(pair.getValue().toString());
                            break;
                        case "folderName":
                            applicationConfigurations.setFolderName(pair.getValue().toString());
                            break;
                        case "interval":
                            applicationConfigurations.setInterval(Integer.parseInt(pair.getValue().toString()));
                            break;
                        default:
                            Logger.LogInfo(new Object() {
                            }.getClass().getEnclosingClass().getSimpleName(), "Unrecognizable key [ " + key + " ]");
                    }
                }
            }

        } catch (Exception e) {
            Logger.LogException(new Object() {
            }.getClass().getEnclosingClass().getSimpleName(), "Exception [ " + e.getCause() + " ]", e);
        }
    }
}
