package com.commandcenter.autocall.Processing;

import com.commandcenter.autocall.MSExchangeModule.MSExchangeEmailService;
import com.commandcenter.autocall.cclogger.CCLogger;
import com.commandcenter.autocall.configs.ApplicationConfigurations;
import com.commandcenter.autocall.configs.Configs;

public class AutoCallHandler implements Runnable {

    private void autoCallProcess() {
        MSExchangeEmailService msExchangeEmailService = new MSExchangeEmailService();
        msExchangeEmailService.findFolderId();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {

            //Load configurations
            CCLogger.LogInfo(new Object() {
            }.getClass().getEnclosingClass().getSimpleName(), "Loading All Configurations. ");

            Configs configs = new Configs();
            configs.getJSON();

            autoCallProcess();

            int intervalInMinutes = ApplicationConfigurations.getInterval();
            try {
                Thread.sleep(intervalInMinutes * 60000);
            } catch (Exception e) {
                CCLogger.LogException(new Object() {
                }.getClass().getEnclosingClass().getSimpleName(), "Exception [ " + e.getCause() + " ]", e);
            }
        }
    }
}
