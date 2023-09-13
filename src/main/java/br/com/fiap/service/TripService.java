package br.com.fiap.service;

import br.com.fiap.dto.TripCreationDto;
import br.com.fiap.dto.TripDto;
import br.com.fiap.entity.Activity;
import br.com.fiap.entity.Trip;
import br.com.fiap.entity.User;
import br.com.fiap.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {

    private final GptService gptService;
    private final UserService userService;
    private final TripRepository tripRepository;

    @Autowired
    public TripService(GptService gptService,
                       UserService userService,
                       TripRepository tripRepository) {
        this.gptService = gptService;
        this.userService = userService;
        this.tripRepository = tripRepository;
    }

    public TripDto createTrip(TripCreationDto tripCreationDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        TripDto trip = gptService.createOpenAiTrip(tripCreationDto);

        this.saveTrip(trip, user.getId());
        return trip;
    }

    @Transactional
    private void saveTrip(TripDto tripDto, Long userId) {
        User user = userService.findById(userId);

        Trip trip = Trip.builder()
                .destination(tripDto.getDestination())
                .country(tripDto.getCountry())
                .accommodation(tripDto.getAccommodation())
                .travelDuration(tripDto.getTravelDuration())
                .cost(tripDto.getCost())
                .user(user)
                .build();

        List<Activity> activities = tripDto.getActivities().stream()
                .flatMap(dayActivities ->
                        dayActivities.getActivities().stream().map(activityDto ->
                                Activity.builder()
                                        .day(dayActivities.getDay())
                                        .name(activityDto.getName())
                                        .duration(activityDto.getDuration())
                                        .trip(trip)
                                        .build()
                        )
                )
                .collect(Collectors.toList());

        trip.setActivities(activities);

        tripRepository.save(trip);
    }



}
