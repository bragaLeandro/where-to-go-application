# Where2Go

## 1. Introdução

Conforme apresentado na Sprint 1, Where2Go é um aplicativo de planejamento de viagens que usa a tecnologia de IA para criar itinerários personalizados para seus usuários. A solução é construída em Java, usando o Spring Boot como framework de backend e baseada na arquitetura RESTful.

## 2. Endpoints

### 2.1 AuthController

- **Endpoint:** POST /login
- **Descrição:** Este endpoint é utilizado para autenticar o usuário.
- **Corpo da Solicitação:** O corpo da solicitação deve ser um JSON contendo o login e a senha do usuário.
  
  **Requisição:**
  ```json
  {
    "login": "neurotrix@example.com",
    "password": "teste123"
  }
  ```
  
**Resposta:** Caso a autenticação seja um sucesso, é retornado um JWT de autenticação com validade de 30 minutos.

### 2.2 TripController

- **Endpoint:** POST /trip
- **Descrição:** Este endpoint é utilizado para a aplicação gerar uma viagem de acordo com inputs de usuário.
- **Corpo da Solicitação:** O corpo da solicitação deve ser um JSON contendo a as preferencias do usuário.

 **Requisição:**
  ```json
  {
  "clima": "quente",
  "transporte": "avião",
  "tempoMaximo": 7,
  "custoMaximo": 10000.00
}
  ```

**Resposta:** Em caso de sucesso, é retornada uma viagem personalizada, exemplo:
  ```json
{
    "destino": "Rio de Janeiro",
    "pais": "Brasil",
    "atividadesPorDia": [
        {
            "dia": 1,
            "atividades": [
                {
                    "nome": "Visitar o Cristo Redentor",
                    "duracao": "2 horas"
                },
                {
                    "nome": "Passear pela praia de Copacabana",
                    "duracao": "3 horas"
                }
            ]
        },
        {
            "dia": 2,
            "atividades": [
                {
                    "nome": "Fazer um passeio de barco na Baía de Guanabara",
                    "duracao": "4 horas"
                },
                {
                    "nome": "Conhecer o Jardim Botânico",
                    "duracao": "2 horas"
                }
            ]
        },
        {
            "dia": 3,
            "atividades": [
                {
                    "nome": "Fazer uma trilha no Parque Nacional da Tijuca",
                    "duracao": "4 horas"
                },
                {
                    "nome": "Visitar o Museu do Amanhã",
                    "duracao": "3 horas"
                }
            ]
        },
        {
            "dia": 4,
            "atividades": [
                {
                    "nome": "Relaxar na praia de Ipanema",
                    "duracao": "4 horas"
                },
                {
                    "nome": "Conhecer o Pão de Açúcar",
                    "duracao": "2 horas"
                }
            ]
        },
        {
            "dia": 5,
            "atividades": [
                {
                    "nome": "Fazer um tour pela Lapa",
                    "duracao": "3 horas"
                },
                {
                    "nome": "Praticar esportes na praia da Barra da Tijuca",
                    "duracao": "4 horas"
                }
            ]
        },
        {
            "dia": 6,
            "atividades": [
                {
                    "nome": "Visitar o Maracanã",
                    "duracao": "2 horas"
                },
                {
                    "nome": "Conhecer o Museu Nacional de Belas Artes",
                    "duracao": "3 horas"
                }
            ]
        },
        {
            "dia": 7,
            "atividades": [
                {
                    "nome": "Fazer um passeio de helicóptero pela cidade",
                    "duracao": "1 hora"
                },
                {
                    "nome": "Apreciar o pôr do sol no Arpoador",
                    "duracao": "2 horas"
                }
            ]
        }
    ],
    "hospedagem": "Hotel Copacabana, Quarto Duplo",
    "duracaoViagem": "7 dias",
    "custo": 10000
}
  ```
- **Endpoint:** GET /message
- **Descrição:** Este endpoint é utilizado para obter todas as mensagens enviadas pelo usuário atualmente autenticado.

 **Requisição:**
  ```URL
   GET http://localhost:8080/message/
  ```
**Resposta:** Em caso de sucesso, retorna uma lista de mensagens enviadas pelo usuário com as respostas do modelo de inteligência artificial.

- **Endpoint:** DELETE /message/{id}
- **Descrição:** Este endpoint é utilizado para deletar uma mensagem específica do usuário autenticado. 
- **Parâmetros de Caminho:** ID da mensagem a ser deletada.

 **Requisição:**
  ```URL
   DELETE http://localhost:8080/message/{id}
  ```
