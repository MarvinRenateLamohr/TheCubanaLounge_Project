package za.co.marvinlamohr.thecubanalounge;


public class Reservation {

    private String reservationFirstName;
    private String reservationLastName;
    private Long reservationContactNumber;
    private String reservationTime;
    private String reservationDate;


    public Reservation(String reservationFirstName, String reservationLastName, Long reservationContactNumber, String reservationTime, String reservationDate) {
        this.reservationFirstName = reservationFirstName;
        this.reservationLastName = reservationLastName;
        this.reservationContactNumber = reservationContactNumber;
        this.reservationTime = reservationTime;
        this.reservationDate = reservationDate;
    }


    public String getReservationFirstName() {
        return reservationFirstName;
    }

    public void setReservationFirstName(String reservationFirstName) {
        this.reservationFirstName = reservationFirstName;
    }

    public String getReservationLastName() {
        return reservationLastName;
    }

    public void setReservationLastName(String reservationLastName) {
        this.reservationLastName = reservationLastName;
    }

    public Long getReservationContactNumber() {
        return reservationContactNumber;
    }

    public void setReservationContactNumber(Long reservationContactNumber) {
        this.reservationContactNumber = reservationContactNumber;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
