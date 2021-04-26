package org.redarolla;

import java.util.Arrays;
import java.util.List;

public class TicketOffice {

    private TrainDataService trainDataService;
    private BookingReferenceService bookingReferenceService;

    public TicketOffice(TrainDataService trainDataService, BookingReferenceService bookingReferenceService) {

        this.trainDataService = trainDataService;
        this.bookingReferenceService = bookingReferenceService;

    }

    public Reservation makeReservation(ReservationRequest request) {
        String expectedBookingReference = bookingReferenceService.get();
        TrainDataResponse trainDataResponse = trainDataService.get("1");
        List<Seat> seats = Arrays.asList(new Seat("A", 1, ""), new Seat("A", 2, ""));
        return new Reservation("1", seats , "1524d");
    }

    public TrainDataResponse getTrainData(String trainId){
        return trainDataService.get(trainId);
    }

    public String getBookingReference(){
        return bookingReferenceService.get();
    }

}