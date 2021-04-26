package org.redarolla;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redarolla.booking.BookingReferenceService;
import org.redarolla.reservation.TicketOffice;
import org.redarolla.traindata.Seat;
import org.redarolla.traindata.TrainDataResponse;
import org.redarolla.traindata.TrainDataService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainDataServiceTest {

    @Mock
    TrainDataService trainDataService;

    @Mock
    BookingReferenceService bookingReferenceService;

    @Test
    void should_return_train_datas_when_provide_train_id(){
        String trainId = "1";

        Seat seat1A = new Seat("A", 1, "");
        Seat seat2A = new Seat("A", 2, "");

        List<Seat> seats = Arrays.asList(seat1A, seat2A);
        TrainDataResponse trainDataResponse = new TrainDataResponse(seats);

        when(trainDataService.get(trainId)).thenReturn(trainDataResponse);

        TicketOffice ticketOffice = new TicketOffice(trainDataService, bookingReferenceService);

        TrainDataResponse trainDataActual = ticketOffice.getTrainData(trainId);

        Assertions.assertThat(trainDataActual).isEqualTo(trainDataResponse);
    }
}
