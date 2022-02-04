package com.commandcenter.datacollector.utils;

import com.commandcenter.datacollector.logger.email.EmailNotification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnect {
    static Logger log = LogManager.getLogger(MysqlConnect.class.getName());

    public String name;
    // init database constants
    private String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DATABASE_URL;
    private String USERNAME = "MonitorAppProd";
    private String PASSWORD = "Monitor@99rodmYsqL";
    private String MAX_POOL = "250";
    private String archivalDB;


    public String getArchivalDB() {
        return archivalDB;
    }

    public void setArchivalDB(String archivalDB) {
        this.archivalDB = archivalDB;
    }

    //    private String DB_IP = "10.32.5.51:3307";  //clearlink
    //    private String DB_IP = "10.32.244.37:3307"; //DGS
    //    private String DB_IP = "10.32.244.72:3307"; //SiriusXM

    // init connection object
    private Connection connection;
    // init properties object
    private Properties properties;

    public MysqlConnect(String archivalDB) {
        setArchivalDB(archivalDB);
    }

    public String getDATABASE_DRIVER() {
        return DATABASE_DRIVER;
    }

    public void setDATABASE_DRIVER(String DATABASE_DRIVER) {
        this.DATABASE_DRIVER = DATABASE_DRIVER;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getMAX_POOL() {
        return MAX_POOL;
    }

    public void setMAX_POOL(String MAX_POOL) {
        this.MAX_POOL = MAX_POOL;
    }

    public String getName() {
        return name;
    }

    public String getDATABASE_URL() {

        return DATABASE_URL;
    }

    public void setDATABASE_URL(String DATABASE_URL) {
        DATABASE_URL = "jdbc:mysql://" + DATABASE_URL + "/monitor4?connectTimeout=5000&socketTimeout=30000&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=";
//        DATABASE_URL = "jdbc:mysql://" + DATABASE_URL + "/monitor4?serverTimezone=UTC";
        log.info(new Object() {
        }.getClass().getEnclosingClass().getSimpleName(), "Database URL [ " + DATABASE_URL + " ]");

        this.DATABASE_URL = DATABASE_URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setProperties() {
        properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        properties.setProperty("MaxPooledStatements", MAX_POOL);
    }


    // create properties
    private Properties getProperties() {
        if (properties == null) {
            log.info("com.rmwaqas.utility.MysqlConnect", "Please set the credentials and properties first");
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                setProperties();
                setDATABASE_URL(getArchivalDB());
                Class.forName(DATABASE_DRIVER);
                log.info(new Object() {
                }.getClass().getEnclosingClass().getSimpleName(), "Database URL [ " + DATABASE_URL + " ] Database Driver [ " + DATABASE_DRIVER + " ]");
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                log.error("com.rmwaqas.utility.MysqlConnect", "Connection Exception [ " + e.getMessage(), e);
                new EmailNotification("Archival DB Connection Exception \n [ " + e.getLocalizedMessage() + " ] \n " + e);
            }
        }
        return connection;
    }


    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                log.error(new Object() {
                }.getClass().getEnclosingClass().getSimpleName(), "Exception ", e);
                new EmailNotification("Exception at disconnecting the database. \n [ " + e.getLocalizedMessage() + " ] \n " + e);
            }
        }
    }
}