package entity;
import java.util.Date;

public class lease {

	private int leaseID;
    private int carID;
    private int customerID;
    private Date startDate;
    private Date endDate;
    private String type;


    public lease() {}


    public lease(int leaseID, int carID, int customerID, Date startDate, Date endDate, String type) {
        this.leaseID = leaseID;
        this.carID = carID;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }


    public int getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public int getcarID() {
        return carID;
    }

    public void setcarID(int carID) {
        this.carID = carID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
	public String toString() {
        return "Lease{" +
                "leaseID=" + leaseID +
                ", customerID=" + customerID +
                ", carID=" + carID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ",type="+type+
                '}';
    }
}