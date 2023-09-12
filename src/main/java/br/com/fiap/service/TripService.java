package br.com.fiap.service;

import br.com.fiap.dto.TripCreationDto;
import br.com.fiap.dto.TripDto;
import br.com.fiap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    private final GptService gptService;

    @Autowired
    public TripService(GptService gptService) {
        this.gptService = gptService;
    }

    public TripDto createTrip(TripCreationDto tripCreationDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        TripDto trip = gptService.createOpenAiTrip(tripCreationDto);

//        this.saveTrip(trip, user.getId());
        return trip;
    }
}
