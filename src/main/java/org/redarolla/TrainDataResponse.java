package org.redarolla;

import java.util.List;
import java.util.Objects;

public class TrainDataResponse {
    private List<Seat> seats;

    public TrainDataResponse(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainDataResponse that = (TrainDataResponse) o;
        return Objects.equals(seats, that.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seats);
    }

    @Override
    public String toString() {
        return "TrainDataResponse{" +
                "seats=" + seats +
                '}';
    }
}
