package com.parking.parkingspaces.adapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parking.parkingspaces.DummyData;
import com.parking.parkingspaces.application.port.in.parkingspace.*;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.LiberateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.in.parkingspacebusy.UseParkingSpaceCommandService;
import com.parking.parkingspaces.config.exception.DataNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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

    static final String API_V1_PARKING_SPACE = "/api/v1/parking/space/";

    @Test
    void createParkingSpaceOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingSpace);

        mockMvc.perform(post(API_V1_PARKING_SPACE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingSpaceDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(Constants.MSG_CREATE_PARKING_SPACE_OK)));
    }

    @Test
    void createParkingSpaceArgumentNotValidExceptionTest() throws Exception {
        mockMvc.perform(post(API_V1_PARKING_SPACE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingSpaceDTOWithNullField)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(Constants.FIELDS_REQUIRED)));
    }

    @Test
    void createParkingSpaceIllegalArgumentExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingSpace);
        doThrow(new IllegalArgumentException(Constants.INTERNAL_SERVER_ERROR)).when(createParkingSpaceCommand).execute(any());
        mockMvc.perform(post(API_V1_PARKING_SPACE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingDTO)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is(Constants.INTERNAL_SERVER_ERROR)));
    }

    @Test
    void getParkingSpaceOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingSpaceDTO);

        mockMvc.perform(get(API_V1_PARKING_SPACE + 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void getParkingSpaceDataNotFoundExceptionTest() throws Exception {
        when(getParkingSpaceQueryService.execute(anyInt())).thenThrow(DataNotFoundException.class);
        mockMvc.perform(get(API_V1_PARKING_SPACE + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingSpaceDTO)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(Constants.DATA_NOT_FOUND_EXCEPTION)));
    }

    @Test
    void updateParkingSpaceOkTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingSpace);
        mockMvc.perform(put(API_V1_PARKING_SPACE + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingSpaceDTO)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_UPDATE_PARKING_SPACE_OK)));
    }

    @Test
    void updateParkingSpaceDataNotFoundExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingSpace);
        doThrow(new DataNotFoundException(Constants.DATA_NOT_FOUND_EXCEPTION)).when(updateParkingSpaceCommandService).execute(anyInt(), any());
        mockMvc.perform(put(API_V1_PARKING_SPACE + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DummyData.parkingSpaceDTO)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(Constants.DATA_NOT_FOUND_EXCEPTION)));
    }

    @Test
    void deleteParkingSpaceOkTest() throws Exception {
        mockMvc.perform(delete(API_V1_PARKING_SPACE + 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(Constants.MSG_DELETE_PARKING_SPACE_OK)));
    }

    @Test
    void deleteParkingSpaceDataNotFoundExceptionTest() throws Exception {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parkingSpace);
        doThrow(new DataNotFoundException(Constants.DATA_NOT_FOUND_EXCEPTION)).when(deleteParkingSpaceCommandService).execute(anyInt());
        mockMvc.perform(delete(API_V1_PARKING_SPACE + 1))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(Constants.DATA_NOT_FOUND_EXCEPTION)));
    }

}