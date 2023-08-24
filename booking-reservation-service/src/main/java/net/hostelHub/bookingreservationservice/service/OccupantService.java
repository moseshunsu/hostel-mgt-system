package net.hostelHub.bookingreservationservice.service;

import net.hostelHub.bookingreservationservice.dto.OccupantRequest;
import net.hostelHub.bookingreservationservice.dto.Response;
import org.springframework.http.ResponseEntity;

public interface OccupantService {
    ResponseEntity<Response> updateDetails(OccupantRequest occupantRequest);
}
