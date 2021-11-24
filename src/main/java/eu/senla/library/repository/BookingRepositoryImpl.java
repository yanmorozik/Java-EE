package eu.senla.library.repository;

import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.api.repository.BookingRepository;
import eu.senla.library.model.Author;
import eu.senla.library.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepositoryImpl extends AbstractRepositoryImpl<Booking> implements BookingRepository {

    public BookingRepositoryImpl() {
        super(Booking.class);
    }
}
