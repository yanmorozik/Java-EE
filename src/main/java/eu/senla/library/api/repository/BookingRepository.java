package eu.senla.library.api.repository;

import eu.senla.library.model.Booking;

import java.util.List;

public interface BookingRepository extends AbstractRepository<Booking>{

    Booking add(Booking booking);

    Booking findById(Long id);

    List<Booking> findAll();

    Booking update(Booking booking);

    void delete(Booking booking);

    void deleteById(Long id);
}
