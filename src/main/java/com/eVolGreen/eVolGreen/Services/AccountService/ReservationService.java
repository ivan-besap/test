package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.ReservationDTO.ReservationClientDTO;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;

import java.util.List;

public interface ReservationService {
    void saveReservation(Reservation reservation);

    List<ReservationClientDTO> getReservationsDTO();
}
