package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO.CarCompanyDTO;
import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;

import java.util.List;

public interface CarService {

//    List<CarDTO> getCarsDTO();

    void saveCar(Car car);

    Car findById(Long id);

    void deleteCar(Long id);

    List<CarCompanyDTO> getCarsCompanyDTO();

    CarCompanyDTO getCardCompanyDTO (Long id);

    List<CarCompanyDTO> getCarsCompanyDTOByCompany(CompanyUser company);
}