**Resposta:** Retorna 200 OK se a mensagem for deletada com sucesso. Retorna 404 Not Found se a mensagem não for encontrada.

### 2.3 UserController

- **Endpoint:** POST /user/register
- **Descrição:** Este endpoint é utilizado para registrar um novo usuário.
- **Corpo da Solicitação:** O corpo da solicitação deve ser um JSON contendo as informações do usuário (nome, login, senha, etc.).

 **Requisição:**
  ```json
{
  "name": "Leandro",
  "email": "neurotrix@fiap.com",
  "password": "teste123",
}
  ```

**Resposta:** Retorna 200 OK caso o usuário seja criado corretamente

- **Endpoint:** GET /user/all 
- **Descrição:** Este endpoint é utilizado para obter uma lista de todos os usuários. Este endpoint está disponível apenas para usuários com a role "ADMIN". 

 **Requisição:**
  ```URL
   GET http://localhost:8080/user/all
  ```
  
**Resposta:** Em caso de sucesso, retorna uma lista de todos os usuários.

## 3. Implementações futuras

Algumas das implementações futuras previstas para a aplicação incluem:

- Melhor integração com o GPT-3 para fornecer itinerários de viagem mais detalhados e personalizados.
- Implementação de uma interface de usuário mais amigável e intuitiva.
- Integração com APIs de companhias aéreas e hotéis para reservar voos e acomodações diretamente através do aplicativo.
- Implementação de um sistema de feedback para os usuários avaliarem seus itinerários e sugerirem melhorias.

## 4. Arquitetura da solução

Para a autenticação do usuário, é utilizada a arquitetura de Spring Web Application com web security:

- **Autenticação:** Quando o usuário envia uma solicitação de login para o servidor, fornecendo suas credenciais (nome de usuário e senha), o backend verifica essas credenciais no banco de dados. Se as credenciais forem válidas, a aplicação irá gerar um token JWT, que é assinado e criptografado usando uma chave secreta mantida no servidor.

- **Geração do Token:** O token JWT contém informações sobre o usuário (como seu ID, nome de usuário e outras informações que podem ser úteis), bem como informações sobre a validade do token. O token é então enviado de volta ao usuário como resposta à solicitação de login.

- **Uso do Token:** Em todas as solicitações subsequentes que o usuário faz para o servidor, o token JWT deve ser incluído no cabeçalho da solicitação. Isso serve como uma "prova" de que o usuário está autenticado. É assim que o servidor "sabe" quem está fazendo a solicitação sem ter que verificar as credenciais do usuário a cada vez.

- **Verificação do Token:** Quando uma solicitação é recebida pelo servidor com um token JWT, o servidor irá validar o token - verificando se ele não expirou e se foi assinado com a chave correta. Se o token for válido, o servidor tratará a solicitação como vinda do usuário identificado no token.

- **Autorização:** Além da autenticação, o token JWT também pode ser usado para autorização. Isso significa que ele pode conter informações sobre o que o usuário tem permissão para fazer no sistema (seus roles ou permissões), e o servidor pode verificar essas informações para garantir que o usuário só tenha acesso aos recursos apropriados.

**Geração de viagens**: O sistema conta com os serviços da OpenAI para gerar viagens personalizadas para o cliente de acordo com as preferências.

- **Finalização da sessão:** Se o usuário desejar fazer logout, o cliente simplesmente descarta o token JWT. Como o servidor não mantém um registro dos tokens emitidos, não há necessidade de uma solicitação de logout para o servidor.

## 8. Arquitetura da Aplicação

A arquitetura do Where2Go é baseada na arquitetura de microserviços, o que permite a escalabilidade e a manutenção independentes dos serviços. O aplicativo é composto por vários serviços independentes que se comunicam entre si.

Os componentes principais da arquitetura são:

- Serviço de Autenticação
- Serviço de Planejamento de Viagens
- Serviço de Persistência de Dados
- Serviço de Integração com a API OpenAI

## 9. Conexão com a API OpenAI

A conexão com a API OpenAI é realizada utilizando a biblioteca openai-java, que permite realizar requisições POST à API, especificando o prompt, o modelo e os tokens máximos.

Exemplo da requisição em JAVA :

```java
    OpenAiService openai = new OpenAiService("seu_token_api", Duration.ofSeconds(60));
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

        return MapperUtil.jsonToEntity(trip, TripDto.class);
    }
```

