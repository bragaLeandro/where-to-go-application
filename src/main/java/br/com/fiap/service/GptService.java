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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
public class GptService {

    //TODO: Find a way to put this into a environment variable
    private final OpenAiService service = new OpenAiService("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", Duration.ofSeconds(60));

    public TripDto createOpenAiTrip(TripCreationDto tripCreationDto) {
        List<ChatMessage> messages = Arrays.asList(
                new ChatMessage(ChatMessageRole.SYSTEM.value(), PromptConstants.TRAVEL_INITIALIZER),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_FORMAT),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.OUTPUT_RULES),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.OUTPUT_EXAMPLE),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.LINE_SEPARATOR),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_CREATOR),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_CLIMATE + tripCreationDto.getClime()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_COST + tripCreationDto.getMaxCost()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.TRAVEL_TRANSPORT + tripCreationDto.getTransport()),
                new ChatMessage(ChatMessageRole.USER.value(), PromptConstants.MAX_DURATION + tripCreationDto.getMaxTime())
        );

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .n(1)
                .maxTokens(900)
                .build();

        String trip = this.replaceLineSeparator(service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage().getContent());
        System.out.println(tripCreationDto);

        System.out.println(trip);
        return MapperUtil.jsonToEntity(trip, TripDto.class);
    }

    public String replaceLineSeparator(String text) {
        return text.replace(System.lineSeparator(), "");
    }

}
