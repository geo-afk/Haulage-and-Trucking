package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.JDBCPersistence;
import database.hibernate.GetEntityFromDB;
import database.hibernate.SaveEntityToDB;
import database.hibernate.UpdateEntityInDB;
import database.jdbc.GetDriverMap;
import database.jdbc.GetTripReport;
import model.Address;
import model.Admin;
import model.Customer;
import model.CustomerBalance;
import model.Route;
import model.Staff;
import model.Trip;

public class Server {

    private static final Logger logger = LogManager.getLogger(Server.class);

    private ServerSocket serverSocket;
    private Socket connectedSocket;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public Server() {

        try {

            serverSocket = new ServerSocket(8040);
            logger.info("Server Started");

            while (!serverSocket.isClosed()) {

                connectedSocket = serverSocket.accept();
                logger.info("Client connected");

                ClientHandler client = new ClientHandler(connectedSocket);
                executor.execute(client);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientHandler implements Runnable {

        private Socket socket;
        private boolean response;
        private ObjectStreamHandler handler;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        private void initializeDataStream() throws IOException {
            handler = new ObjectStreamHandler(socket);
            logger.info("Data streams initialized");
        }

        @Override
        public void run() {

            try {
                initializeDataStream();
                String request;
                while ((request = (String) handler.readObject()) != null) {
                    logger.info("Request received: {}", request);
                    handleRequest(request);
                }
            } catch (Exception e) {
                logger.error("Client handler exception: {}", e.getMessage());
            } finally {
                closeResources();
            }
        }

        private void handleRequest(String request) {
            if (request != null) {
                switch (request) {
                    case "Add Trip":
                        addTrip();
                        break;
                    case "Add Customer":
                        addCustomer();
                        break;
                    case "Add Customer Balance":
                        addCustomerBalance();
                        break;
                    case "Add Route":
                        addRoute();
                        break;
                    case "Add Admin":
                        addAdministrator();
                        break;
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
                    case "Get Route":
                        getRoutes();
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
                        logger.info("Unknown request: {}", request);
                }
            }
        }

        private void closeResources() {
            try {
                if (handler != null) {
                    handler.close();
                }
                if (socket != null)
                    socket.close();
                logger.info("Closed client connection");
            } catch (IOException e) {
                logger.info("Error closing resources: {}", e.getMessage());
            }
        }

        public void validateLogin() {

            String username = null;
            String password = null;
            try {
                username = (String) handler.readObject();
                password = (String) handler.readObject();

            } catch (IOException e) {
                logger.error("Error closing streams {}", e.getMessage(), e);
            }

            response = JDBCPersistence.isValidLogin(username, password);

            handler.writeObject(response);

        }

        public void doesStaffExists() {

            String username = "";

            try {

                username = (String) handler.readObject();

                logger.info("does staff exists: {}", username);
            } catch (IOException e) {
                logger.error("Does Staff Exists, Error: {}", e.getMessage());
            }

            response = JDBCPersistence.isStaffIdPresent(username);

            handler.writeObject(response);
        }

        public void getTripReport() {

            try {

                String driverName = (String) handler.readObject();
                Timestamp startDate = (Timestamp) handler.readObject();
                Timestamp endDate = (Timestamp) handler.readObject();

                logger.info("Getting trip data for: {}", driverName);
                handler.writeObject(GetTripReport.generateTripReport(driverName, startDate, endDate));

            } catch (IOException e) {
                logger.error("Get Trip Report, Error: {}", e.getMessage());
            }
        }

        public void getCustomerBalance() {

            try {
                String customerName = (String) handler.readObject();

                @SuppressWarnings("unchecked")
                List<CustomerBalance> customerBalance = (List<CustomerBalance>) GetEntityFromDB
                        .getEntitiesByCriteria(CustomerBalance.class);

                handler.writeObject(
                        customerBalance
                                .stream()
                                .filter(e -> e.getCompanyName().equalsIgnoreCase(customerName))
                                .collect(Collectors.toList()));

            } catch (IOException e) {
                logger.error("Get Customer Balance, Error: {}", e.getMessage());
            }
        }

        public void getRoutes() {

            handler.writeObject(GetEntityFromDB.getEntitiesByCriteria(Route.class));
        }

        public void getCustomer() {

            handler.writeObject(GetEntityFromDB.getEntitiesByCriteria(Customer.class));
        }

        public void getStaff() {

            handler.writeObject(GetEntityFromDB.getEntitiesByCriteria(Staff.class));
        }

        public void retrieveAddress() {
            handler.writeObject(JDBCPersistence.getAddress());

        }

        public void getDriverMap() {

            handler.writeObject(GetDriverMap.DriverAndValueMap());

        }

        public void addTrip() {

            try {

                Trip trip = (Trip) handler.readObject();

                SaveEntityToDB.saveObject(trip.getRoute().getSourceAddress(), Address.class);
                SaveEntityToDB.saveObject(trip.getRoute().getDestinationAddress(), Address.class);

                SaveEntityToDB.saveObject(trip.getRoute(), Route.class);
                SaveEntityToDB.saveObject(trip, Trip.class);

            } catch (IOException e) {
                logger.error("Add Trip, Error: {}", e.getMessage());
            }

        }

        public void addStaff() {

            try {

                Staff staff = (Staff) handler.readObject();

                SaveEntityToDB.saveObject(staff.getAddress(), Address.class);
                SaveEntityToDB.saveObject(staff, Staff.class);

            } catch (IOException e) {
                logger.error("Add Staff, Error: {}", e.getMessage(), e);
            } finally {
                SaveEntityToDB.closeSession();
            }

        }

        public void addCustomer() {

            try {
                Customer customer = (Customer) handler.readObject();

                SaveEntityToDB.saveObject(customer.getAddress(), Address.class);
                SaveEntityToDB.saveObject(customer, Customer.class);

            } catch (IOException e) {
                logger.error("Add Customer,Error: {}", e.getMessage());
            } finally {
                SaveEntityToDB.closeSession();
            }

        }

        public void addCustomerBalance() {

            try {
                CustomerBalance customerBalance = (CustomerBalance) handler.readObject();

                SaveEntityToDB.saveObject(customerBalance.getRoute(), Route.class);
                SaveEntityToDB.saveObject(customerBalance, CustomerBalance.class);

            } catch (IOException e) {
                logger.error("Add Customer,Error: {}", e.getMessage());
            } finally {
                SaveEntityToDB.closeSession();
            }

        }

        public void addRoute() {

            try {
                Route route = (Route) handler.readObject();

                SaveEntityToDB.saveObject(route.getSourceAddress(), Address.class);
                SaveEntityToDB.saveObject(route.getDestinationAddress(), Address.class);
                SaveEntityToDB.saveObject(route, Route.class);

            } catch (IOException e) {
                logger.error("Add Route, Error: {}", e.getMessage());
            }
        }

        public void resetStaffPassword() {

            try {

                Admin admin = (Admin) handler.readObject();

                UpdateEntityInDB.updateEntity(admin, Admin.class);

            } catch (IOException e) {
                logger.error("Reset Staff Password, Error: {}", e.getMessage());
                UpdateEntityInDB.closeSession();
            }

        }

        public void addAdministrator() {

            try {
                Staff staff = (Staff) handler.readObject();
                Admin admin = (Admin) handler.readObject();

                Address address = new Address(staff.getAddress());
                SaveEntityToDB.saveObject(address, Address.class);

                SaveEntityToDB.saveObject(staff, Staff.class);
                admin.setId(staff.getId());
                SaveEntityToDB.saveObject(admin, Admin.class);

            } catch (IOException e) {
                logger.error("Error: {}", e.getMessage());
            } finally {
                SaveEntityToDB.closeSession();
            }

        }
    }

    public static void main(String[] args) {
        new Server();
    }

}