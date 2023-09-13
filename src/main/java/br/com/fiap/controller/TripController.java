package br.com.fiap.controller;

import br.com.fiap.dto.TripCreationDto;
import br.com.fiap.dto.TripDto;
import br.com.fiap.entity.Trip;
import br.com.fiap.service.TripService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final static Logger logger = LoggerFactory.getLogger(TripController.class);

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public TripDto createRecipe(@RequestBody @Valid TripCreationDto tripCreationDto) {
        logger.info("Calling Service(GET) /recipe");
        return tripService.createTrip(tripCreationDto);
    }

    //TODO: SPRINT 4 -> GET TRIPS/ACTIVITIES BY USER
}
