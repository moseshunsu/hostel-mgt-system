package net.HostelHub.roommgtservice.service;

import net.HostelHub.roommgtservice.dto.*;
import net.HostelHub.roommgtservice.entity.Room;
import net.HostelHub.roommgtservice.entity.RoomStatus;
import net.HostelHub.roommgtservice.entity.RoomType;
import net.HostelHub.roommgtservice.roomRepository.RoomRepository;
import net.HostelHub.roommgtservice.roomRepository.RoomTypeRepository;
import net.HostelHub.roommgtservice.service.client.TenantFeignClient;
import net.HostelHub.roommgtservice.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    TenantFeignClient tenantFeignClient;
    @Autowired
    RoomTypeRepository roomTypeRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public ResponseEntity<Response> addRoomType(RoomTypeRequest roomTypeRequest) {

        String hostelName = roomTypeRequest.getHostelName();
        String school = roomTypeRequest.getSchoolName();

        HostelPropertyDto fetchedDto = tenantFeignClient.fetchProperty(hostelName,school);

        if (fetchedDto != null) {
            RoomType roomType = new RoomType();
            roomType.setNumberInARoom(roomTypeRequest.getNumberInARoom());
            roomType.setHostelName(fetchedDto.getHostelName());
            roomType.setSchoolName(fetchedDto.getSchool());
            roomType.setTenantCode(fetchedDto.getTenantCode());
            roomType.setPricePerBed(roomTypeRequest.getPricePerBed());
            roomType.setDescription(roomTypeRequest.getDescription());

            RoomType savedRoomType = roomTypeRepository.save(roomType);

            return ResponseEntity.ok().body(
                    Response.builder()
                            .responseCode(ResponseUtils.SUCCESS_CODE)
                            .responseMessage(ResponseUtils.ROOM_TYPE_ENTRY_SUCCESS)
                            .data(
                                    Data.builder()
                                            .tenantCode(savedRoomType.getTenantCode())
                                            .hostelName(savedRoomType.getHostelName())
                                            .build()
                            )
                            .build()
            );
        }

            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .responseCode(ResponseUtils.NOT_FOUND_CODE)
                            .responseMessage(ResponseUtils.HOSTEL_NOT_FOUND_MESSAGE)
                            .build()
            );
    }

    @Override
    public ResponseEntity<Response> addRoom(RoomRequest roomRequest) {
        boolean isRoomExists = roomRepository.existsById(roomRequest.getRoomNumber());

        if (isRoomExists) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .responseCode(ResponseUtils.ROOM_EXISTS_CODE)
                            .responseMessage(ResponseUtils.ROOM_EXISTS_MESSAGE)
                            .data(
                                    Data.builder()
                                            .roomNumber(roomRequest.getRoomNumber())
                                            .build()
                            )
                            .build()
            );
        }

        Room room = new Room();
        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setRoomType(roomTypeRepository.findByHostelNameAndNumberInARoomAndSchoolName(roomRequest.getHostelName(),
                roomRequest.getNumberInARoom(), roomRequest.getSchoolName()));
        room.setRoomStatus(RoomStatus.valueOf(roomRequest.getRoomStatus()));
        room.setSex(roomRequest.getSex());

        if (roomRequest.getRoomStatus().equals("MAINTENANCE")) {
            room.setBedAvailable(0);
        } else room.setBedAvailable(room.getRoomType().getNumberInARoom());

        Room savedRoom = roomRepository.save(room);

        return ResponseEntity.ok().body(
                Response.builder()
                        .responseCode(ResponseUtils.SUCCESS_CODE)
                        .responseMessage(ResponseUtils.ROOM_ENTRY_SUCCESS)
                        .data(
                                Data.builder()
                                        .hostelName(savedRoom.getRoomType().getHostelName())
                                        .roomNumber(savedRoom.getRoomNumber())
                                        .tenantCode(savedRoom.getRoomType().getTenantCode())
                                        .build()
                        )
                        .build()
        );
    }

    @Override
    public ResponseEntity<RoomResponseDto> fetchRoomDetails(String schoolName, String hostelName, String roomNumber) {

        Optional<Room> roomOptional = roomRepository.findAll().stream()
                .filter(room ->
                                room.getRoomType().getHostelName().equals(hostelName) &&
                                room.getRoomType().getSchoolName().equals(schoolName) &&
                                room.getRoomNumber().equals(roomNumber)
                )
                .findFirst();

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            RoomResponseDto roomDetails = RoomResponseDto.builder()
                    .hostelName(room.getRoomType().getHostelName())
                    .schoolName(room.getRoomType().getSchoolName())
                    .tenantCode(room.getRoomType().getTenantCode())
                    .pricePerBed(room.getRoomType().getPricePerBed())
                    .description(room.getRoomType().getDescription())
                    .roomNumber(room.getRoomNumber())
                    .sex(String.valueOf(room.getSex()))
                    .numberInARoom(room.getRoomType().getNumberInARoom())
                    .bedAvailable(room.getBedAvailable())
                    .build();

            return ResponseEntity.ok().body(roomDetails);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // This allows occupants search for available rooms in a particular school
    @Override
    public ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(String schoolName, String hostelName) {

        List<RoomResponseDto> rooms = roomRepository.findAll().stream().filter(
                room -> room.getRoomType().getHostelName().equals(hostelName) &&
                        room.getRoomType().getSchoolName().equals(schoolName) &&
                        room.getRoomStatus().equals(RoomStatus.AVAILABLE)
        ).map(
                room -> RoomResponseDto.builder()
                        .hostelName(room.getRoomType().getHostelName())
                        .schoolName(room.getRoomType().getSchoolName())
                        .tenantCode(room.getRoomType().getTenantCode())
                        .pricePerBed(room.getRoomType().getPricePerBed())
                        .description(room.getRoomType().getDescription())
                        .roomNumber(room.getRoomNumber())
                        .sex(String.valueOf(room.getSex()))
                        .numberInARoom(room.getRoomType().getNumberInARoom())
                        .bedAvailable(room.getBedAvailable())
                        .build()
        ).toList();

        return ResponseEntity.ok().body(rooms);

    }

}
