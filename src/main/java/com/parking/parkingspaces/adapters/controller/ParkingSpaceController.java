package com.parking.parkingspaces.adapters.controller;

import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceBusyDTO;
import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceDTO;
import com.parking.parkingspaces.adapters.controller.dto.Response;
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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ParkingSpaceController {
    private final ModelMapper                           modelMapper;
    private final CreateParkingSpaceCommandService      createParkingSpaceCommandService;
    private final UpdateParkingSpaceCommandService      updateParkingSpaceCommandService;
    private final GetParkingSpaceQueryService           getParkingSpaceQueryService;
    private final GetParkingSpacesQueryService          getParkingSpacesQueryService;
    private final GetParkingSpacesFreeQueryService      getParkingSpacesFreeQueryService;
    private final DeleteParkingSpaceCommandService      deleteParkingSpaceCommandService;
    private final UseParkingSpaceCommandService         useParkingSpaceCommandService;
    private final LiberateParkingSpaceCommandService    liberateParkingSpaceCommandService;

    @PostMapping
    public ResponseEntity<Response> createParkingSpace (@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        createParkingSpaceCommandService.execute(modelMapper.map(parkingSpaceDTO, ParkingSpace.class));
        return new ResponseEntity<>(Response.builder().message(MSG_CREATE_OK).build(), HttpStatus.OK);
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
    public ResponseEntity<Response> updateParkingSpace(@PathVariable int id, @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        updateParkingSpaceCommandService.execute(id, modelMapper.map(parkingSpaceDTO, ParkingSpace.class));
        return new ResponseEntity<>(Response.builder().message(MSG_UPDATE_OK).build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteParkingSpace(@PathVariable int id) {
        deleteParkingSpaceCommandService.execute(id);
        return new ResponseEntity<>(Response.builder().message(MSG_DELETE_OK).build(), HttpStatus.OK);
    }

    @PutMapping("/use")
    public ResponseEntity<Response> useParkingSpace(@RequestBody ParkingSpaceBusyDTO parkingSpaceBusyDTO) {
        useParkingSpaceCommandService.execute(modelMapper.map(parkingSpaceBusyDTO, ParkingSpaceBusy.class));
        return new ResponseEntity<>(Response.builder().message(MSG_USE_PARKING_SPACE).build(), HttpStatus.OK);
    }

    @PutMapping("/liberate")
    public ResponseEntity<Response> liberateParkingSpace(@RequestBody String id) {
        liberateParkingSpaceCommandService.execute(id);
        return new ResponseEntity<>(Response.builder().message(MSG_LIBERATE_PARKING_SPACE).build(), HttpStatus.OK);
    }
}
