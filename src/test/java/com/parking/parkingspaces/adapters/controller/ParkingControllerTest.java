package com.parking.parkingspaces.adapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.parkingspaces.DummyData;
import com.parking.parkingspaces.application.port.in.parking.*;
import com.parking.parkingspaces.config.exception.DataNotFoundException;
import com.parking.parkingspaces.config.exception.ParkingCreationLimitException;
import com.parking.parkingspaces.config.exception.ParkingSpaceCustomErrorException;
import com.parking.parkingspaces.config.utility.Constants;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
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
    ModelMapper modelMapper;
    @MockBean
    CreateParkingCommand createParkingCommand;
    @MockBean
    UpdateParkingCommandService updateParkingCommandService;
    @MockBean
    GetParkingQueryService getParkingQueryService;
    @MockBean
    GetParkingsQueryService getParkingsQueryService;
    @MockBean
    DeleteParkingCommandService deleteParkingCommandService;

    private ParkingController parkingController;

    static final String API_V1_PARKING = "/api/v1/parking/";

    @Test
    void createParkingOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        mockMvc.perform(post(API_V1_PARKING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(Constants.MSG_CREATE_PARKING_OK)));
    }

    @Test
    void createParkingArgumentNotValidExceptionTest() throws Exception {
        mockMvc.perform(post(API_V1_PARKING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTOWithNullField)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(Constants.FIELDS_REQUIRED)));
    }

    @Test
    void createParkingIllegalArgumentExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        doThrow(new IllegalArgumentException(Constants.INTERNAL_SERVER_ERROR)).when(createParkingCommand).execute(any());
        mockMvc.perform(post(API_V1_PARKING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is(Constants.INTERNAL_SERVER_ERROR)));
    }

    @Test
    void createParkingParkingCreationLimitExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        doThrow(new ParkingCreationLimitException(Constants.MSG_CREATION_LIMIT_PARKING)).when(createParkingCommand).execute(any());
        mockMvc.perform(post(API_V1_PARKING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", is(Constants.MSG_CREATION_LIMIT_PARKING)));
    }

    @Test
    void getParkingOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingDTO);

        mockMvc.perform(get(API_V1_PARKING + 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void getParkingDataNotFoundExceptionTest() throws Exception {
        when(getParkingQueryService.execute(anyInt())).thenThrow(DataNotFoundException.class);
        mockMvc.perform(get(API_V1_PARKING + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(Constants.DATA_NOT_FOUND_EXCEPTION)));
    }

    @Test
    void updateParkingOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        mockMvc.perform(put(API_V1_PARKING + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_UPDATE_PARKING_OK)));
    }

    @Test
    void updateParkingDataNotFoundExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        doThrow(new DataNotFoundException(Constants.DATA_NOT_FOUND_EXCEPTION)).when(updateParkingCommandService).execute(anyInt(), any());
        mockMvc.perform(put(API_V1_PARKING + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(Constants.DATA_NOT_FOUND_EXCEPTION)));
    }

    @Test
    void deleteParkingOkTest() throws Exception {
        mockMvc.perform(delete(API_V1_PARKING + 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_DELETE_PARKING_OK)));
    }

    @Test
    void deleteParkingDataNotFoundExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        doThrow(new DataNotFoundException(Constants.DATA_NOT_FOUND_EXCEPTION)).when(deleteParkingCommandService).execute(anyInt());
        mockMvc.perform(delete(API_V1_PARKING + 1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(Constants.DATA_NOT_FOUND_EXCEPTION)));
    }

    @Test
    void deleteParkingParkingCustomErrorExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        doThrow(new ParkingSpaceCustomErrorException("Custom Exception", null)).when(deleteParkingCommandService).execute(anyInt());
        mockMvc.perform(delete(API_V1_PARKING + 1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Custom Exception")));
    }
}