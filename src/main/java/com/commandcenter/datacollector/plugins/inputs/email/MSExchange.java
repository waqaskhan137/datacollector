package com.commandcenter.datacollector.plugins.inputs.email;

import com.commandcenter.datacollector.config.ApplicationConfigurations;
import com.commandcenter.datacollector.plugins.inputs.Input;
import com.commandcenter.datacollector.utils.MysqlConnect;
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
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.Mailbox;
import microsoft.exchange.webservices.data.search.FindFoldersResults;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.FolderView;
import microsoft.exchange.webservices.data.search.ItemView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

/**
 * @author Rana M Waqas
 */
public class MSExchange implements Input {
    static Logger log = LogManager.getLogger(MysqlConnect.class.getName());

    @Setter
    @Getter
    ExchangeService service;
    @Setter
    @Getter
    String mailbox;
    @Setter
    @Getter
    String password;

    @Override
    public void start() {

    }

    @Override
    public void fetch() {

    }

    @Override
    public void stop() {

    }


    public MSExchange() {
        setMailbox(ApplicationConfigurations.getEmail());
        setPassword(ApplicationConfigurations.getEmailPassword());

        service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(getMailbox(), getPassword());
        service.setCredentials(credentials);
        service.setTraceEnabled(false);

        try {
            service.setUrl(new URI("https://mail.ps-afiniti.com/ews/Exchange.asmx"));
            setService(service);

        } catch (Exception e) {
            log.error("Exception ", e);
        }


    }

    public void findFolderId() {

        try {
            Mailbox userMailbox = new Mailbox(mailbox);
            FolderId rootFolder = new FolderId(WellKnownFolderName.Inbox, userMailbox);

            FolderView view = new FolderView(Integer.MAX_VALUE);
            view.setPropertySet(new PropertySet(BasePropertySet.IdOnly, FolderSchema.DisplayName));
            view.setTraversal(FolderTraversal.Deep);
            FindFoldersResults findFolderResults = this.service.findFolders(rootFolder, view);
            //find specific folder

            for (Folder folder : findFolderResults) {
                if (folder.getDisplayName().contains(ApplicationConfigurations.getFolderName())) {
                    folder.load();
                    getSMTPMessages(folder);
                }
            }
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }

    private void getSMTPMessages(Folder folder) throws Exception {

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
                AttachmentCollection attachment = item.getAttachments();
                attachment.save();

                item.delete(DeleteMode.HardDelete);
            }
        } else {
            log.error("There is no new email in folder");
        }

        log.info("Closing the Exchange Service.");
        getService().close();
    }
}