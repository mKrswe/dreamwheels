package com.dreamwheels.dreamwheels.garage.service;

import com.dreamwheels.dreamwheels.configuration.responses.CustomPageResponse;
import com.dreamwheels.dreamwheels.garage.dtos.GarageDto;
import com.dreamwheels.dreamwheels.garage.models.MotorbikeGarageModel;
import com.dreamwheels.dreamwheels.garage.models.VehicleGarageModel;
import jakarta.servlet.http.HttpServletRequest;

public interface GarageService {
    GarageDto addVehicleGarage(VehicleGarageModel vehicleGarageDto, HttpServletRequest httpRequest);

    GarageDto addMotorbikeGarage(MotorbikeGarageModel motorbikeGarageDto, HttpServletRequest httpServletRequest);

    CustomPageResponse<GarageDto> allGarages(int pageNumber);

    GarageDto getGarageById(String id);

    GarageDto updateVehicleGarage(VehicleGarageModel vehicleGarageDto, String id, HttpServletRequest httpRequest);

    GarageDto updateMotorbikeGarage(MotorbikeGarageModel motorbikeGarageDto, String id, HttpServletRequest httpServletRequest);

    CustomPageResponse<GarageDto> garagesByCategory(String category, Integer pageNumber);

    CustomPageResponse<GarageDto> searchGarages(Integer pageNumber, String query, String vehicleMake, String vehicleModel, Integer mileage, Integer previousOwnersCount, Integer enginePower, Integer topSpeed, Integer acceleration, String transmissionType, String driveTrain, String enginePosition, String engineLayout, String engineAspiration, String bodyType);

    void deleteGarage(String id);

    CustomPageResponse<GarageDto> searchMotorbikeGarages(Integer pageNumber, String name, String motorbikeMake, String motorbikeModel, String motorbikeCategory1, Integer mileage, Integer previousOwnersCount, Integer enginePower, Integer topSpeed, Integer acceleration, String transmissionType, String engineAspiration, String engineLayout);
}
