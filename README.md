# Gestão de Eventos (API REST)

Projeto desenvolvido no âmbito da disciplina de LP - Componente 2.
Esta API permite a gestão de eventos, inscrições, avaliações e notificações.

## Como Correr o Projeto
1. Certifique-se que tem o MySQL a correr na porta 3306.
2. Crie uma base de dados chamada `gestordeeventosupt`.
3. Configure as credenciais no ficheiro `src/main/resources/application.properties`.
4. Execute a aplicação via Maven ou na sua IDE.

## 🛠 Endpoints Principais (Testar no Postman)

### Utilizadores
- **Criar Utilizador:** `POST /api/utilizadores`
- **Listar Todos:** `GET /api/utilizadores`

### Eventos
- **Criar Evento:** `POST /api/eventos?organizadorId={id}`
- **Listar Disponíveis:** `GET /api/eventos/disponiveis`
- **Filtrar:** `GET /api/eventos?estado=ATIVO`

### Inscrições
- **Inscrever:** `POST /api/inscricoes` (Body: `{ "eventoId": 1, "participanteId": 2 }`)
- **Cancelar:** `PUT /api/inscricoes/{id}/cancelar`

## Autores
- Equipa 4
