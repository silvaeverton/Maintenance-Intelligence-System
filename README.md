# MANTIS
**(Maintenance Intelligence System)**

## 🧭 Visão Geral do Projeto

Objetivo:
Desenvolver um sistema completo de controle de manutenção industrial, integrando ordens de serviço, relatórios técnicos, cadastro de máquinas e técnicos, com foco na rastreabilidade e produtividade da equipe de manutenção.

### Possíveis plataformas:

Web (painel administrativo)

## 🧱 Módulos Principais
**1. Cadastro**
* ✅  Técnicos (ID, Nome, Matrícula)

* ✅  Máquinas (ID, Nome, Setor, Fabricante, Modelo, Status)

* ✅  Categorias (tipo de problema, tipo de manutenção)

* ✅ Setores (ex: produção, embalagem, etc.)

**2. Ordem de Serviço (OS)**
### `Escopo Ordem de Serviço`´
* ✅ Requisitante, Setor, Líder

* ✅ Descrição da atividade

* ✅  Categoria (Preventiva, Corretiva, Preditiva)

* ✅ Prioridade (Extrema, Alta, Baixa)

* ✅ Status (Aberta, Em andamento, Finalizada, Cancelada)

* ✅ Datas (programada, conclusão)

* ✅ Indicador de prazo (Entregue no prazo, Atrasado)

* ✅ Observações gerais

**3. Relatório Técnico (RT)**
### `Escopo RT`

* ✅ Técnico Responsável

* ✅ Máquina relacionada

* ✅ Categoria do problema (Elétrico, Mecânico, etc.)

* ✅ Status (Aberto, Fechado)

* ✅ Horário de início / término

* ✅ Resumo do problema

* ✅ Solução aplicada

* ✅ Observações

**4. Consultas e Filtros Avançados**

* 🔍 Buscar por ID, técnico, máquina, status, categoria, data, mês

* ✅ Exportação de relatórios:

* ✅ Planilha Excel

* ✅ PDF com layout profissional

## 5. Integração com IoT
* ✅ Requisição de serviço enviada automaticamente por sensores ESP32

* ✅ Identificação da máquina e categoria da falha (por código)

## 🔍 Filtros e Relatórios
**Relatórios Técnicos:**

* Por técnico

* Por máquina

* Por período

* Por status

* Por categoria

* Exportação por período (Excel / PDF)

**Ordens de Serviço:**

* Por prioridade

* Por status

* Por técnico

* Por prazo

* Por setor

* Estatísticas (tempo médio de atendimento, etc.)

## 📊 1. Dashboard de Indicadores

* Visualizar painel com KPIs em tempo real ou filtrados por período

* Gráficos de barras, pizza e linha com:

* Distribuição por tipo de manutenção (preventiva, corretiva, preditiva)

* Ordens por status (Aberta, Em andamento, Finalizada, Cancelada)

* Ordens por prioridade (Alta, Extrema, Baixa)

* Problemas mais frequentes por categoria (Elétrico, Mecânico, etc.)


* Máquinas com maior número de ocorrências

## 🧮 2. Cálculo automático de KPIs
* MTTR (Mean Time to Repair) – Tempo médio para resolver falhas

* MTBF (Mean Time Between Failures) – Tempo médio entre falhas da mesma máquina

* Taxa de cumprimento de prazo – % de OS finalizadas no prazo planejado

* Tempo médio de atendimento – Entre abertura da OS e início da ação

* Relação OS Preventivas x Corretivas – Avalia maturidade da manutenção



## 🏗️ Tecnologias Utilizadas

* Java 17

* Spring Boot 3.5

* Spring Data JPA

* Spring Validation

* MySQL (produção)

* Lombok

* JUnit 5

* Mockito

* MockMvc para testes de Controller

* Swagger/OpenAPI 3 (documentação automática)

* Jacoco (análise de cobertura de testes)

## 📂 Estrutura do Projeto

```plaintext

src/
└── main/
    ├── java/com/everton/maintenance_intelligence_system/
    │   ├── controllers/          # Controllers REST
    │   ├── dtos/                # DTOs de entrada e saída
    │   ├── enums/               # Enums para status de máquina e ordem
    │   ├── exceptions/          # Custom exceptions e handlers globais
    │   ├── model/               # Entidades JPA (Machine, ServiceOrder, etc)
    │   ├── repository/          # Interfaces JPA Repository
    │   └── services/            # Lógica de negócios (Services)
    │
    └── resources/
        ├── application.yml      # Configurações do Spring Boot
        └── static/              # arquivos estáticos
```

## ⚙️ Configuração Local

**1. Clone o repositório:**

 ```bash

 git clone https://github.com/seu-usuario/maintenance-control.git
cd maintenance-control

```

