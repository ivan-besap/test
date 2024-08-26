package com.eVolGreen.eVolGreen.Services.ImplementService.AccountServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.ReservationDTO.ReservationClientDTO;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Repositories.ReservationRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImplement implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationClientDTO> getReservationsDTO() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationClientDTO::new)
                .collect(Collectors.toList());
    }
}
