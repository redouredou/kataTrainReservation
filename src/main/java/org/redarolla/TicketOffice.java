package org.redarolla;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Seat> seats = trainDataResponse.getSeats();
        List<Seat> freeSeats = seats
                .stream()
                .filter(seat -> seat.getBookingId().length() == 0)
                .collect(Collectors.toList());

        int countSeatsBooked = freeSeats.size();

        if(countSeatsBooked < request.getSeatCount()){
           throw new BookingException("The seats have already a booking reference");
        };

        return new Reservation(request.getTrainId(), freeSeats , expectedBookingReference);
    }

    public TrainDataResponse getTrainData(String trainId){
        return trainDataService.get(trainId);
    }

    public String getBookingReference(){
        return bookingReferenceService.get();
    }

}