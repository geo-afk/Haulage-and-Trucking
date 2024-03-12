package trucking;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Staff {
	
	 JFrame frame = new JFrame("Report");
     frame.setSize(400, 400);
     frame.setLayout(null);

     JLabel startDateLabel = new JLabel("Start Date(yyyy-MM-dd):");
     startDateLabel.setBounds(20, 20, 150, 20);
     JTextField startDateTextField = new JTextField();
     startDateTextField.setBounds(180, 20, 150, 20);

     JLabel endDateLabel = new JLabel("End Date(yyyy-MM-dd):");
     endDateLabel.setBounds(20, 60, 150, 20);
     JTextField endDateTextField = new JTextField();
     endDateTextField.setBounds(180, 60, 150, 20);

     JLabel driverLabel = new JLabel("Driver Name:");
     driverLabel.setBounds(20, 100, 150, 20);
     JTextField driverTextField = new JTextField();
     driverTextField.setBounds(180, 100, 150, 20);

     JButton reportButton = new JButton("Generate Report");
     reportButton.setBounds(130, 150, 150, 30);

     // Add components to the frame
     frame.add(startDateLabel);
     frame.add(startDateTextField);
     frame.add(endDateLabel);
     frame.add(endDateTextField);
     frame.add(driverLabel);
     frame.add(driverTextField);
     
    
	reportButton.addActionListiner( new ActionListiner() 
	{
		public void actionPerformed(ActionEvent e)
		{
			String dateString = "2024-03-11"; // Sample date string
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
	        try {
	          //getting date entered from text field
	        	Date startDate = dateFormat.parse(startDateTextField.getText());
	        	Date endDate = dateFormat.parse(endDateTextField.getText());
	        	String  driverID = driverTextField.getText();
			
	        	//putting parse data into function
	        	generateTripReport(startDate, endDate, driverID);
	        }catch(ParseException e) 
	        {
	        	System.err.println("Error parsing date: " + e.getMessage());
	        }
			
		}
	});
	
	frame.setVisible(true);
	frame.add(reportButton);
	
	
	//Method to generate Trip Report
	public List<Trip> generateTripReport(Date startDate, Date endDate, String driverID) {
       
		List<Trip> trips = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {//preparing sql statement 
            String sql = "SELECT * FROM trips WHERE driverID = ? AND start_date BETWEEN ? AND ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, driverID);
            preparedStatement.setDate(2, startDate);
            preparedStatement.setDate(3, endDate);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Retrieve trip data from the result set and create Trip objects
                int invoiceNumber = resultSet.getInt("invoiceNumber");
                String driver_ID = resultSet.getString("driverID");
                
                //adding data to Trip constructor 
                Trip trip = new Trip(invoiceNumber, driver_ID);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return trips;
    } 
    
}
   