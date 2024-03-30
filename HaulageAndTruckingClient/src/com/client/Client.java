package com.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Client {
    
    private static Logger logger = LogManager.getLogger(Client.class);

    private  Socket server;
    private  ObjectOutputStream outputStream;
    private  ObjectInputStream inputStream;
    private  boolean response;



    public Client() {

        configureConnection();
        configureStreams();
    }


    private void configureConnection() {

        try {
            server = new Socket("localhost", 8040);
            logger.info("Socket is configured at port 8040");
        } catch (IOException e) {
           logger.error("Error: " + e.getMessage());
        }
    }


    private void configureStreams() {

        try {

            outputStream = new ObjectOutputStream(server.getOutputStream());
            inputStream = new ObjectInputStream(server.getInputStream());
            logger.info("Input and Output streams configured..");

        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
        }

    }
    
    

    public void closeConnection() {

        logger.info("Closing Streams..");

        try {

            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if(server != null){
                server.close();
            }

        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
    
    
    public void sendAction(String action) {

        logger.info("Sending action to server: " + action);

        try {
            outputStream.writeObject(action);
        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
        }
    }



    public boolean validate(String username, String password) {

        try {

            logger.info("Validating user login: Username: "+ username);
            outputStream.writeObject(username);
            outputStream.writeObject(password);

        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
        }

        try {
            
            response = (boolean) inputStream.readObject();
             logger.info("Server response to user login is = " + response);
        } catch (ClassNotFoundException | IOException e) {
            logger.error("Error: " + e.getMessage());
            return false;
        }

        return response;

    }
    
    public void sendObject(Object obj) {

        try {
            
            logger.info("Sending object to Server");
            outputStream.writeObject(obj);
        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
    

    public Object getObject() {

        Object obj = null;
        try {
             logger.info("Retrieving object from server");
            obj = inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error: " + e.getMessage());
        }

        return obj;

    }
    



    public boolean staffExists(String username) {

        this.sendAction("Does Staff Exist");
        logger.info("Sending action [Does Staff Exist] to server");
        try {
            outputStream.writeObject(username);
        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
        }

        try {
            response = (boolean) inputStream.readObject();
            logger.info("Server response for [Does Staff Exist] = " + response);
        } catch (ClassNotFoundException | IOException e) {
            logger.error("Error: " + e.getMessage());
            return false;
        }
        return response;
    }
    
    

}
