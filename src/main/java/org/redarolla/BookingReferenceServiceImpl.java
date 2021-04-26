package org.redarolla;

public class BookingReferenceServiceImpl implements BookingReferenceService {

    BookingReferenceService bookingReferenceService;

    @Override
    public String get() {
        return bookingReferenceService.get();
    }
}
