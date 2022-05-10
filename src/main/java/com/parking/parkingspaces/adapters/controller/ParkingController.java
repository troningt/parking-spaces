package com.parking.parkingspaces.adapters.controller;

import com.parking.parkingspaces.adapters.controller.dto.ParkingDTO;
import com.parking.parkingspaces.adapters.controller.dto.Response;
import com.parking.parkingspaces.application.port.in.parking.*;
import com.parking.parkingspaces.config.utility.Constants;
import com.parking.parkingspaces.domain.Parking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/parking")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ParkingController {
    private final ModelMapper                   modelMapper;
    private final CreateParkingCommand          createParkingCommand;
    private final UpdateParkingCommandService   updateParkingCommandService;
    private final GetParkingQueryService        getParkingQueryService;
    private final GetParkingsQueryService       getParkingsQueryService;
    private final DeleteParkingCommandService   deleteParkingCommandService;

    @Operation(
            summary = "Create a new parking",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Parking created successfully"),
                    @ApiResponse(responseCode = "409", description = ""),
                    @ApiResponse(responseCode = "400", description = "The body is malformed")
            }
    )
    @PostMapping
    public ResponseEntity<Response> createParking(@Valid @RequestBody ParkingDTO parkingDTO) {
        createParkingCommand.execute(modelMapper.map(parkingDTO, Parking.ParkingBuilder.class).build());
        return new ResponseEntity<>(Response.builder().message(Constants.MSG_CREATE_PARKING_OK).build(), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get info of parking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return info of parking"),
                    @ApiResponse(responseCode = "404", description = "The parking isn't created yet"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> getParking(@Valid @PathVariable int id) {
        return new ResponseEntity<>(modelMapper.map(getParkingQueryService.execute(id), ParkingDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> getParkingList() {
        return new ResponseEntity<>(getParkingsQueryService.execute().stream()
                .map(parking -> modelMapper.map(parking, ParkingDTO.class))
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(
            summary = "Update info of parking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parking is updated successfully"),
                    @ApiResponse(responseCode = "404", description = "The parking isn't created yet"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateParking(@Valid @PathVariable int id, @RequestBody ParkingDTO parkingDTO) {
        updateParkingCommandService.execute(id, modelMapper.map(parkingDTO, Parking.ParkingBuilder.class).build());
        return new ResponseEntity<>(Response.builder().message(Constants.MSG_UPDATE_PARKING_OK).build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete parking in system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parking is deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "The parking isn't created yet"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteParking(@Valid @PathVariable int id) {
        deleteParkingCommandService.execute(id);
        return new ResponseEntity<>(Response.builder().message(Constants.MSG_DELETE_PARKING_OK).build(), HttpStatus.OK);
    }
}
