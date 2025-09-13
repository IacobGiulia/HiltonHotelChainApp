package org.example;

public class Guest {

    private int guestId;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private int hotelId;

    public Guest(int guestId, String guestName, String guestEmail, String guestPhone, int hotelId) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
        this.hotelId = hotelId;
    }

    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public String getGuestName() {
        return guestName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    public String getGuestEmail() {
        return guestEmail;
    }
    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
    public String getGuestPhone() {
        return guestPhone;
    }
    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    void addGuests(Guest guest) {
        System.out.println("Please insert the guest's information: ");
        System.out.println("Guest Id: " + guest.getGuestId());
        System.out.println("Guest Name: " + guest.getGuestName());
        System.out.println("Guest Email: " + guest.getGuestEmail());
        System.out.println("Guest Phone: " + guest.getGuestPhone());
        System.out.println("Hotel Id: " + guest.getHotelId());

    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + guestName + '\'' +
                ", email='" + guestEmail + '\'' +
                ", phone='" + guestPhone + '\'' +
                ", hotelId=" + hotelId +
                '}';
    }

}
