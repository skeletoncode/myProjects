package com.tech.tech_servise.service;

import com.tech.tech_servise.entity.ApplicationUser;
import com.tech.tech_servise.entity.Reservation;
import com.tech.tech_servise.entity.Servise;
import com.tech.tech_servise.entity.UserRole;
import com.tech.tech_servise.exceptions.AccountException;
import com.tech.tech_servise.repository.ApplicationUserRepository;
import com.tech.tech_servise.repository.ReservationRepository;
import com.tech.tech_servise.repository.ServiceRepository;
import com.tech.tech_servise.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final ApplicationUserRepository applicationUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReservationRepository reservationRepository;
    private final ServiceRepository serviceRepository;

    public AccountService(ApplicationUserRepository applicationUserRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ReservationRepository reservationRepository, ServiceRepository serviceRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.reservationRepository = reservationRepository;
        this.serviceRepository = serviceRepository;
    }

    public void registration(ApplicationUser user) throws AccountException {
        if(applicationUserRepository.existsByUsername(user.getUsername())) {
            throw new AccountException("Username is already in use");
        }
        userRoleRepository.findByRoleType(UserRole.RoleType.ROLE_USER)
                .ifPresentOrElse(user::setUserRole,
                        () -> {
                            UserRole userRole = new UserRole();
                            userRole.setRoleType(UserRole.RoleType.ROLE_USER);
                            user.setUserRole(userRole);
                            userRoleRepository.save(userRole);
                              }
                        );
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                applicationUserRepository.save(user);

    }
    public void changeProfile(ApplicationUser user, String newLogin, String newPassword, UserRole role) throws AccountException {
        if(applicationUserRepository.existsByUsername(user.getUsername())) {
            throw new AccountException("Username is do not exist");
        }
        user.setUserRole(role);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUsername(newLogin);
        applicationUserRepository.save(user);
    }

    public List<Reservation> getUserReservation(Long userId) {
        reservationRepository.findByUserId(userId);
        return reservationRepository.findByUserId(userId);
    }

    public double getDiscount(Long userId, int discount) {
        List <Reservation> reservations = reservationRepository.findByUserId(userId);
        double sum = 0.0;
        for (Reservation reservation : reservations) {
               List<Long> userServ = reservation.getIdServices();
               for (Long userServId : userServ) {
                  Optional<Servise> servicesUser = serviceRepository.findById(userServId);
                   List<Servise> servisesUs = servicesUser.stream().toList();
                   for (Servise service : servisesUs) {
                       Double price = service.getPrice();

                            sum =   sum +price*0.01 *discount;

                   }
               }
            }
        return sum;
    }




}