**2. Configure o banco de dados no application.yml:**

```bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/maintenance_control
    username: root
    password: sua_senha

```

**3. Compile e rode a aplicação:**
```bash

./mvnw clean install
./mvnw spring-boot:run
```

**4. Acesse o Swagger (Documentação de API):**
```bash

http://localhost:8080/swagger-ui.html

```

## ✅ Testes

A aplicação possui cobertura de testes unitários e de integração:

* **Mockito** para simular dependências.

* **MockMvc** para testar endpoints dos controllers.

* **Jacoco** para verificar a cobertura de testes.

Executar os testes:

```bash

./mvnw clean test
```

Após a execução, a cobertura pode ser vista no:
```bash

target/site/jacoco/index.html

```

## 📌 Principais Endpoints

| Recurso            | Método | Endpoint                                 | Descrição                                       |
|--------------------|--------|------------------------------------------|-------------------------------------------------|
| Técnicos           | GET    | /tecnicos                                | Listar todos os técnicos                        |
|                    | GET    | /tecnicos/{id}                           | Buscar técnico por ID                           |
|                    | POST   | /tecnicos                                | Criar novo técnico                              |
|                    | PUT    | /tecnicos/{id}                           | Atualizar técnico                               |
|                    | DELETE | /tecnicos/{id}                           | Deletar técnico                                 |
| Máquinas           | GET    | /maquinas                                | Listar todas as máquinas                        |
|                    | GET    | /maquinas/{id}                           | Buscar máquina por ID                           |
|                    | POST   | /maquinas                                | Criar nova máquina                              |
|                    | PUT    | /maquinas/{id}                           | Atualizar máquina                               |
|                    | DELETE | /maquinas/{id}                           | Deletar máquina                                 |
| Ordem de Serviço   | GET    | /ordens                                  | Listar todas as ordens de serviço               |
|                    | GET    | /ordens/{id}                             | Buscar ordem por ID                             |
|                    | GET    | /ordens/tecnico/{id}                     | Buscar ordens por técnico                       |
|                    | GET    | /ordens/maquina/{id}                     | Buscar ordens por máquina                       |
|                    | GET    | /ordens/status/{status}                 | Buscar ordens por status                        |
|                    | GET    | /ordens/categoria/{categoria}           | Buscar por categoria de manutenção              |
|                    | GET    | /ordens/data?start=yyyy-MM-dd&end=yyyy-MM-dd | Buscar por intervalo de datas              |
|                    | GET    | /ordens/mes/{mes}                        | Buscar por mês                                  |
|                    | POST   | /ordens                                  | Criar nova ordem de serviço                     |
|                    | PUT    | /ordens/{id}                             | Atualizar ordem                                 |
|                    | DELETE | /ordens/{id}                             | Cancelar ordem de serviço                       |
| Relatório Técnico  | GET    | /relatorios                              | Listar todos os relatórios técnicos             |
|                    | GET    | /relatorios/{id}                         | Buscar relatório por ID                         |
|                    | GET    | /relatorios/tecnico/{id}                 | Buscar por técnico                              |
|                    | GET    | /relatorios/maquina/{id}                 | Buscar por máquina                              |
|                    | GET    | /relatorios/status/{status}             | Buscar por status (aberto/fechado)              |
|                    | GET    | /relatorios/categoria/{categoria}       | Buscar por categoria do problema                |
|                    | GET    | /relatorios/data?start=yyyy-MM-dd&end=yyyy-MM-dd | Buscar por intervalo de datas          |
|                    | GET    | /relatorios/mes/{mes}                    | Buscar por mês                                  |
|                    | POST   | /relatorios                              | Criar novo relatório técnico                    |
|                    | PUT    | /relatorios/{id}                         | Atualizar relatório técnico                     |
|                    | DELETE | /relatorios/{id}                         | Deletar relatório técnico                       |
| Exportações        | GET    | /export/relatorios/excel                 | Exportar planilha de relatórios técnicos        |
|                    | GET    | /export/relatorios/pdf/{id}             | Exportar PDF de um relatório técnico            |
|                    | GET    | /export/ordens/excel                     | Exportar planilha de ordens de serviço          |
|                    | GET    | /export/ordens/pdf/{id}                 | Exportar PDF de uma ordem de serviço            |
| KPIs               | GET    | /kpis/geral                              | KPIs gerais do sistema                          |
|                    | GET    | /kpis/mes/{mes}                          | KPIs filtrados por mês                          |
|                    | GET    | /kpis/maquina/{id}                       | KPIs por máquina específica                     |
|                    | GET    | /kpis/tecnico/{id}                       | KPIs por técnico específico                     |

## 👨‍💻 Autor
**Everton Silva**

Java Developer | Backend Enthusiast | Industrial Systems Integration


