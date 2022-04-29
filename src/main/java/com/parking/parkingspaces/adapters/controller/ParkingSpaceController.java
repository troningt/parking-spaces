package com.parking.parkingspaces.adapters.controller;

import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceBusyDTO;
import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceDTO;
import com.parking.parkingspaces.adapters.controller.dto.Response;
import com.parking.parkingspaces.application.port.in.parkingspace.*;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.LiberateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.UseParkingSpaceCommandService;
import com.parking.parkingspaces.domain.ParkingSpace;
import com.parking.parkingspaces.domain.ParkingSpaceBusy;
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

import static com.parking.parkingspaces.config.utility.Constants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/parking/space")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ParkingSpaceController {
    private final ModelMapper                           modelMapper;
    private final CreateParkingSpaceCommand             createParkingSpaceCommand;
    private final UpdateParkingSpaceCommandService      updateParkingSpaceCommandService;
    private final GetParkingSpaceQueryService           getParkingSpaceQueryService;
    private final GetParkingSpacesQueryService          getParkingSpacesQueryService;
    private final GetParkingSpacesFreeQueryService      getParkingSpacesFreeQueryService;
    private final DeleteParkingSpaceCommandService      deleteParkingSpaceCommandService;
    private final UseParkingSpaceCommandService         useParkingSpaceCommandService;
    private final LiberateParkingSpaceCommandService    liberateParkingSpaceCommandService;

    @Operation(
            summary = "Create a new parking space",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Parking space created successfully"),
                    @ApiResponse(responseCode = "400", description = "The body is malformed")
            }
    )
    @PostMapping
    public ResponseEntity<Response> createParkingSpace(@Valid @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        createParkingSpaceCommand.execute(modelMapper.map(parkingSpaceDTO, ParkingSpace.ParkingSpaceBuilder.class).build());
        return new ResponseEntity<>(Response.builder().message(MSG_CREATE_OK).build(), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a parking space by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return parking space found by id"),
                    @ApiResponse(responseCode = "404", description = "The parking space not found"),
                    @ApiResponse(responseCode = "400", description = "The body is malformed")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpaceDTO> getParkingSpace(@Valid @PathVariable int id) {
        return new ResponseEntity<>(modelMapper.map(getParkingSpaceQueryService.execute(id), ParkingSpaceDTO.class), HttpStatus.OK);
    }

    @Operation(
            summary = "Get all parking spaces",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return a list of all parking spaces")
            }
    )
    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpaces() {
        return new ResponseEntity<>(getParkingSpacesQueryService.execute().stream()
                .map(parkingSpace -> modelMapper.map(parkingSpace, ParkingSpaceDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(
            summary = "Get parking spaces free",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return a list of all parking spaces free")
            }
    )
    @GetMapping("/free")
    public ResponseEntity<List<ParkingSpaceDTO>> getParkingSpacesNoBusy() {
        return new ResponseEntity<>(getParkingSpacesFreeQueryService.execute().stream()
                .map(parkingSpace -> modelMapper.map(parkingSpace, ParkingSpaceDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(
            summary = "Update parking space",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The parking space has updated successfully"),
                    @ApiResponse(responseCode = "404", description = "The parking space not exists"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateParkingSpace(@Valid @PathVariable int id, @Valid @RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        updateParkingSpaceCommandService.execute(id, modelMapper.map(parkingSpaceDTO, ParkingSpace.ParkingSpaceBuilder.class).build());
        return new ResponseEntity<>(Response.builder().message(MSG_UPDATE_OK).build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Update parking space",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The parking space has deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "The parking space not exists"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteParkingSpace(@Valid @PathVariable int id) {
        deleteParkingSpaceCommandService.execute(id);
        return new ResponseEntity<>(Response.builder().message(MSG_DELETE_OK).build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Use a  parking space",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The status parking space is toggle to use"),
                    @ApiResponse(responseCode = "404", description = "The parking space not exists"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/use")
    public ResponseEntity<Response> useParkingSpace(@Valid @RequestBody ParkingSpaceBusyDTO parkingSpaceBusyDTO) {
        useParkingSpaceCommandService.execute(modelMapper.map(parkingSpaceBusyDTO, ParkingSpaceBusy.class));
        return new ResponseEntity<>(Response.builder().message(MSG_USE_PARKING_SPACE).build(), HttpStatus.OK);
    }

    @Operation(
            summary = "Liberate a  parking space",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The status parking space is toggle to free"),
                    @ApiResponse(responseCode = "404", description = "The parking space not exists"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PutMapping("/liberate")
    public ResponseEntity<Response> liberateParkingSpace(@RequestBody String id) {
        liberateParkingSpaceCommandService.execute(id);
        return new ResponseEntity<>(Response.builder().message(MSG_LIBERATE_PARKING_SPACE).build(), HttpStatus.OK);
    }
}
