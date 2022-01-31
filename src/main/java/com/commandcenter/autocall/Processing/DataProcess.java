package com.commandcenter.autocall.Processing;

import com.commandcenter.autocall.cclogger.CCLogger;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DataProcess {
    private final String callString;
    //Flag to close the connection after calls
    private int flag = 0;

    public DataProcess(String callString) {
        this.callString = callString;
    }

    private String getCallString() {
        return callString;
    }

    /**
     * Read the line
     */
    public void callStringParser() {
        ArrayList<String> callURLs = new ArrayList<>();

        String[] urlArr = getCallString().split("\\|");
        for (int i = 0; i < urlArr.length; i++) {
            if (urlArr[i].contains("Call")) {
                String callURL = urlArr[i + 1];
                //handle mimecast prefix
//                if (callURL.contains("mimecast")) {
//                    String[] mimeCastURL = urlArr[i + 1].split("<|>");
//                    callURL = mimeCastURL[2].replace("amp;", "").replace(" ", "");
//                    CCLogger.LogInfo(new Object() {
//                    }.getClass().getEnclosingClass().getSimpleName(), "After Mime Cast URL [  " + callURL + " ]");
//                }
                callURLs.add(callURL);
                CCLogger.LogInfo(new Object() {
                }.getClass().getEnclosingClass().getSimpleName(), "Call URL " + urlArr[i + 1]);
            }
        }


        automatedCallTrigger(callURLs);
    }

    public void automatedCallTrigger(ArrayList<String> callURLs) {
        final DefaultAsyncHttpClient client = new DefaultAsyncHttpClient();

        try {
            Response response = client.prepareGet("http://10.32.8.250:8088/asterisk/rawman?action=login&username=ccavc&secret=abc1234").execute().get();
//            Response response = client.prepareGet(authURL).execute().get();
            CCLogger.LogInfo(new Object() {
            }.getClass().getEnclosingClass().getSimpleName(), "Response " + response.getStatusCode());

            if (response.getStatusCode() == 200) {
                for (int i = 0; i < callURLs.size(); i++) {

                    CCLogger.LogInfo(new Object() {
                    }.getClass().getEnclosingClass().getSimpleName(), "I am here before calling URL [ " + callURLs.get(i) + " ]");

                    flag = i;
                    ListenableFuture<Response> f = client.prepareGet(callURLs.get(i)).execute(new AsyncCompletionHandler<Response>() {

                        public Response onCompleted(Response response) {
                            if (flag == callURLs.size() - 1)
                                client.close();
                            return response;
                        }


                    });
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            CCLogger.LogException(new Object() {
            }.getClass().getEnclosingClass().getSimpleName(), "Exception Cause [ " + e.getCause() + " ]", e);
        }

    }

}

