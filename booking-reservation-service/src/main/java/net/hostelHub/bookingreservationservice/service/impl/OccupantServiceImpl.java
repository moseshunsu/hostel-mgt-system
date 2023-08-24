package net.hostelHub.bookingreservationservice.service.impl;

import net.hostelHub.bookingreservationservice.dto.Data;
import net.hostelHub.bookingreservationservice.dto.OccupantRequest;
import net.hostelHub.bookingreservationservice.dto.Response;
import net.hostelHub.bookingreservationservice.dto.User;
import net.hostelHub.bookingreservationservice.entity.Occupant;
import net.hostelHub.bookingreservationservice.repository.OccupantRepository;
import net.hostelHub.bookingreservationservice.service.OccupantService;
import net.hostelHub.bookingreservationservice.service.client.IdentityFeignClient;
import net.hostelHub.bookingreservationservice.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OccupantServiceImpl implements OccupantService {

    @Autowired
    private OccupantRepository occupantRepository;
    @Autowired
    private IdentityFeignClient identityFeignClient;

    @Override
    public ResponseEntity<Response> updateDetails(OccupantRequest occupantRequest) {
        boolean isOccupantExists = occupantRepository.existsByEmailAndUsername(occupantRequest.getEmail(),
                occupantRequest.getPhoneNumber());

        if (isOccupantExists) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .responseCode(ResponseUtils.OCCUPANT_EXISTS_CODE)
                            .responseMessage(ResponseUtils.OCCUPANT_EXISTS_MESSAGE)
                            .data(
                                    Data.builder()
                                            .occupantCode(occupantRequest.getOccupantCode())
                                            .email(occupantRequest.getEmail())
                                            .build()
                            )
                            .build()
            );
        }

        User fetchedUser = identityFeignClient.fetchUser(occupantRequest.getEmail());

        Occupant occupant = new Occupant();
        occupant.setOccupantCode(ResponseUtils.generateOccupantCode(ResponseUtils.LENGTH_OF_CLIENT_CODE));
        occupant.setEmail(fetchedUser.getEmail());
        occupant.setName(fetchedUser.getName());
        occupant.setUsername(fetchedUser.getUsername());
        occupant.setPhoneNumber(occupant.getPhoneNumber());

        Occupant savedOccupant = occupantRepository.save(occupant);

        return ResponseEntity.ok().body(
                Response.builder()
                        .responseCode(ResponseUtils.SUCCESS_CODE)
                        .responseMessage(ResponseUtils.DETAILS_ENTRY_SUCCESS)
                        .data(
                                Data.builder()
                                        .occupantCode(savedOccupant.getOccupantCode())
                                        .email(savedOccupant.getEmail())
                                        .build()
                        )
                        .build()
        );

    }
}
