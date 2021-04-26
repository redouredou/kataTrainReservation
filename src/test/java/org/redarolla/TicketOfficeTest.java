package org.redarolla;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketOfficeTest {

    @Mock
    TrainDataService trainDataService;

    @Mock
    BookingReferenceService bookingReferenceService;

    @Test
    void it_should_return_valid_Reservation_seats_when_makeReservation(){

        //GIVEN
        String expectedTrainId = "1";
        int numberOfSeats = 2;

        String expectedBookingReference = "1524d";
        when(bookingReferenceService.get()).thenReturn(expectedBookingReference);

        Seat seat1A = new Seat("A", 1, "");
        Seat seat2A = new Seat("A", 2, "");
        List<Seat> expectedSeats = Arrays.asList(seat1A, seat2A);
        TrainDataResponse trainDataResponse = new TrainDataResponse(expectedSeats);
        when(trainDataService.get(expectedTrainId)).thenReturn(trainDataResponse);

        TicketOffice ticketOffice = new TicketOffice(trainDataService, bookingReferenceService);

        ReservationRequest reservationRequest = new ReservationRequest(expectedTrainId, numberOfSeats);

        Reservation expectedReservation = new Reservation(expectedTrainId, expectedSeats, expectedBookingReference);
        //WHEN
        Reservation actualReservation = ticketOffice.makeReservation(reservationRequest);

        //THEN
        Assertions.assertThat(actualReservation.getBookingId()).isEqualTo(expectedReservation.getBookingId());

    }
}
