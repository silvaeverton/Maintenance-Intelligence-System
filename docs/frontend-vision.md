
# 🖥️ Visão Geral das Telas – Sistema MANTIS

## 🧭 1. Tela Inicial (Dashboard / Home)
### Objetivo:
Exibir os KPIs de manutenção e visão geral do sistema.

**Componentes:**
* **Cards Resumo:**

  * OS Abertas, OS Finalizadas, RTs Abertas, RTs Fechadas

  * Tempo médio de atendimento (MTTR)

  * Quantidade de falhas por máquina

* **Gráficos:**

  * Gráfico de pizza: Tipos de manutenção

  * Gráfico de barra: RTs por técnico

  * Linha do tempo: OS por mês

* **Filtros:**

    * Data (início/fim)

    * Técnico, máquina, setor

* **Botões:**

    * [Exportar PDF]

    * [Exportar Excel]

    * [Ver mais detalhes]

## 🛠️ 2. Cadastro e Listagem de Máquinas
### Objetivo:
Cadastrar e editar informações das máquinas.

**Componentes:**
* **Formulário de cadastro:**

    * Nome, Setor, Modelo, Fabricante, Status

    * [Salvar] [Cancelar]

* **Tabela de máquinas:**

    * Colunas: ID, Nome, Setor, Status, Ações

    * **Botões:** [Editar] [Excluir]

## 👨‍🔧 3. Cadastro e Listagem de Técnicos
#### Objetivo:
Gerenciar os técnicos que atuam nas manutenções.

**Componentes:**

* **Formulário de cadastro:**

    * Nome, Matrícula

    * [Salvar] [Cancelar]

* **Tabela de técnicos:**

    * Colunas: ID, Nome, Matrícula, Ações

    * **Botões:** [Editar] [Excluir]

## 📝 4. Tela de Ordem de Serviço (OS)
**Objetivo:**
Criar, editar e acompanhar as ordens de serviço.

**Componentes:**
* **Formulário:**

    * Requisitante, Setor, Líder Responsável

    * Descrição da atividade

    * Tipo de manutenção: Preventiva / Corretiva / Preditiva

    * Prioridade: Baixa / Alta / Extrema

    * Status: Aberta / Em andamento / Finalizada / Cancelada

    * Data Programada, Data de Conclusão

    * Status de Prazo: Atrasado / Entregue no prazo

    * Observações

    * [Salvar] [Cancelar]

* **Tabela de OS:**

    * Filtros por status, data, tipo, prioridade

    * **Ações:** [Editar] [Exportar PDF] [Excluir]

## 📋 5. Tela de Relatório Técnico (RT)
**Objetivo:**

Visualizar e editar os RTs abertos automaticamente pelo ESP32.

**Comportamento**:

   * Criado automaticamente com maquinaId e dataHoraInicioAt.

   * Finalizado automaticamente com dataHoraTerminoAt

   * Técnico entra e preenche o restante

**Componentes**:
* **Formulário de edição:**

    * Técnico (dropdown)

    * Líder, Operador

    * Categoria: Elétrico, Mecânico, etc.

    * Status: Aberto / Fechado

    * dataHoraInicioAt (desabilitado)

    * dataHoraTerminoAt (desabilitado)

    * Problema encontrado

    * Solução adotada

    * Observações

    * [Salvar] [Fechar RT] [Exportar PDF]

## 🔍 6. Consulta e Filtros de OS e RTs
**Objetivo:**
Permitir análise e busca rápida de OS e RTs.

**Componentes:**
* **Filtros avançados:**

    * Técnico, máquina, data, status, categoria

* **Tabela dinâmica:**

* **Botões de ação:** [Ver], [Editar], [Exportar]

* **Exportações:**

    * [Exportar Excel] [Exportar PDF Consolidado]

## 🔐 7. Login / Perfil (simples)
**Objetivo:**
Autenticar técnico ou administrador.

**Componentes:**
* Login por e-mail ou matrícula

* Redirecionamento com base no perfil

    * Técnico: acesso limitado (preencher RT)

    * Admin: acesso completo

