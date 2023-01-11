package Entities;

/* Classe referente ao domínio de Booking, com suas propriedades (exemplo: lastname, firstname...). :)
Para facilitar que a identidade possa ser repassada para requisição do Rest-Assured,
o nome da propriedade tem que seguir a mesma convenção do nome na API. :) */
public class Booking {
    private String lastname;
    private String firstname;
    private float totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;


// Desenvolvimento do construtor, repassando as propriedades criadas. :) //
    public Booking(String firstName, String lastName, float totalPrice, boolean depositPaid, BookingDates bookingDates, String additionalNeeds) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.totalprice = totalPrice;
        this.depositpaid = depositPaid;
        this.bookingdates = bookingDates;
        this.additionalneeds = additionalNeeds;
    }

// Desenvolvimento dos métodos de acesso - gets e sets. :) //
    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}