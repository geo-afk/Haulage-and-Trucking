package src.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import src.constants.JDBCPersistence;
import src.database.GetCustomerBalance;
import src.database.GetCustomerFromDB;
import src.database.GetDriverMap;
import src.database.SaveEntityToDB;
import src.database.UpdateEntityInDB;
import src.database.GetRouteFromDB;
import src.database.GetStaffFromDB;
import src.database.GetTripReport;
import src.models.Address;
import src.models.Administrator;
import src.models.Customer;
import src.models.Rate;
import src.models.Route;
import src.models.Staff;
import src.models.Trip;

public class Server {

    private final static Logger logger = LogManager.getLogger(Server.class);

    private ServerSocket serverSocket;
    private Socket connectedSocket;


    public Server() {

        try { 
            
            serverSocket = new ServerSocket(8040);

           
            logger.info("Server Started");

            while (!serverSocket.isClosed()) {
                
                connectedSocket = serverSocket.accept();
                logger.info("Client connected");
                


                ClientHandler client = new ClientHandler(connectedSocket);
                Thread thread = new Thread(client);
                thread.start();
                    
            }


                

        } catch (IOException e) {
                e.printStackTrace();
            }
        }


   
    class ClientHandler implements Runnable {

        private Socket socket;
        private boolean response;
        private ObjectInputStream dataInput;
        private ObjectOutputStream dataOutput;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

    

        @Override
        public void run() {
            
            try{
                initializeDataStream();
                String request;
                while ((request = (String) dataInput.readObject()) != null) {
                    logger.info("Request received: " + request);
                    handleRequest(request);
                }
            } catch (Exception e) {
                logger.error("Client handler exception: " + e.getMessage());
            } finally {
                closeResources();
            }
    }

        private void handleRequest(String request) {
            if (request != null) {
                switch (request) {
                    case "Get Address":
                        retrieveAddress();
                        break;
                    case "Staff Login":
                        validateLogin();
                        break;
                    case "Add Staff":
                        addStaff();
                        break;
                    case "Does Staff Exist":
                        doesStaffExists();
                        break;
                    case "Reset Password":
                        resetStaffPassword();
                        break;
                    case "Add Customer":
                        addCustomer();
                        break;
                    case "Add Route":
                        addRoute();
                        break;
                    case "Add Rate":
                        addRate();
                        break;
                    case "Add Admin":
                        addAdministrator();
                        break;
                    case "Get Staff Ids":
                        getStaffIds();
                        break;
                    case "Get Route":
                        getRoutes();
                        break;
                    case "Add Trip":
                        addTrip();
                        break;
                    case "Get Customer":
                        getCustomer();
                        break;
                    case "Get Staff":
                        getStaff();
                        break;
                    case "Get Driver Map":
                        getDriverMap();
                        break;
                    case "Trip Report":
                        getTripReport();
                        break;
                    case "check Balance":
                        getCustomerBalance();
                        break;
                    default:
                        logger.info("Unknown request: " + request);
                }
            }
        }


        private void initializeDataStream() throws IOException {
            dataOutput = new ObjectOutputStream(socket.getOutputStream());
            dataInput = new ObjectInputStream(socket.getInputStream());
            logger.info("Data streams initialized");
        }

        private void closeResources() {
            try {
                if (dataInput != null)
                    dataInput.close();
                if (dataOutput != null)
                    dataOutput.close();
                if (socket != null)
                    socket.close();
                logger.info("Closed client connection");
            } catch (IOException e) {
                logger.info("Error closing resources: " + e.getMessage());
            }
        }


