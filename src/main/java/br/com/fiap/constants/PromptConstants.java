package br.com.fiap.constants;

public class PromptConstants {

    public static final String TRAVEL_INITIALIZER = "Você é um gerador de viagens personalizadas";

    public static final String TRAVEL_FORMAT = "Responda todas as perguntas exclusivamente no formato JSON, sem quebras de linha:" +
            "{\"destino\": \"\", \"pais\": \"\", \"atividadesPorDia\": [{\"dia\": 1, \"atividades\": [{\"nome\": \"\", \"duracao\": \"\"}]}], \"hospedagem\":\"\", \"duracaoViagem\":\"\", \"custo\": 0}";

    public static final String OUTPUT_EXAMPLE = "Você deve preencher atividades para todos os dias da viagem, mesmo que seja uma atividade de 'Descanso'. Exemplo de como deve ser o output: {\"destino\": \"Paris\", \"pais\": \"França\", \"atividadesPorDia\": [{\"dia\": 1, \"atividades\": [{\"nome\": \"Visitar Torre Eiffel\", \"duracao\": \"3 horas\"}]}, {\"dia\": 2, \"atividades\": [{\"nome\": \"Passeio pelo Rio Sena\", \"duracao\": \"2 horas\"}, {\"nome\": \"Visita ao Louvre\", \"duracao\": \"5 horas\"}]}, {\"dia\": 3, \"atividades\": [{\"nome\": \"Descanso\", \"duracao\": \"Todo o dia\"}]}, {\"dia\": 4, \"atividades\": [{\"nome\": \"Descanso\", \"duracao\": \"Todo o dia\"}]}, {\"dia\": 5, \"atividades\": [{\"nome\": \"Descanso\", \"duracao\": \"Todo o dia\"}]}, {\"dia\": 6, \"atividades\": [{\"nome\": \"Descanso\", \"duracao\": \"Todo o dia\"}]}, {\"dia\": 7, \"atividades\": [{\"nome\": \"Descanso\", \"duracao\": \"Todo o dia\"}]}], \"hospedagem\": \"Hotel Central, Quarto Duplo\", \"duracaoViagem\": \"7 dias\", \"custo\": 10000}";
    public static final String TRAVEL_CREATOR = "Crie uma viagem com as seguintes características: ";
    public static final String TRAVEL_CLIMATE = "O clima da viagem deve ser: ";
    public static final String TRAVEL_TRANSPORT = "O meio de transporte para ir ao local inicial de viagem é: ";

    public static final String LINE_SEPARATOR = "No atributo atividades, cada atividade deve vir sempre com uma quebra de linha entre uma atividade e outra";

    public static final String OUTPUT_RULES = "Você deve retornar somente o JSON, Nenhum texto dos atributos deve ter caracteres que possam estragar o formato JSON, por exemplo aspas duplas, pois vai estragar a serialização para objeto";

    public static final String MAX_DURATION = "A viagem pode ter a duração máxima em dias de: ";

    public static final String TRAVEL_COST = "O custo em reais dessa viagem deve ser no máximo de:";
}
