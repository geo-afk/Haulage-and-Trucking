package com.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import model.Trip;

public class Client{
    
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
            server = new Socket( "localhost", 8040);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void configureStreams() {

        try {

            outputStream = new ObjectOutputStream(server.getOutputStream());
            inputStream = new ObjectInputStream(server.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    

    public void closeConnection() {

        try {

            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void sendAction(String action) {

        try {
            outputStream.writeObject(action);
        } catch (IOException e) {
       
            e.printStackTrace();
        }
    }



    public boolean validate(Long staffId, String password) {

        try {

            outputStream.writeObject(staffId);
            outputStream.writeObject(password);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            response = (boolean) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return false;
        }

        return response;

    }
    
    public void sendObject(Object obj) {

        try {
            outputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    // public void receiveResponse() {

    //     try {

    //         response = (boolean) inputStream.readObject();

    //         if (request.equalsIgnoreCase("Add Staff")) {

    //             if (response) {
    //                 JOptionPane.showMessageDialog(null, "Staff added successfully", "Success",
    //                         JOptionPane.INFORMATION_MESSAGE);

    //             } else {
    //                 JOptionPane.showMessageDialog(null, "Staff not added", "",
    //                         JOptionPane.ERROR_MESSAGE);
    //             }
    //         }

    //         if (request.equalsIgnoreCase("Reset Password")) {

    //             if (response) {
    //                 JOptionPane.showMessageDialog(null, "Password reset successfully", "Success",
    //                         JOptionPane.INFORMATION_MESSAGE);

    //             } else {
    //                 JOptionPane.showMessageDialog(null, "Password not reset", "Error",
    //                         JOptionPane.ERROR_MESSAGE);
    //             }

    //         }

    //         if (request.equalsIgnoreCase("Get Invoice") && (!response)) {

    //             JOptionPane.showMessageDialog(null, "No Invoice Found", "Error",
    //                     JOptionPane.ERROR_MESSAGE);

    //         }

    //     } catch (ClassCastException | ClassNotFoundException | IOException ex) {
    //         ex.printStackTrace();
    //     }
    // }

    

    public List<Trip> getTrips() {
        try {
            Object obj = inputStream.readObject();
            return castToTripList(obj);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private List<Trip> castToTripList(Object obj) {
        if (obj instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<Trip> trips = (List<Trip>) obj;
            return trips;
        }
        return List.of();
    }


    
    public void resetPassword(Long staffId, String newPassword) 
    {
        try {
            outputStream.writeObject(staffId);
            outputStream.writeObject(newPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean staffExists(Long staffId) {

        this.sendAction("Does Staff Exist");
        try {
            outputStream.writeObject(staffId);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            response = (boolean) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return response;
    }
}
