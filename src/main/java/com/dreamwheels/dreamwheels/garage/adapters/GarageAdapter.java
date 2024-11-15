package com.dreamwheels.dreamwheels.garage.adapters;

import com.dreamwheels.dreamwheels.comments.adapters.CommentAdapter;
import com.dreamwheels.dreamwheels.configuration.adapters.EntityAdapter;
import com.dreamwheels.dreamwheels.garage.dtos.GarageDto;
import com.dreamwheels.dreamwheels.garage.dtos.MotorbikeDto;
import com.dreamwheels.dreamwheels.garage.dtos.VehicleDto;
import com.dreamwheels.dreamwheels.garage.entity.Garage;
import com.dreamwheels.dreamwheels.garage.entity.Motorbike;
import com.dreamwheels.dreamwheels.garage.entity.Vehicle;
import com.dreamwheels.dreamwheels.uploaded_files.adapters.UploadedFileAdapter;
import com.dreamwheels.dreamwheels.users.dtos.UserDto;
import com.dreamwheels.dreamwheels.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GarageAdapter implements EntityAdapter<Garage, GarageDto> {

    private final UploadedFileAdapter uploadedFileAdapter;

    private final CommentAdapter commentAdapter;

    public GarageAdapter(UploadedFileAdapter uploadedFileAdapter, CommentAdapter commentAdapter) {
        this.uploadedFileAdapter = uploadedFileAdapter;
        this.commentAdapter = commentAdapter;
    }

    @Override
    public GarageDto toBusiness(Garage garage) {
        GarageDto garageDto = null;
        if (garage instanceof Vehicle vehicle){
            garageDto = vehicleGarageDto(vehicle);
            buildGarageDto(garageDto, garage);
        } else if (garage instanceof Motorbike motorbike) {
            garageDto = motorbikeGarageDto(motorbike);
            buildGarageDto(garageDto, garage);
        }else{
            return null;
        }
        return garageDto;
    }

    private GarageDto motorbikeGarageDto(Motorbike motorbike) {
        return MotorbikeDto.builder()
                .motorbikeCategory(motorbike.getMotorbikeCategory())
                .motorbikeMake(motorbike.getMotorbikeMake())
                .motorbikeModel(motorbike.getMotorbikeModel())
                .build();
    }


    private GarageDto vehicleGarageDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .vehicleModel(vehicle.getVehicleModel())
                .bodyType(vehicle.getBodyType())
                .driveTrain(vehicle.getDriveTrain())
                .engineLayout(vehicle.getEngineLayout())
                .enginePosition(vehicle.getEnginePosition())
                .vehicleMake(vehicle.getVehicleMake())
                .build();
    }

    private void buildGarageDto(GarageDto garageDto, Garage garage) {
        garageDto.setAcceleration(garage.getAcceleration());
        garageDto.setId(garage.getId());
        garageDto.setCategory(garage.getCategory());
        garageDto.setDescription(garage.getDescription());
        garageDto.setBuyingPrice(garage.getBuyingPrice());
        garageDto.setEnginePower(garage.getEnginePower());
        garageDto.setCommentsCount(garage.getCommentsCount());
        garageDto.setName(garage.getName());
        garageDto.setMileage(garage.getMileage());
        garageDto.setEngineAspiration(garage.getEngineAspiration());
        garageDto.setLikesCount(garage.getLikesCount());
        garageDto.setTorque(garage.getTorque());
        garageDto.setFuelType(garage.getFuelType());
        garageDto.setTransmissionType(garage.getTransmissionType());
        garageDto.setCreatedAt(garage.getCreatedAt());
        garageDto.setUpdatedAt(garage.getUpdatedAt());
        garageDto.setUser(garage.getUser() != null ? mapToUserDto(garage.getUser()) : null);
        garageDto.setGarageFiles(garage.getGarageFiles().stream().map(uploadedFileAdapter::toBusiness).toList());
        garageDto.setPreviousOwnersCount(garage.getPreviousOwnersCount());
        garageDto.setComments(garage.getComments().stream().map(commentAdapter::toBusiness).toList());
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
