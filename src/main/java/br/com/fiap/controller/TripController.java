package br.com.fiap.controller;

import br.com.fiap.dto.TripCreationDto;
import br.com.fiap.dto.TripDto;
import br.com.fiap.entity.Trip;
import br.com.fiap.entity.User;
import br.com.fiap.service.TripService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final static Logger logger = LoggerFactory.getLogger(TripController.class);

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<?> createTrip(@RequestBody @Valid TripCreationDto tripCreationDto) {
        logger.info("Calling Service(POST) /trip");
        try {
            return ResponseEntity.ok(tripService.createTrip(tripCreationDto));
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping
    public List<TripDto> findByUser() {
        logger.info("Calling Service(GET) /trip");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return tripService.findByUserId(user.getId());
    }

    @GetMapping("/all")
    public List<TripDto> findByAll() {
        logger.info("Calling Service(GET) /trip");
        return tripService.findAll();
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDto> findByUserAndTripId(@PathVariable Long tripId) {
        logger.info("Calling Service(GET) /trip/{}", tripId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            return ResponseEntity.ok(tripService.findByUserAndTripId(user.getId(), tripId));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long tripId) {
        logger.info("Calling Service(DELETE) /trip/{}", tripId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        tripService.deleteById(tripId);
        return ResponseEntity.ok().build();
    }
}
