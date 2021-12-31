package com.lecture.car_rental1.service;


import com.lecture.car_rental1.domain.Car;
import com.lecture.car_rental1.domain.Reservation;
import com.lecture.car_rental1.domain.User;
import com.lecture.car_rental1.repository.CarRepository;
import com.lecture.car_rental1.repository.ReservationRepository;
import com.lecture.car_rental1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@AllArgsConstructor
@Service
public class ExcelService {

    UserRepository userRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public ByteArrayInputStream loadUser() {
        List<User> users = userRepository.findAll();

        return ExcelHelper.usersExcel(users);
    }

    public ByteArrayInputStream loadCar() {
        List<Car> cars = carRepository.findAll();

        return ExcelHelper.carsExcel(cars);
    }

    public ByteArrayInputStream loadReservation() {
        List<Reservation> reservations = reservationRepository.findAll();

        return ExcelHelper.reservationsExcel(reservations);
    }
}
