package org.redarolla;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redarolla.booking.BookingException;
import org.redarolla.booking.BookingReferenceService;
import org.redarolla.reservation.Reservation;
import org.redarolla.reservation.ReservationRequest;
import org.redarolla.reservation.TicketOffice;
import org.redarolla.traindata.Seat;
import org.redarolla.traindata.TrainDataResponse;
import org.redarolla.traindata.TrainDataService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketOfficeTest {

    @Mock
    TrainDataService trainDataService;

    @Mock
    BookingReferenceService bookingReferenceService;

    static Seat seat1A;
    static Seat seat2A;
    static Seat seat3A;
    static Seat seat4B;
    static Seat seat5B;

    static String expectedTrainId;
    static String expectedBookingReference;

    static List<Seat> expectedOnlyFreeSeats;
    static List<Seat> expectedMixedSeats;

    @BeforeAll
    static void init(){
         seat1A = new Seat("A", 1, "541563");
         seat2A = new Seat("A", 2, "");
         seat3A = new Seat("A", 3, "2575");
         seat4B = new Seat("B", 4, "");
         seat5B = new Seat("B", 5, "5875");

        expectedTrainId = "1";
        expectedBookingReference = "1524d";


        expectedMixedSeats = Arrays.asList(seat2A, seat4B);
        expectedOnlyFreeSeats = Arrays.asList(seat2A, seat2A, seat2A);
    }

    @Test
    void it_should_refuse_Reservation_if_seats_has_already_booking_reference_when_makeReservation(){

        //GIVEN
        String expectedTrainId = "1";
        int numberOfSeats = 2;

        String expectedBookingReference = "1524d";
        when(bookingReferenceService.get()).thenReturn(expectedBookingReference);

        Seat seat1A = new Seat("A", 1, "541563");
        Seat seat2A = new Seat("A", 2, "875851");
        List<Seat> expectedSeats = Arrays.asList(seat1A, seat2A);
        TrainDataResponse trainDataResponse = new TrainDataResponse(expectedSeats);
        when(trainDataService.get(expectedTrainId)).thenReturn(trainDataResponse);

        TicketOffice ticketOffice = new TicketOffice(trainDataService, bookingReferenceService);

        ReservationRequest reservationRequest = new ReservationRequest(expectedTrainId, numberOfSeats);

        //WHEN

        Assertions.assertThatThrownBy( () -> ticketOffice.makeReservation(reservationRequest))
                .isInstanceOf(BookingException.class)
                .hasMessageContaining("The seats have already a booking reference");

    }

    @ParameterizedTest(name = "should return {1} with {0}")
    @MethodSource("provideMixFreeAndBookedSeats")
    void it_should_return_Reservation_with_seats_empty_and_seats_booked_when_makeReservation(List<Seat> inputSeats, Reservation expectedReservation) throws BookingException {

        //GIVEN
        String expectedTrainId = "1";
        int numberOfSeats = 2;

        String expectedBookingReference = "1524d";
        when(bookingReferenceService.get()).thenReturn(expectedBookingReference);

        TrainDataResponse trainDataResponse = new TrainDataResponse(inputSeats);
        when(trainDataService.get(expectedTrainId)).thenReturn(trainDataResponse);

        TicketOffice ticketOffice = new TicketOffice(trainDataService, bookingReferenceService);

        ReservationRequest reservationRequest = new ReservationRequest(expectedTrainId, numberOfSeats);

        //WHEN
        Reservation actualReservation = ticketOffice.makeReservation(reservationRequest);

        //THEN
        Assertions.assertThat(actualReservation.getBookingId()).isEqualTo(expectedReservation.getBookingId());
        Assertions.assertThat(actualReservation.getTrainId()).isEqualTo(expectedReservation.getTrainId());
        Assertions.assertThat(actualReservation.getSeats()).isEqualTo(expectedReservation.getSeats());

    }


    public static Stream<Arguments> provideMixFreeAndBookedSeats(){
        return Stream.of(
                Arguments.of(Arrays.asList(seat2A, seat2A,seat2A), new Reservation(expectedTrainId, expectedOnlyFreeSeats, expectedBookingReference)),
                Arguments.of(Arrays.asList(seat1A, seat2A, seat3A, seat4B, seat5B), new Reservation(expectedTrainId, expectedMixedSeats, expectedBookingReference)
                )
        );
    }



}
