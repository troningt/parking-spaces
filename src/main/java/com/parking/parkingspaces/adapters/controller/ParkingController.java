package com.parking.parkingspaces.adapters.controller;

import com.parking.parkingspaces.adapters.controller.dto.ParkingDTO;
import com.parking.parkingspaces.application.port.in.parking.CreateParkingCommandService;
import com.parking.parkingspaces.application.port.in.parking.DeleteParkingCommandService;
import com.parking.parkingspaces.application.port.in.parking.GetParkingQueryService;
import com.parking.parkingspaces.application.port.in.parking.UpdateParkingCommandService;
import com.parking.parkingspaces.domain.Parking;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.parking.parkingspaces.config.utility.Constants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/parking")
public class ParkingController {
    private final ModelMapper                   modelMapper;
    private final CreateParkingCommandService   createParkingCommandService;
    private final UpdateParkingCommandService   updateParkingCommandService;
    private final GetParkingQueryService        getParkingQueryService;
    private final DeleteParkingCommandService   deleteParkingCommandService;

    @PostMapping
    public ResponseEntity<String> createParking(@RequestBody ParkingDTO parkingDTO) {
        createParkingCommandService.execute(modelMapper.map(parkingDTO, Parking.class));
        return new ResponseEntity<>(MSG_CREATE_PARKING_OK, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> getParking(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper.map(getParkingQueryService.execute(id), ParkingDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateParking(@PathVariable int id, @RequestBody ParkingDTO parkingDTO) {
        updateParkingCommandService.execute(id, modelMapper.map(parkingDTO, Parking.class));
        return new ResponseEntity<>(MSG_UPDATE_PARKING_OK, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParking(@PathVariable int id) {
        deleteParkingCommandService.execute(id);
        return new ResponseEntity<>(MSG_DELETE_PARKING_OK, HttpStatus.OK);
    }
}
