package net.hostelHub.bookingreservationservice.service.impl;

import net.hostelHub.bookingreservationservice.dto.*;
import net.hostelHub.bookingreservationservice.entity.Booking;
import net.hostelHub.bookingreservationservice.utils.Status;
import net.hostelHub.bookingreservationservice.repository.BookingRepository;
import net.hostelHub.bookingreservationservice.repository.OccupantRepository;
import net.hostelHub.bookingreservationservice.service.BookingService;
import net.hostelHub.bookingreservationservice.service.client.RoomFeignClient;
import net.hostelHub.bookingreservationservice.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private OccupantRepository occupantRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomFeignClient roomFeignClient;

    @Override
    public ResponseEntity<Response> makeBooking(BookingRequest bookingRequest) {
        ResponseEntity<RoomResponseDto> fetchedRoomDetails =
                roomFeignClient.fetchRoomDetails(bookingRequest.getSchoolName(),
                        bookingRequest.getHostelName(), bookingRequest.getRoomNumber());

        RoomResponseDto fetchedRoomDetailsBody = fetchedRoomDetails.getBody();

//        boolean isOccupantExists = occupantRepository.existsByOccupantCode(bookingRequest.getOccupantCode());

        if (fetchedRoomDetailsBody != null) {
            Booking booking = new Booking();
            booking.setHostelName(fetchedRoomDetailsBody.getHostelName());
            booking.setSchool(fetchedRoomDetailsBody.getSchoolName());
            booking.setTenantCode(fetchedRoomDetailsBody.getTenantCode());
            booking.setOccupant(occupantRepository.findByOccupantCode(bookingRequest.getOccupantCode()));
            booking.setAcademicYear(bookingRequest.getAcademicYear());
            booking.setRoomNumber(fetchedRoomDetailsBody.getRoomNumber());
            booking.setPrice(fetchedRoomDetailsBody.getPricePerBed());
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
        } else  return ResponseEntity.badRequest().body(
                Response.builder()
                        .responseCode(ResponseUtils.ROOM_NOT_FOUND_CODE)
                        .responseMessage(ResponseUtils.ROOM_NOT_FOUND_MESSAGE)
                        .data(
                                Data.builder()
                                        .roomNumber(bookingRequest.getOccupantCode())
                                        .roomNumber(bookingRequest.getRoomNumber())
                                        .build()
                        )
                        .build()
        );
    }

    // This allows occupants search for available rooms in a particular school
    @Override
    public ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(SchoolRequest schoolRequest) {
        return roomFeignClient.fetchAvailableRooms(schoolRequest.getSchoolName(), schoolRequest.getHostelName());
    }

}
