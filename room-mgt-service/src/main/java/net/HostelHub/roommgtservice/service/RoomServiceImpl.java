package net.HostelHub.roommgtservice.service;

import lombok.extern.slf4j.Slf4j;
import net.HostelHub.roommgtservice.dto.*;
import net.HostelHub.roommgtservice.entity.Room;
import net.HostelHub.roommgtservice.entity.RoomStatus;
import net.HostelHub.roommgtservice.entity.RoomType;
import net.HostelHub.roommgtservice.roomRepository.RoomRepository;
import net.HostelHub.roommgtservice.roomRepository.RoomTypeRepository;
import net.HostelHub.roommgtservice.service.client.TenantFeignClient;
import net.HostelHub.roommgtservice.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        room.setStatus(RoomStatus.valueOf(roomRequest.getStatus()));
        room.setSex(roomRequest.getSex());

        if (roomRequest.getStatus().equals("MAINTENANCE")) {
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
}
