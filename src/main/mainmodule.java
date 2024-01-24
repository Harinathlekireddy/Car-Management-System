package main;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import dao.ICarLeaseRepository;
import dao.ICarLeaseRepositoryImpl;
import entity.customer;
import entity.lease;
import entity.vehicle;
import myexceptions.CarNotFoundException;
import myexceptions.CustomerNotFoundException;
import myexceptions.LeaseNotFoundException;


public class mainmodule {
	private final ICarLeaseRepository carLeaseRepository;


    public mainmodule(ICarLeaseRepository carLeaseRepository) {
    this.carLeaseRepository = carLeaseRepository;
    }
    public void run() throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
			int choice;

			do {
				System.out.println("WELCOME TO CAR RENTAL SYSTEM");
			    System.out.println("Menu:\n");
			    System.out.println("1. Add Car");
			    System.out.println("2. Remove Car");
			    System.out.println("3. List Available Cars");
			    System.out.println("4. List Rented Cars");
			    System.out.println("5. Find Car by ID");
			    System.out.println("6. Add Customer");
			    System.out.println("7. Remove Customer");
			    System.out.println("8. List Customers");
			    System.out.println("9. Find Customer by ID");
			    System.out.println("10. create a lease");
			    System.out.println("11. return a car ");
			    System.out.println("12. list active leases");
			    System.out.println("13. list history leases");
			    System.out.println("14. find lease by leaseID");
			    System.out.println("15. record payment");
			    System.out.println("0. Exit");
			    System.out.print("Enter your choice: \n");

			    choice = scanner.nextInt();
                 scanner.nextLine();
			    switch (choice) {
			        case 1:
			            addCar();
			            break;
			        case 2:
			            removeCar();
			            break;
			        case 3:
			            listAvailableCars();
			            break;
			        case 4:
			            listRentedCars();
			            break;
			        case 5:
			            findCarById();
			            break;
			        case 6:
			            addCustomer();
			            break;
			        case 7:
			            removeCustomer();
			            break;
			        case 8:
			            listCustomers();
			            break;
			        case 9:
			            findCustomerById();
			            break;
			        case 10:
			        	createLease();
			        	break;
			        case 11:
			        	returnCar();
			        	break;
			        case 12:
			        	listActiveLeases();
			        	break;
			        case 13:
			        	listLeaseHistory();
			        	break;
			        case 14:
			        	findLeaseById();
			        	break;
			        case 15:
			        	recordPayment();
			        	break;
			        case 0:
			            System.out.println("Exiting the application. Goodbye!");
			            break;
			        default:
			            System.out.println("Invalid choice. Please enter a valid option.");
			    }
			} while (choice != 0);
		}
    }

    private void addCar() {
            Scanner scanner = new Scanner(System.in);
			System.out.println("Enter carID:");
			int carID =scanner.nextInt();
			System.out.println("Enter make: \n");
			scanner.nextLine();
			String make = scanner.nextLine();
			System.out.println("Enter model: \n");
			String model = scanner.nextLine();
			System.out.println("Enter year: \n");
			int year = scanner.nextInt();
			System.out.println("Enter daily rate: \n");
			double dailyRate = scanner.nextDouble();
			System.out.println("Enter status (available/notAvailable):\n ");
			scanner.nextLine();
			String status = scanner.nextLine();
			System.out.println("Enter passenger capacity: \n");
			int passengerCapacity = scanner.nextInt();
			System.out.println("Enter engine capacity:\n ");
			int engineCapacity = scanner.nextInt();

			vehicle car = new vehicle(carID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity);
			carLeaseRepository.addCar(car);

        System.out.println("Car added successfully. \n");
    }


    private void removeCar() throws SQLException {
            Scanner scanner = new Scanner(System.in);
			System.out.print("Enter car ID to remove: ");
			int carID = scanner.nextInt();
		    carLeaseRepository.removeCar(carID);

        System.out.println("Car removed successfully.");
    }

    private void listAvailableCars() {
        List<vehicle> availableCars = carLeaseRepository.listAvailableCars();
        System.out.println("Available Cars:");
        for (vehicle car : availableCars) {
            System.out.println(car);
        }
    }

    private void listRentedCars() {
        List<vehicle> rentedCars = carLeaseRepository.listRentedCars();
        System.out.println("Rented Cars:");
        for (vehicle car : rentedCars) {
            System.out.println(car);
        }
    }

    private void findCarById() {
            Scanner scanner = new Scanner(System.in);
			System.out.print("Enter carID: ");
			int carId = scanner.nextInt();
			try {
			    vehicle car = carLeaseRepository.findCarById(carId);
			    System.out.println("Car details: "+car);
			} catch (CarNotFoundException e) {
			    System.out.println("Error: " + e.getMessage());
			}
		}


    private void addCustomer() {
            Scanner scanner = new Scanner(System.in);
			System.out.println("Enter customerID: \n");
			int customerID=scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter first name: \n");
			String firstName = scanner.nextLine();
			System.out.print("Enter last name:\n ");
			String lastName = scanner.nextLine();
			System.out.print("Enter email: \n");
			String email = scanner.nextLine();
			System.out.print("Enter phone number: \n");
			String phoneNumber = scanner.nextLine();

			customer customer = new customer(customerID,firstName, lastName, email, phoneNumber);
			carLeaseRepository.addCustomer(customer);

            System.out.println("Customer added successfully.");
    }

    private void removeCustomer() {
            Scanner scanner = new Scanner(System.in);
			System.out.print("Enter customer ID to remove: ");
			int customerID = scanner.nextInt();
			carLeaseRepository.removeCustomer(customerID);

            System.out.println("Customer removed successfully.");
    }

    private void listCustomers() {
        List<customer> customers = carLeaseRepository.listCustomers();
        System.out.println("List of Customers:");
        for (customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void findCustomerById() {
    	    System.out.print("Enter customer ID: ");
            Scanner scanner = new Scanner(System.in);
			int customerID = scanner.nextInt();
			try {
			    customer customer = carLeaseRepository.findCustomerById(customerID);
			    System.out.println("Customer details:"+customer);
			} catch (CustomerNotFoundException e) {
			    System.out.println("Error: " + e.getMessage());
			}
		}


    private void createLease() {
            Scanner scanner = new Scanner(System.in);
			System.out.print("Enter customer ID: ");
			int customerID = scanner.nextInt();
			System.out.print("Enter car ID: ");
			int carID = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Enter start date (yyyy-MM-dd): ");
			String startDateStr = scanner.next();
			System.out.print("Enter end date (yyyy-MM-dd): ");
			String endDateStr = scanner.next();

			try {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    java.util.Date startDateUtil = dateFormat.parse(startDateStr);
			    java.util.Date endDateUtil = dateFormat.parse(endDateStr);


			    java.sql.Date startDate = new java.sql.Date(startDateUtil.getTime());
			    java.sql.Date endDate = new java.sql.Date(endDateUtil.getTime());

			    lease lease = carLeaseRepository.createLease(customerID, carID, startDate, endDate);
			    System.out.println("Lease created successfully. Lease ID: " + lease.getLeaseID());
			} catch (ParseException e) {
			    System.out.println("Invalid date format. Please enter dates in the format yyyy-MM-dd.");
			}
		}


    private void returnCar(){
            Scanner scanner = new Scanner(System.in);
			System.out.print("Enter lease ID to return the car: ");
			if (scanner.hasNextInt()) { // Check if there's an integer to read
	            int leaseID = scanner.nextInt();
	            lease returnedLease = carLeaseRepository.returnCar(leaseID);
	            System.out.println("Car returned successfully. Lease details: " + returnedLease);
	        } else {
	            System.out.println("Invalid input. Please enter a valid lease ID.");
	        }
		}


    private void listActiveLeases() {
        List<lease> activeLeases = carLeaseRepository.listActiveLeases();
        System.out.println("Active Leases:");
        for (lease lease : activeLeases) {
            System.out.println(lease);
        }
    }

    private void listLeaseHistory() {
        List<lease> leaseHistory = carLeaseRepository.listLeaseHistory();
        System.out.println("Lease History:");
        for (lease lease : leaseHistory) {
            System.out.println(lease);
        }
    }
    private void findLeaseById() {
    	System.out.println("Enter leaseID");
    	Scanner scanner = new Scanner(System.in);
    	int leaseID=scanner.nextInt();
    	try {
			lease lease = carLeaseRepository.findLeaseById( leaseID);
			System.out.println("Lease Details"+lease);
		} catch (LeaseNotFoundException e) {
			 System.out.println("Error: " + e.getMessage());
		}
    }
    private void recordPayment() {
            Scanner scanner = new Scanner(System.in);
			System.out.print("Enter lease ID for the payment: ");
			int leaseID = scanner.nextInt();
			System.out.print("Enter payment amount: ");
			double amount = scanner.nextDouble();
			try {
			    lease lease = carLeaseRepository.findLeaseById(leaseID);
			    carLeaseRepository.recordPayment(lease, amount);
			    System.out.println("Payment recorded successfully for Lease ID: " + leaseID);
			} catch (LeaseNotFoundException e) {
			    System.out.println("Error: " + e.getMessage());
			}
		}


public static void main(String[] args) throws SQLException {
    ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl(null);
    mainmodule mainmodule = new mainmodule(carLeaseRepository);
    mainmodule.run();
}
}