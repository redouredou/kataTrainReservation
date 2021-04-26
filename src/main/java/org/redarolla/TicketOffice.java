package org.redarolla;

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
        return new Reservation(null, null , "1524d");
    }

    public TrainDataResponse getTrainData(String trainId){
        return trainDataService.get(trainId);
    }

    public String getBookingReference(){
        return bookingReferenceService.get();
    }

}