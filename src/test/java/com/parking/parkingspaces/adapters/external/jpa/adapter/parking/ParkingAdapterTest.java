package com.parking.parkingspaces.adapters.external.jpa.adapter.parking;

import com.parking.parkingspaces.DummyData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingAdapterTest {
    @Mock
    ModelMapper modelMapper;
    @Mock
    ParkingPersistence parkingRepository;

    @InjectMocks
    ParkingAdapter parkingAdapter;

    @Test
    void createOkTest() {
        when(parkingRepository.save(any())).thenReturn(DummyData.parkingEntity().get(0));
        parkingAdapter.create(DummyData.parking.build());
        verify(parkingRepository, times(1)).save(any());
    }

    @Test
    void findOkTest() {
        when(modelMapper.map(any(), any())).thenReturn(DummyData.parking);
        when(parkingRepository.findById(any())).thenReturn(Optional.of(DummyData.parkingEntity().get(0)));
        assertNotNull(parkingAdapter.find(1));
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}