package dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.customer;
import entity.lease;
import entity.vehicle;
import myexceptions.CarNotFoundException;
import myexceptions.CustomerNotFoundException;
import myexceptions.LeaseNotFoundException;
import util.DBConnection;

public class ICarLeaseRepositoryImpl implements ICarLeaseRepository {
	private Connection connection;

	 public ICarLeaseRepositoryImpl(Connection connection) {
	        this.connection = connection;
	  }

	@Override
	public void addCar(vehicle car) {
		try {
			Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO vehicle (carID,make, model, year,dailyRate,status,passengerCapacity,engineCapacity) VALUES (?,?,?,?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            	preparedStatement.setInt(1, car.getcarID());
            	preparedStatement.setString(2, car.getMake());
                preparedStatement.setString(3, car.getModel());
                preparedStatement.setInt(4, car.getYear());
                preparedStatement.setDouble(5, car.getdailyRate());
                preparedStatement.setString(6, car.getstatus());
                preparedStatement.setInt(7, car.getpassengerCapacity());
                preparedStatement.setInt(8, car.getengineCapacity());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

	@Override
	public void removeCar(int carID) {
		try {
			Connection connection = DBConnection.getConnection();
            String query = "DELETE FROM vehicle WHERE carID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, carID);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }	@Override
	public List<vehicle> listAvailableCars() {
		 List<vehicle> availableCars = new ArrayList<>();
	        try {
	        	Connection connection = DBConnection.getConnection();
	            String query = "SELECT * FROM vehicle WHERE status = 'Available'";
	            try (Statement statement = connection.createStatement()) {
	                try (ResultSet resultSet = statement.executeQuery(query)) {
	                    while (resultSet.next()) {
	                        availableCars.add(mapResultSetToCar(resultSet));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();

	        }
	        return availableCars;
	    }
	@Override
	public List<vehicle> listRentedCars() {
		List<vehicle> rentedCars = new ArrayList<>();
        try {
        	Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM vehicle WHERE status = 'notAvailable'";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        rentedCars.add(mapResultSetToCar(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return rentedCars;
    }
	@Override
	public vehicle findCarById(int carID) throws CarNotFoundException {
		 try {
			    Connection connection = DBConnection.getConnection();
	            String query = "SELECT * FROM vehicle WHERE carID = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, carID);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        return mapResultSetToCar(resultSet);
	                    } else {
	                        throw new CarNotFoundException("Car not found with ID: " + carID);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error finding car by ID: " + carID, e);
	        }
	    }

	 private vehicle mapResultSetToCar(ResultSet resultSet) throws SQLException {
	 int carID = resultSet.getInt("carID");
	 String make = resultSet.getString("make");
	 String model = resultSet.getString("model");
	 int year = resultSet.getInt("year");
	 Double dailyRate = resultSet.getDouble("dailyRate");
	 String status = resultSet.getString("status");
     int passengerCapacity = resultSet.getInt("passengerCapacity");
     int engineCapacity = resultSet.getInt("engineCapacity");
	 return new vehicle(carID,make, model, year,dailyRate, status, passengerCapacity,engineCapacity);
	 }
	@Override
	public void addCustomer(customer customer) {
		 try {
			    Connection connection = DBConnection.getConnection();
		        String query = "INSERT INTO customer (customerID,firstName, lastName, email, phoneNumber) VALUES (?,?, ?, ?, ?)";
		        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        	preparedStatement.setInt(1,customer.getCustomerID());
		            preparedStatement.setString(2, customer.getFirstName());
		            preparedStatement.setString(3, customer.getLastName());
		            preparedStatement.setString(4, customer.getEmail());
		            preparedStatement.setString(5, customer.getPhoneNumber());

		            preparedStatement.executeUpdate();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();

		    }
		}


	@Override
	public void removeCustomer(int customerID) {
		try {
			Connection connection = DBConnection.getConnection();
	        String query = "DELETE FROM customer WHERE customerID = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, customerID);

	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();

	    }
	}

	@Override
	public List<customer> listCustomers() {
		 List<customer> customers = new ArrayList<>();
		    try {
		    	Connection connection = DBConnection.getConnection();
		        String query = "SELECT * FROM Customer";
		        try (Statement statement = connection.createStatement()) {
		            try (ResultSet resultSet = statement.executeQuery(query)) {
		                while (resultSet.next()) {
		                    customers.add(mapResultSetToCustomer(resultSet));
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();

		    }
		    return customers;
		}

	@Override
	public customer findCustomerById(int customerID) throws CustomerNotFoundException {
		try {
			Connection connection = DBConnection.getConnection();
	        String query = "SELECT * FROM customer WHERE customerID = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, customerID);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return mapResultSetToCustomer(resultSet);
	                } else {
	                    throw new CustomerNotFoundException("Customer not found with ID: " + customerID);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error finding customer by ID: " + customerID, e);
	    }
	}
	private customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
	    int customerID = resultSet.getInt("customerID");
	    String firstName = resultSet.getString("firstName");
	    String lastName = resultSet.getString("lastName");
	    String email = resultSet.getString("email");
	    String phoneNumber = resultSet.getString("phoneNumber");

	    return new customer(customerID, firstName, lastName, email, phoneNumber);
	}

	@Override
	public lease createLease(int customerID, int carID, Date startDate, Date endDate) {
		 try {
			    Connection connection = DBConnection.getConnection();

		        String query = "INSERT INTO lease (customerID, carID, startDate, endDate) VALUES (?, ?, ?, ?)";
		        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
		            preparedStatement.setInt(1, customerID);
		            preparedStatement.setInt(2, carID);
		            preparedStatement.setObject(3, new java.sql.Date(startDate.getTime()));
		            preparedStatement.setObject(4, new java.sql.Date(endDate.getTime()));

		            int rowsAffected = preparedStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                // Retrieve the auto-generated leaseID
		                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
		                    if (generatedKeys.next()) {
		                        int generatedLeaseID = generatedKeys.getInt(1);
		                        // You can return the Lease object if needed
		                        return new lease(generatedLeaseID, customerID, carID, startDate, endDate, null);
		                    } else {
		                        throw new SQLException("Creating lease failed, no ID obtained.");
		                    }
		                }
		            } else {
		                throw new SQLException("Creating lease failed, no rows affected.");
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();

		        return null;
		    }
		}

	@Override
	public lease returnCar(int leaseID) {
		try {
			Connection connection = DBConnection.getConnection();
	        String query = "UPDATE lease SET endDate = CURRENT_DATE() WHERE leaseID = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, leaseID);

	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();

	    }
		return null;
	}

	@Override
	public List<lease> listActiveLeases() {
		 List<lease> activeLeases = new ArrayList<>();
		    try {
		    	Connection connection = DBConnection.getConnection();
		        String query = "SELECT * FROM lease WHERE endDate IS NULL";
		        try (Statement statement = connection.createStatement()) {
		            try (ResultSet resultSet = statement.executeQuery(query)) {
		                while (resultSet.next()) {
		                    activeLeases.add(mapResultSetToLease(resultSet));
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();

		    }
		    return activeLeases;
		}
	@Override
	public List<lease> listLeaseHistory() {
	    List<lease> leaseHistory = new ArrayList<>();
	    try {
	    	Connection connection = DBConnection.getConnection();
	        String query = "SELECT * FROM Lease WHERE endDate IS NOT NULL";
	        try (Statement statement = connection.createStatement()) {
	            try (ResultSet resultSet = statement.executeQuery(query)) {
	                while (resultSet.next()) {
	                    leaseHistory.add(mapResultSetToLease(resultSet));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();

	    }
	    return leaseHistory;
	}
	    @Override
	    public lease findLeaseById(int leaseID) throws LeaseNotFoundException {
	        try {
	        	Connection connection = DBConnection.getConnection();

	            String query = "SELECT * FROM Lease WHERE leaseID = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, leaseID);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        return mapResultSetToLease(resultSet);
	                    } else {
	                        throw new LeaseNotFoundException("Lease not found with ID: " + leaseID);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Lease not found with ID: " + leaseID);
	        }

	}
	private lease mapResultSetToLease(ResultSet resultSet) throws SQLException {
	    int leaseID = resultSet.getInt("leaseID");
	    int customerID = resultSet.getInt("customerID");
	    int carID = resultSet.getInt("carID");
	    Date startDate = resultSet.getDate("startDate");
	    Date endDate = resultSet.getDate("endDate");

	    return new lease(leaseID, customerID, carID, startDate, endDate, null);
	}

	@Override
	public void recordPayment(lease lease, double amount) {
		try {
			Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO Payment (leaseID, paymentDate, amount) VALUES (?, CURRENT_DATE(), ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, lease.getLeaseID());
                preparedStatement.setDouble(2, amount);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


