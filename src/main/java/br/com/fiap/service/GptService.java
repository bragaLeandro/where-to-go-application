package br.com.fiap.service;

import br.com.fiap.constants.PromptConstants;
import br.com.fiap.dto.TripCreationDto;
import br.com.fiap.dto.TripDto;
import br.com.fiap.util.MapperUtil;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class GptService {

    @Value("${openai.api.key}")
    private String apiKey;
    private OpenAiService service;

    @PostConstruct
    public void init() {
        this.service = new OpenAiService(apiKey, Duration.ofSeconds(60));
    }

    public TripDto createOpenAiTrip(TripCreationDto tripCreationDto) {
        List<ChatMessage> messages = new ArrayList<>(List.of(
                new ChatMessage(ChatMessageRole.SYSTEM.value(), PromptConstants.TRAVEL_INITIALIZER),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_FORMAT),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.OUTPUT_RULES),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.OUTPUT_EXAMPLE),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.LINE_SEPARATOR),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_CREATOR),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_CLIMATE + tripCreationDto.getClime()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_COST + tripCreationDto.getMaxCost()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_TRANSPORT + tripCreationDto.getTransport()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.MAX_DURATION + tripCreationDto.getMaxTime()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.START_DATE + tripCreationDto.getStartDate()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.END_DATE + tripCreationDto.getEndDate())
        ));

        if (null != tripCreationDto.getDestino()) {
            ChatMessage destiny = new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.DESTINY + tripCreationDto.getDestino());
            messages.add(destiny);
        }

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(900)
                .build();

        String trip = this.replaceLineSeparator(service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage().getContent());
        log.info(trip);
        return MapperUtil.jsonToEntity(trip, TripDto.class);
    }

    public String replaceLineSeparator(String text) {
        return text.replace(System.lineSeparator(), "");
    }

}
