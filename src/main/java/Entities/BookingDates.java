package Entities;

// Classe desenvolvida recebendo as datas e separando-as. :) //
public class BookingDates {

    private String checkin;
    private String checkout;


// Desenvolvimento do construtor, repassando as propriedades criadas. :) //
    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

// Desenvolvimento dos métodos de acesso - gets e sets. :) //
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}