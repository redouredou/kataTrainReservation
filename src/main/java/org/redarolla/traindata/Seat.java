package org.redarolla.traindata;

import java.util.Objects;

public class Seat {
    public final String coach;
    public final int seatNumber;
    public final String bookingId;

    public Seat(String coach, int seatNumber, String bookingId) {
        this.bookingId = bookingId;
        this.coach = coach;
        this.seatNumber = seatNumber;
    }

    public String getCoach() {
        return coach;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return seatNumber == seat.seatNumber && Objects.equals(coach, seat.coach) && Objects.equals(bookingId, seat.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, seatNumber, bookingId);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "coach='" + coach + '\'' +
                ", seatNumber=" + seatNumber +
                ", bookingId='" + bookingId + '\'' +
                '}';
    }
}