        public void validateLogin() {

            String username = null;
            String password = null;
            try {
                username = (String) dataInput.readObject();
                password = (String) dataInput.readObject();

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

            response = JDBCPersistence.isValidLogin(username, password);

            try {
                dataOutput.writeObject(response);
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
            }

        }

        public void doesStaffExists() {

            String username = "";

            try {

                username = (String) dataInput.readObject();

                logger.info("does staff exists: " + username);
            } catch (ClassNotFoundException | IOException e) {
                logger.error("Error: " + e.getMessage());
            }

            response = JDBCPersistence.isStaffIdPresent(username);

            try {
                dataOutput.writeObject(response);
            } catch (IOException e) {
                logger.error("Error: "+ e.getMessage());
            }

        }

        public void getStaffIds() {

            try {
                dataOutput.writeObject(JDBCPersistence.fetchStaffIdsFromDatabase());
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
            }
        }

        public void getTripReport() {

            try {
                String driverName = (String) dataInput.readObject();
                Timestamp startDate = (Timestamp) dataInput.readObject();
                Timestamp endDate = (Timestamp) dataInput.readObject();
                
                logger.info("Getting trip data for: "+driverName);
                dataOutput.writeObject(GetTripReport.generateTripReport(driverName, startDate, endDate));
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Error: " + e.getMessage());
            }
        }
        
        public void getCustomerBalance() {

            try {
                String customerName = (String) dataInput.readObject();
                
                dataOutput.writeObject(GetCustomerBalance.getCustomerBalance(customerName));
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Error: " + e.getMessage());
            } 
        }

        public void getRoutes() {

            try {
                dataOutput.writeObject(GetRouteFromDB.getAllRoutes());
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
            }
        }
        public void getCustomer() {

            try {
                dataOutput.writeObject(GetCustomerFromDB.getAllCustomers());
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
            }
        }
        public void getStaff() {

            try {
                dataOutput.writeObject(GetStaffFromDB.getAllStaff());
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
            }
        }

        


        public void retrieveAddress() {

            try {
                dataOutput.writeObject(JDBCPersistence.getAddress());

            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
            }

        }
        public void getDriverMap() {

            try {
                dataOutput.writeObject(GetDriverMap.DriverAndValueMap());

            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
            }

        }
        

        public void addTrip() {

            try {

               Trip tripToSave = (Trip) dataInput.readObject();
               

                SaveEntityToDB.saveObject(tripToSave, Trip.class);

              

            } catch (ClassNotFoundException | IOException e) {
                logger.error("Error: " + e.getMessage());
            } finally {
                logger.info("Closing sessions");
                SaveEntityToDB.closeSession();
            }

        }
        

        public void addStaff() {

            try {

                model.Staff staff = (model.Staff) dataInput.readObject();

                Address address = new Address();
                address.setAddress1(staff.getAddress().getAddress1());
                address.setAddress2(staff.getAddress().getAddress2());
                address.setParish(staff.getAddress().getParish());
                address.setPostOffice(staff.getAddress().getPostOffice());

                SaveEntityToDB.saveObject(address, Address.class);

                Staff staffToSave = new Staff();
                staffToSave.setFirstName(staff.getFirstName());
                staffToSave.setLastName(staff.getLastName());
                staffToSave.setDateOfBirth(staff.getDateOfBirth());
                staffToSave.setDateOfBirth(staff.getDateOfBirth());
                staffToSave.setAddress(address);
                staffToSave.setEmail(staff.getEmail());
                staffToSave.setPosition(staff.getPosition());
                staffToSave.setStatus(staff.getStatus());

                SaveEntityToDB.saveObject(staffToSave, Staff.class);

            } catch (ClassNotFoundException | IOException e) {
               logger.error("Error: {}", e.getMessage(), e);
            } finally {
                SaveEntityToDB.closeSession();
            }

        }
        
        public void addCustomer() {

            try {
                model.Customer customer = (model.Customer) dataInput.readObject();


                Address address = new Address();
                address.setAddress1(customer.getAddress().getAddress1());
                address.setAddress2(customer.getAddress().getAddress2());
                address.setParish(customer.getAddress().getParish());
                address.setPostOffice(customer.getAddress().getPostOffice());

                SaveEntityToDB.saveObject(address, Address.class);

                Customer customerToSave = new Customer();
                customerToSave.setCompanyName(customer.getCompanyName());
                customerToSave.setContactPerson(customer.getContactPerson());
                customerToSave.setAddress(address);
                customerToSave.setTelephone(customer.getTelephone());
                customerToSave.setEmail(customer.getEmail());
               

                SaveEntityToDB.saveObject(customerToSave, Customer.class);

            } catch (ClassNotFoundException | IOException e) {
                logger.error("Error: " + e.getMessage());
            } finally {
                SaveEntityToDB.closeSession();
            }

        }

        public void addRoute() {

            try {
                model.Route route = (model.Route) dataInput.readObject();

                Address sourceAddress = new Address();
                sourceAddress.setAddress1(route.getSourceAddress().getAddress1());
                sourceAddress.setAddress2(route.getSourceAddress().getAddress2());
                sourceAddress.setParish(route.getSourceAddress().getParish());
                sourceAddress.setPostOffice(route.getSourceAddress().getPostOffice());

                SaveEntityToDB.saveObject(sourceAddress, Address.class);


                Address destinationAddress = new Address();
                destinationAddress.setAddress1(route.getDestinationAddress().getAddress1());
                destinationAddress.setAddress2(route.getDestinationAddress().getAddress2());
                destinationAddress.setParish(route.getDestinationAddress().getParish());
                destinationAddress.setPostOffice(route.getDestinationAddress().getPostOffice());

                SaveEntityToDB.saveObject(destinationAddress, Address.class);

                Rate rate = new Rate();
                rate.setValue(route.getRateId().getValue());
                rate.setDescription(route.getRateId().getDescription());

                SaveEntityToDB.saveObject(rate, Rate.class);

                Route routeToSave = new Route();
                routeToSave.setSource(route.getSource());
                routeToSave.setSourceAddress(sourceAddress);
                routeToSave.setDestinationAddress(destinationAddress);
                routeToSave.setRateId(null);
                routeToSave.setRateId(rate);

                SaveEntityToDB.saveObject(routeToSave, Route.class);

            } catch (ClassNotFoundException | IOException e) {
                logger.error("Error: " + e.getMessage());
            } finally {
                SaveEntityToDB.closeSession();
            }

        }
        
           
     

        public void resetStaffPassword() {

           
            try {
                
                model.Admin admin = (model.Admin) dataInput.readObject();

                Administrator administratorToSave = new Administrator();
                administratorToSave.setUsername(admin.getUsername());
                administratorToSave.setPassword(admin.getPassword());
               
                
                UpdateEntityInDB.updateEntity(administratorToSave, Administrator.class);

            } catch (ClassNotFoundException | IOException e) {
                logger.error("Error: " + e.getMessage());
            }



        }
        

        public void addRate() {

            try {
                model.Rate rate = (model.Rate) dataInput.readObject();

                Rate rateToSave = new Rate();
                rateToSave.setValue(rate.getValue());
                rateToSave.setDescription(rate.getDescription());

                SaveEntityToDB.saveObject(rateToSave, Rate.class);

            } catch (ClassNotFoundException | IOException e) {
                logger.error("Error: " + e.getMessage());
            } finally {
                SaveEntityToDB.closeSession();
            }

        }
        
        public void addAdministrator() {

            try {
                model.Staff staff = (model.Staff) dataInput.readObject();
                model.Admin admin = (model.Admin) dataInput.readObject();

                Address address = new Address();
                address.setAddress1(staff.getAddress().getAddress1());
                address.setAddress2(staff.getAddress().getAddress2());
                address.setParish(staff.getAddress().getParish());
                address.setPostOffice(staff.getAddress().getPostOffice());

                SaveEntityToDB.saveObject(address, Address.class);

                Staff staffToSave = new Staff();
                staffToSave.setFirstName(staff.getFirstName());
                staffToSave.setLastName(staff.getLastName());
                staffToSave.setDateOfBirth(staff.getDateOfBirth());
                staffToSave.setDateOfBirth(staff.getDateOfBirth());
                staffToSave.setAddress(address);
                staffToSave.setEmail(staff.getEmail());
                staffToSave.setPosition(staff.getPosition());
                staffToSave.setStatus(staff.getStatus());

                SaveEntityToDB.saveObject(staffToSave, Staff.class);


                Administrator adminToSave = new Administrator();
                adminToSave.setId(staffToSave.getStaffId());
                adminToSave.setUsername(admin.getUsername());
                adminToSave.setPassword(admin.getPassword());

                SaveEntityToDB.saveObject(adminToSave, Administrator.class);

            } catch (ClassNotFoundException | IOException e) {
                logger.error("Error: " + e.getMessage());
            } finally {
                SaveEntityToDB.closeSession();
            }

        }
    }
    
    public static void main(String[] args) {
        new Server();
    }
    
}