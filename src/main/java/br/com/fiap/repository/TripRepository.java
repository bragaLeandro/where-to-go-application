package br.com.fiap.repository;

import br.com.fiap.entity.Trip;
import br.com.fiap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByUser(User user);
    Optional<Trip> findByUserAndId(User user, Long tripId);
}