package com.parking.parkingspaces.adapters.controller;

import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceBusyDTO;
import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceDTO;
import com.parking.parkingspaces.application.port.in.parkingspace.*;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.LiberateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.UseParkingSpaceCommandService;
import com.parking.parkingspaces.domain.ParkingSpace;
import com.parking.parkingspaces.domain.ParkingSpaceBusy;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.parking.parkingspaces.config.utility.Constants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/parking/space")
public class ParkingSpaceController {
    private final ModelMapper                           modelMapper;
    private final CreateParkingSpaceCommandService      createParkingSpaceCommandService;
    private final UpdateParkingSpaceCommandService      updateParkingSpaceCommandService;
    private final GetParkingSpaceQueryService           getParkingSpaceQueryService;
    private final GetParkingSpacesQueryService          getParkingSpacesQueryService;
    private final GetParkingSpacesFreeQueryService      getParkingSpacesFreeQueryService;
    private final DeleteParkingSpaceCommandService      deleteParkingSpaceCommandService;
    private final UseParkingSpaceCommandService useParkingSpaceCommandService;
    private final LiberateParkingSpaceCommandService liberateParkingSpaceCommandService;

    @PostMapping
    public ResponseEntity<String> createParkingSpace (@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        createParkingSpaceCommandService.execute(modelMapper.map(parkingSpaceDTO, ParkingSpace.class));
        return new ResponseEntity<>(MSG_CREATE_OK, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpace(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper.map(getParkingSpaceQueryService.execute(id), ParkingSpaceDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpaces() {
        return new ResponseEntity<>(getParkingSpacesQueryService.execute().stream()
                .map(parkingSpace -> modelMapper.map(parkingSpace, ParkingSpaceDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesNoBusy() {
        return new ResponseEntity<>(getParkingSpacesFreeQueryService.execute().stream()
                .map(parkingSpace -> modelMapper.map(parkingSpace, ParkingSpaceDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateParkingSpace(@PathVariable int id, @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        updateParkingSpaceCommandService.execute(id, modelMapper.map(parkingSpaceDTO, ParkingSpace.class));
        return new ResponseEntity<>(MSG_UPDATE_OK, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParkingSpace(@PathVariable int id) {
        deleteParkingSpaceCommandService.execute(id);
        return new ResponseEntity<>(MSG_DELETE_OK, HttpStatus.OK);
    }

    @PutMapping("/use")
    public ResponseEntity<String> useParkingSpace(@RequestBody ParkingSpaceBusyDTO parkingSpaceBusyDTO) {
        useParkingSpaceCommandService.execute(modelMapper.map(parkingSpaceBusyDTO, ParkingSpaceBusy.class));
        return new ResponseEntity<>(MSG_USE_PARKING_SPACE, HttpStatus.OK);
    }

    @PutMapping("/liberate")
    public ResponseEntity<String> liberateParkingSpace(@RequestBody String id) {
        liberateParkingSpaceCommandService.execute(id);
        return new ResponseEntity<>(MSG_LIBERATE_PARKING_SPACE, HttpStatus.OK);
    }
}
