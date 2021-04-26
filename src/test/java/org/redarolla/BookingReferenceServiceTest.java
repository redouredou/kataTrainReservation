package org.redarolla;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redarolla.booking.BookingReferenceService;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingReferenceServiceTest{

    @Mock
    BookingReferenceService bookingReferenceService;



    @Test
    void it_should_return_booking_reference_when_call_get_booking_reference(){
        //GIVEN

        String expectedBookingReference = "1524d";

        when(bookingReferenceService.get()).thenReturn(expectedBookingReference);
        //WHEN
        String bookingReference = bookingReferenceService.get();
        //THEN

        Assertions.assertThat(bookingReference).isEqualTo(expectedBookingReference);

    }
}
