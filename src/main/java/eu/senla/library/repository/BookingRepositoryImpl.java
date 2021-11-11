package eu.senla.library.repository;

import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private final List<Booking> bookings = new ArrayList<>();

    private static Long facilitiesIdSequence = 0L;

    private static Long generateFacilitiesId() {
        return facilitiesIdSequence++;
    }

    @Override
    public Booking add(Booking booking) {
        booking.setId(generateFacilitiesId());
        bookings.add(booking);
        return booking;
    }

    @Override
    public Booking findById(Long id) {
        return bookings.get(id.intValue());
    }

    @Override
    public List<Booking> findAll() {
        return bookings;
    }

    @Override
    public Booking update(Booking booking) {
        Long index = booking.getId();
        bookings.set(index.intValue(), booking);
        return booking;
    }

    @Override
    public void delete(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public void deleteById(Long id) {
        bookings.remove(id.intValue());
    }
}
