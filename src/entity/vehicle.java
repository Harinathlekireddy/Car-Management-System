package entity;

public class vehicle {
  private int carID;
    private String make;
    private String model;
    private int year;
    private double dailyRate;
    private String status;
    private int passengerCapacity;
    private int engineCapacity;


    public vehicle() {}


    public vehicle(int carID, String make, String model, int year, double dailyRate, String status, int passengerCapacity, int engineCapacity) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.status = status;
        this.passengerCapacity = passengerCapacity;
        this.engineCapacity = engineCapacity;
    }


    public int getcarID() {
        return carID;
    }

    public void setcarID(int carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getdailyRate() {
        return dailyRate;
    }

    public void setdailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public int getpassengerCapacity() {
        return passengerCapacity;
    }

    public void setpassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getengineCapacity() {
        return engineCapacity;
    }

    public void setengineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
	public String toString() {
       return "Vehicle{" +
            "carID=" + carID +
            ", make='" + make + '\'' +
            ", model='" + model + '\'' +
            ", year=" + year +
            ", dailyRate=" + dailyRate +
            ", status='" + status + '\'' +
            ", passengerCapacity=" + passengerCapacity +
            ", engineCapacity=" + engineCapacity +
            '}';
}
}

