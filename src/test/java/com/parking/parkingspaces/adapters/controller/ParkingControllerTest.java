package com.parking.parkingspaces.adapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.parkingspaces.DummyData;
import com.parking.parkingspaces.application.port.in.parking.CreateParkingCommand;
import com.parking.parkingspaces.application.port.in.parking.DeleteParkingCommandService;
import com.parking.parkingspaces.application.port.in.parking.GetParkingQueryService;
import com.parking.parkingspaces.application.port.in.parking.UpdateParkingCommandService;
import com.parking.parkingspaces.config.utility.Constants;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ParkingController.class)
class ParkingControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ModelMapper                 modelMapper;
    @MockBean
    CreateParkingCommand        createParkingCommand;
    @MockBean
    UpdateParkingCommandService updateParkingCommandService;
    @MockBean
    GetParkingQueryService      getParkingQueryService;
    @MockBean
    DeleteParkingCommandService deleteParkingCommandService;

    @InjectMocks
    private ParkingController parkingController;

    static final String URL_PARKING = "/api/v1/parking/";

    @Test
    void createParkingOkTest() throws Exception {
        mockMvc.perform(post("/api/v1/parking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_CREATE_PARKING_OK)));
    }


    @Test
    void getParkingOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingDTO);

        mockMvc.perform(get(URL_PARKING + 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void updateParkingOkTest() throws Exception {
        mockMvc.perform(put(URL_PARKING + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_UPDATE_PARKING_OK)));
    }

    @Test
    void deleteParkingOkTest() throws Exception {
        mockMvc.perform(delete(URL_PARKING + 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_DELETE_PARKING_OK)));
    }
}