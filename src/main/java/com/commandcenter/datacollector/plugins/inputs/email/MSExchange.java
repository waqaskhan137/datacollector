package com.commandcenter.datacollector.plugins.inputs.email;

import com.commandcenter.datacollector.config.Configurations;
import com.commandcenter.datacollector.plugins.inputs.Input;
import com.commandcenter.datacollector.plugins.inputs.email.message.Message;
import com.commandcenter.datacollector.plugins.inputs.email.message.MessageList;
import lombok.Getter;
import lombok.Setter;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.FolderTraversal;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.FolderSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.Mailbox;
import microsoft.exchange.webservices.data.search.FindFoldersResults;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.FolderView;
import microsoft.exchange.webservices.data.search.ItemView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Date;

/**
 * @author Rana M Waqas
 */
@Component
public class MSExchange implements Input {
    private static final Logger LOGGER = LoggerFactory.getLogger(MSExchange.class);


    @Autowired
    Configurations configurations;

    @Setter
    @Getter
    ExchangeService service;
    @Setter
    @Getter
    String mailbox;
    @Setter
    @Getter
    String password;
    @Setter
    @Getter
    MessageList messageList;

    @Override
    public void start() {
        initialize();

    }

    @Override
    public MessageList fetch() {
        try {
            Folder folder = searchFolder();
            setMessageList(getSMTPMessages(folder));
        } catch (Exception e) {
            LOGGER.error("Exception ", e);
        }
        return getMessageList();
    }

    @Override
    public void stop() {
        LOGGER.info("Closing the Exchange Service.");
        getService().close();
    }

    /**
     * Initialize the Exchange Service
     */
    public void initialize() {
        setMailbox(configurations.email);
        setPassword(configurations.emailPassword);
        LOGGER.info("Checking the email and password " + configurations.email + " " + configurations.emailPassword);
        service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(getMailbox(), getPassword());
        service.setCredentials(credentials);
        service.setTraceEnabled(false);

        try {
            service.setUrl(new URI("https://mail.ps-afiniti.com/ews/Exchange.asmx"));
            setService(service);

        } catch (Exception e) {
            LOGGER.error("Exception ", e);
        }


    }

    /**
     * Search for the folder in the Exchange Server
     *
     * @return Folder
     */
    public Folder searchFolder() {

        try {
            Mailbox userMailbox = new Mailbox(mailbox);
            FolderId rootFolder = new FolderId(WellKnownFolderName.Inbox, userMailbox);

            FolderView view = new FolderView(Integer.MAX_VALUE);
            view.setPropertySet(new PropertySet(BasePropertySet.IdOnly, FolderSchema.DisplayName));
            view.setTraversal(FolderTraversal.Deep);
            FindFoldersResults findFolderResults = this.service.findFolders(rootFolder, view);
            //find specific folder
            LOGGER.info("Folder to search for: " + configurations.folderName);
            for (Folder folder : findFolderResults) {
                if (folder.getDisplayName().contains(configurations.folderName)) {
                    LOGGER.info("Folder found " + folder.getDisplayName());
                    return folder;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception ", e);
        }
        return null;
    }

    /**
     * Gets messages from the Inbox folder.
     *
     * @param folder Folder to get the messages from.
     * @return MessageList
     * @throws Exception If the folder doesn't exist throws an exception
     */
    private MessageList getSMTPMessages(Folder folder) throws Exception {
        MessageList messageList = new MessageList();
        folder.load();
        if (folder.getTotalCount() > 0) {
            ItemView view2 = new ItemView(folder.getTotalCount());
            view2.getOrderBy().add(ItemSchema.DateTimeReceived, SortDirection.Descending);
            view2.setPropertySet(new PropertySet(BasePropertySet.IdOnly, ItemSchema.Subject, ItemSchema.DateTimeReceived));

            FindItemsResults<Item> findResults = service.findItems(folder.getId(), view2);
            service.loadPropertiesForItems(findResults, PropertySet.FirstClassProperties);

            for (Item item : findResults.getItems()) {
                String body = item.getBody().toString().trim();
                String subject = item.getSubject().trim();
                Date date = item.getDateTimeReceived();


                Message message = new Message(body, subject, date);
                messageList.add(message);

                item.delete(DeleteMode.HardDelete);
            }
        } else {
            LOGGER.error("There is no new email in folder");
        }
        return messageList;
    }
}
