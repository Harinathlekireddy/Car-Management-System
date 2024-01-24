package dao;
import java.util.Date;
import java.util.List;

import entity.customer;
import entity.lease;
import entity.vehicle;
import myexceptions.CarNotFoundException;
import myexceptions.CustomerNotFoundException;
import myexceptions.LeaseNotFoundException;

public interface ICarLeaseRepository {

    void addCar(vehicle car);
    void removeCar(int carID);
    List<vehicle> listAvailableCars();
    List<vehicle> listRentedCars();
    vehicle findCarById(int carID) throws CarNotFoundException;


    void addCustomer(customer customer);
    void removeCustomer(int customerID);
    List<customer> listCustomers();
    customer findCustomerById(int customerID) throws CustomerNotFoundException;


    lease createLease(int customerID, int carID, Date startDate, Date endDate);
    lease returnCar(int leaseID);
    List<lease> listActiveLeases();
    List<lease> listLeaseHistory();
    lease findLeaseById(int leaseID) throws LeaseNotFoundException;



    void recordPayment(lease lease, double amount);
}
