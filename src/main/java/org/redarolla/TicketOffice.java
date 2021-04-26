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

    public Reservation makeReservation(ReservationRequest request) throws BookingException {
        String expectedBookingReference = bookingReferenceService.get();
        TrainDataResponse trainDataResponse = trainDataService.get(request.getTrainId());
        String bookingIdFromSeatOne = trainDataResponse.getSeats().get(0).getBookingId();
        String bookingIdFromSeatTwo = trainDataResponse.getSeats().get(1).getBookingId();

        if(bookingIdFromSeatOne.length() != 0 || bookingIdFromSeatTwo.length() != 0 ){
            throw new BookingException("The seats have already a booking reference");
        }
        return new Reservation(request.getTrainId(), trainDataResponse.getSeats() , expectedBookingReference);
    }

    public TrainDataResponse getTrainData(String trainId){
        return trainDataService.get(trainId);
    }

    public String getBookingReference(){
        return bookingReferenceService.get();
    }

}