package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.ReservationDTO;
import com.eVolGreen.eVolGreen.Models.Reservation;
import com.eVolGreen.eVolGreen.Repositories.ReservationRepository;
import com.eVolGreen.eVolGreen.Services.ReservationService;
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
    public List<ReservationDTO> getReservationsDTO() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }
}
