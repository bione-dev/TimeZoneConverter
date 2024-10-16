package TimeZoneConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TimeZoneConverter extends JFrame implements java.io.Serializable {
    private static final long serialVersionUID = 1L; // Adicionando o serialVersionUID
    private JTextField inputTime;
    private JComboBox<String> fromComboBox, toComboBox;
    private JTextArea resultArea;
    
    // Mapeamento das cidades e seus respectivos fusos horários
    private static final HashMap<String, ZoneId> timeZones = new HashMap<>();

    static {
        timeZones.put("São Paulo, Brasil", ZoneId.of("America/Sao_Paulo"));
        timeZones.put("Nova Iorque, EUA", ZoneId.of("America/New_York"));
        timeZones.put("Tóquio, Japão", ZoneId.of("Asia/Tokyo"));
        timeZones.put("Londres, Reino Unido", ZoneId.of("Europe/London"));
        timeZones.put("Paris, França", ZoneId.of("Europe/Paris"));
        timeZones.put("Sydney, Austrália", ZoneId.of("Australia/Sydney"));
        timeZones.put("Cairo, Egito", ZoneId.of("Africa/Cairo"));
        timeZones.put("Moscovo, Rússia", ZoneId.of("Europe/Moscow"));
        timeZones.put("Los Angeles, EUA", ZoneId.of("America/Los_Angeles"));
        timeZones.put("Pequim, China", ZoneId.of("Asia/Shanghai"));
        // Adicione mais cidades conforme necessário
    }

    public TimeZoneConverter() {
        setTitle("Conversor de Fuso Horário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margens

        JLabel timeLabel = new JLabel("Hora (HH:mm):");
        inputTime = new JTextField(10);

        JLabel fromLabel = new JLabel("De:");
        fromComboBox = new JComboBox<>(timeZones.keySet().toArray(new String[0]));
        fromComboBox.setSelectedItem("São Paulo, Brasil"); // Define São Paulo como padrão

        JLabel toLabel = new JLabel("Para:");
        toComboBox = new JComboBox<>(timeZones.keySet().toArray(new String[0]));

        JButton convertButton = new JButton("Converter");
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Layout
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        add(timeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(inputTime, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(fromLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(fromComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(toLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        add(toComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(convertButton, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Ação do botão
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTime();
            }
        });
    }

    private void convertTime() {
        String input = inputTime.getText();
        String fromCity = (String) fromComboBox.getSelectedItem();
        String toCity = (String) toComboBox.getSelectedItem();

        if (input.isEmpty()) {
            resultArea.setText("Por favor, insira a hora.");
            return;
        }

        ZoneId fromZone = timeZones.get(fromCity);
        ZoneId toZone = timeZones.get(toCity);

        // Conversão do horário
        ZonedDateTime inputTimeZoned = ZonedDateTime.now(fromZone).withHour(Integer.parseInt(input.split(":")[0]))
                .withMinute(Integer.parseInt(input.split(":")[1]));
        ZonedDateTime convertedTime = inputTimeZoned.withZoneSameInstant(toZone);

        // Cálculo da diferença de horas
        int hoursDifference = toZone.getRules().getOffset(inputTimeZoned.toInstant()).getTotalSeconds() / 3600
                - fromZone.getRules().getOffset(inputTimeZoned.toInstant()).getTotalSeconds() / 3600;

        // Formatação do resultado
        String differenceSign = hoursDifference > 0 ? "para mais" : "para menos";
        String result = String.format("Resultado em %s: %s\n", toCity, convertedTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        result += String.format("Diferença de horário: %d horas %s.\n", Math.abs(hoursDifference), differenceSign);
        resultArea.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimeZoneConverter converter = new TimeZoneConverter();
            converter.setVisible(true);
        });
    }
}
