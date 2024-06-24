package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.ReservationDTO;
import com.eVolGreen.eVolGreen.Models.Reservation;

import java.util.List;

public interface ReservationService {
    void saveReservation(Reservation reservation);

    List<ReservationDTO> getReservationsDTO();
}
