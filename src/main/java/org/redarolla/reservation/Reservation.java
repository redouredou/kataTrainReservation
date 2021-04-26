package org.redarolla.reservation;

import org.redarolla.traindata.Seat;

import java.util.List;
import java.util.Objects;

public class Reservation {
    public final String trainId;
    public final String bookingId;
    public final List<Seat> seats;

    public Reservation(String trainId, List<Seat> seats, String bookingId) {
        this.trainId = trainId;
        this.bookingId = bookingId;
        this.seats = seats;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(trainId, that.trainId) && Objects.equals(bookingId, that.bookingId) && Objects.equals(seats, that.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, bookingId, seats);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "trainId='" + trainId + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", seats=" + seats +
                '}';
    }
}