package net.hostelHub.bookingreservationservice.service.impl;

import net.hostelHub.bookingreservationservice.dto.BookingRequest;
import net.hostelHub.bookingreservationservice.dto.Data;
import net.hostelHub.bookingreservationservice.dto.Response;
import net.hostelHub.bookingreservationservice.entity.Booking;
import net.hostelHub.bookingreservationservice.entity.Status;
import net.hostelHub.bookingreservationservice.repository.BookingRepository;
import net.hostelHub.bookingreservationservice.repository.OccupantRepository;
import net.hostelHub.bookingreservationservice.service.BookingService;
import net.hostelHub.bookingreservationservice.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private OccupantRepository occupantRepository;
    @Autowired
    private BookingRepository bookingRepository;

    // Add a search class to get fetch hostel details

    @Override
    public ResponseEntity<Response> makeBooking(BookingRequest bookingRequest) {
        boolean isOccupantExists = occupantRepository.existsByOccupantCode(bookingRequest.getOccupantCode());

        Booking booking = new Booking();
        booking.setHostelName(bookingRequest.getHostelName());
        booking.setSchool(bookingRequest.getSchool());
        booking.setState(bookingRequest.getState());
        booking.setOccupant(occupantRepository.findByOccupantCode(bookingRequest.getOccupantCode()));
        booking.setAcademicYear(bookingRequest.getAcademicYear());
        booking.setRoomNumber(bookingRequest.getRoomNumber());
        booking.setPrice(bookingRequest.getPrice());
        booking.setStatus(Status.PENDING);

        Booking savedbooking = bookingRepository.save(booking);

        return ResponseEntity.ok().body(
                Response.builder()
                        .responseCode(ResponseUtils.SUCCESS_CODE)
                        .responseMessage(ResponseUtils.BOOKING_SUCCESS_MESSAGE)
                        .data(
                                Data.builder()
                                        .roomNumber(savedbooking.getRoomNumber())
                                        .occupantCode(savedbooking.getOccupant().getOccupantCode())
                                        .email(savedbooking.getOccupant().getEmail())
                                        .build()
                        )
                        .build()
        );
    }

}
