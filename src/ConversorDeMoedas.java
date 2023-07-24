import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConversorDeMoedas extends JFrame {


    // taxas
    private static final Map<String, Double> TAXAS_DE_CONVERSAO = new HashMap<>();

    static {
        // Inicializar as taxas de conversão de moedas
        TAXAS_DE_CONVERSAO.put("De Reais para Dólares", 5.20);
        TAXAS_DE_CONVERSAO.put("De Reais para Euros", 6.10);
        TAXAS_DE_CONVERSAO.put("De Reais para Libras - BRL-BRL", 0.16);
        TAXAS_DE_CONVERSAO.put("De Reais para Peso Argentino", 56.20);
        TAXAS_DE_CONVERSAO.put("De Reais para Rublo Russo", 18.94);
        TAXAS_DE_CONVERSAO.put("De Dólares para Reais", 4.78);
        TAXAS_DE_CONVERSAO.put("De Euros para Reais", 5.32);
        TAXAS_DE_CONVERSAO.put("De Libras para Reais", 6.14);
        TAXAS_DE_CONVERSAO.put("De Peso Argentino para Reais", 0.018);
        TAXAS_DE_CONVERSAO.put("De Rublo Russo para Reais", 0.053);
    }

    public ConversorDeMoedas() {
        super("Conversor de Moedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Criar o painel e definir as restrições do layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Adicionar rótulo para seleção de moeda
        JLabel texto = new JLabel("Selecione uma opção:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(texto, constraints);

        // Adicionar caixa de seleção para opções de moeda
        JComboBox<String> comboBox = new JComboBox<>(TAXAS_DE_CONVERSAO.keySet().toArray(new String[0]));
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboBox, constraints);

        // Adicionar campo de texto para inserir o valor da moeda
        JTextField valorField = new JTextField(10);
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(valorField, constraints);

        // Adicionar botões para realizar a conversão e limpar a entrada
        JButton botaoOk = new JButton("Ok");
        JButton botaoApagar = new JButton("Apagar");
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 1;

        // Listeners de ação para os botões
        botaoOk.addActionListener(e -> {
            realizarConversao(comboBox.getSelectedItem().toString(), valorField.getText());
        });

        botaoApagar.addActionListener(e -> {
            valorField.setText("");
        });

        // Definir as dimensões dos botões e adicioná-los ao painel
        botaoOk.setPreferredSize(new Dimension(100, botaoOk.getPreferredSize().height));
        botaoApagar.setPreferredSize(new Dimension(100, botaoApagar.getPreferredSize().height));

        panel.add(botaoOk, constraints);
        constraints.gridx = 1;
        panel.add(botaoApagar, constraints);

        // Painel
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Realizar coversão
    private void realizarConversao(String selectedOption, String valorText) {
        if (valorText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido!");
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido!");
            return;
        }

        double taxaDeConversao = TAXAS_DE_CONVERSAO.get(selectedOption);
        double resultado = valor * taxaDeConversao;
        JOptionPane.showMessageDialog(this, "Resultado da conversão: " + resultado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConversorDeMoedas();
        });
    }
}
