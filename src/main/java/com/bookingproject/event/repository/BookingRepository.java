package com.bookingproject.event.repository;



import com.bookingproject.event.entity.Booking;
import com.bookingproject.event.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);

    List<Booking> findByUserId(int userId);

    List<Booking> findByEventId(int eventId);
}
