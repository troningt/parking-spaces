package com.parking.parkingspaces.adapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.parkingspaces.DummyData;
import com.parking.parkingspaces.application.port.in.parkingspace.*;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.LiberateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.UseParkingSpaceCommandService;
import com.parking.parkingspaces.config.utility.Constants;
import com.parking.parkingspaces.domain.ParkingSpace;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ParkingSpaceController.class)
class ParkingSpaceControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ModelMapper modelMapper;
    @MockBean
    CreateParkingSpaceCommand createParkingSpaceCommand;
    @MockBean
    UpdateParkingSpaceCommandService updateParkingSpaceCommandService;
    @MockBean
    GetParkingSpaceQueryService getParkingSpaceQueryService;
    @MockBean
    GetParkingSpacesQueryService getParkingSpacesQueryService;
    @MockBean
    GetParkingSpacesFreeQueryService getParkingSpacesFreeQueryService;
    @MockBean
    DeleteParkingSpaceCommandService      deleteParkingSpaceCommandService;
    @MockBean
    UseParkingSpaceCommandService useParkingSpaceCommandService;
    @MockBean
    LiberateParkingSpaceCommandService liberateParkingSpaceCommandService;

    @InjectMocks
    private ParkingSpaceController parkingSpaceController;

    static final String URL_PARKING = "/api/v1/parking/space/";

    @Test
    void createParkingSpaceOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(any(ParkingSpace.class));

        mockMvc.perform(post(URL_PARKING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingSpaceDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_CREATE_OK)));
    }
}