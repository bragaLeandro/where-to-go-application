package br.com.fiap.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GptService {

    //TODO: Find a way to put this into a environment variable
    private final OpenAiService service = new OpenAiService("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");

    public String sendMessageGpt(String message) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Responda à pergunta: " + message)
                .maxTokens(2000)
                .model("text-davinci-003")
                .build();

       return service.createCompletion(completionRequest).getChoices().get(0).getText();
    }
}
