
# TimeZoneConverter

O **TimeZoneConverter** é uma aplicação Java com interface gráfica que permite converter horários entre diferentes fusos horários. A aplicação é baseada em Swing, uma biblioteca para construir interfaces gráficas em Java.

## Funcionalidade

A aplicação permite ao usuário inserir uma hora em formato **HH:mm** e selecionar uma cidade de origem e uma cidade de destino de uma lista predefinida. Ao clicar no botão "Converter", a aplicação calcula e exibe a hora correspondente na cidade de destino, juntamente com a diferença de horário em horas.

## Estrutura do Código

### Importações

O código utiliza as seguintes bibliotecas:

- `javax.swing.*`: Para construir a interface gráfica.
- `java.awt.*`: Para gerenciar layouts e componentes gráficos.
- `java.time.*`: Para manipulação de datas e horários.
- `java.util.HashMap`: Para mapear cidades a seus respectivos fusos horários.

### Classe TimeZoneConverter

- **Herança**: A classe `TimeZoneConverter` estende `JFrame`, tornando-se uma janela GUI.
- **Serialização**: Implementa `java.io.Serializable` para permitir a serialização da classe.

### Componentes da Interface

- `JTextField inputTime`: Campo para inserir a hora.
- `JComboBox<String> fromComboBox`: ComboBox para selecionar a cidade de origem.
- `JComboBox<String> toComboBox`: ComboBox para selecionar a cidade de destino.
- `JTextArea resultArea`: Área de texto para exibir os resultados da conversão.

### Mapeamento de Fusos Horários

Um `HashMap` chamado `timeZones` armazena o mapeamento entre cidades e seus respectivos fusos horários. O bloco `static` inicializa esse mapeamento com algumas cidades e seus fusos horários.

### Construtor

No construtor da classe, os componentes da interface são configurados:

- Título, tamanho e layout da janela.
- Labels e campos de entrada para hora e seleção de cidades.
- Botão "Converter" que aciona a conversão de horário.
- A disposição dos componentes é gerenciada usando `GridBagLayout`.

### Método `convertTime`

Esse método é responsável por:

1. Obter os valores de entrada (hora, cidade de origem e cidade de destino).
2. Verificar se a hora foi inserida.
3. Realizar a conversão do horário usando `ZonedDateTime`.
4. Calcular a diferença de horas entre os fusos horários.
5. Formatar e exibir o resultado na `resultArea`.

### Método `main`

O método `main` inicializa a aplicação, tornando a janela visível ao usuário.

## Como Usar

1. Insira a hora no formato **HH:mm**.
2. Selecione a cidade de origem e a cidade de destino.
3. Clique no botão "Converter" para visualizar o horário convertido e a diferença de horário.

## Dependências

- Java SE 8 ou superior

---

Sinta-se à vontade para ajustar qualquer parte conforme necessário!
