package eu.senla.library.repository;

import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.model.Booking;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepositoryImpl extends AbstractRepositoryImpl<Booking> implements BookingRepository {

    public BookingRepositoryImpl() {
        super(Booking.class);
    }

    @Override
    protected String getNameGraph() {
        return "bookingEntityGraph";
    }
}
