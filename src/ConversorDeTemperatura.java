import javax.swing.*;
import java.awt.*;

public class ConversorDeTemperatura extends JFrame {
    // Componentes da interface gráfica
    private JTextField campoValor;
    private JComboBox<String> comboBox;

    public ConversorDeTemperatura() {
        super("Conversor de Temperatura");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Criar o painel e definir as configurações de layout
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Rótulo para seleção da unidade de temperatura
        JLabel rotuloUnidade = new JLabel("Selecione a unidade:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        painel.add(rotuloUnidade, constraints);

        // Caixa de seleção para escolher a unidade de temperatura
        String[] unidadesDeTemperatura = {"Celsius", "Fahrenheit", "Kelvin"};
        comboBox = new JComboBox<>(unidadesDeTemperatura);
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        painel.add(comboBox, constraints);

        // Campo de texto para inserir o valor da temperatura
        campoValor = new JTextField(10);
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        painel.add(campoValor, constraints);

        // Botões para realizar a conversão e limpar a entrada
        JButton botaoConverter = new JButton("Converter");
        JButton botaoLimpar = new JButton("Limpar");
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 1;

        // Listeners de ação para os botões
        botaoConverter.addActionListener(e -> {
            realizarConversao(comboBox.getSelectedItem().toString(), campoValor.getText());
        });

        botaoLimpar.addActionListener(e -> {
            limparCampoValor();
        });

        // Definir o tamanho dos botões e adicioná-los ao painel
        botaoConverter.setPreferredSize(new Dimension(100, botaoConverter.getPreferredSize().height));
        botaoLimpar.setPreferredSize(new Dimension(100, botaoLimpar.getPreferredSize().height));

        painel.add(botaoConverter, constraints);
        constraints.gridx = 1;
        painel.add(botaoLimpar, constraints);

        // Adicionar o painel ao JFrame
        add(painel);

        // Configurar a janela e torná-la visível
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para realizar a conversão de temperatura
    private void realizarConversao(String unidadeSelecionada, String valor) {
        double temperatura;
        try {
            temperatura = Double.parseDouble(valor);
        } catch (NumberFormatException ex) {
            exibirMensagemDeErro("Valor de temperatura inválido.");
            return;
        }

        double resultado;
        String unidadeResultado;

        switch (unidadeSelecionada) {
            case "Celsius":
                unidadeResultado = "Fahrenheit";
                resultado = (temperatura * 9 / 5) + 32;
                break;
            case "Fahrenheit":
                unidadeResultado = "Celsius";
                resultado = (temperatura - 32) * 5 / 9;
                break;
            case "Kelvin":
                unidadeResultado = "Celsius";
                resultado = temperatura - 273.15;
                break;
            default:
                exibirMensagemDeErro("Unidade de temperatura inválida.");
                return;
        }

        String mensagem = String.format("%.2f %s é igual a %.2f %s", temperatura, unidadeSelecionada, resultado, unidadeResultado);
        exibirMensagemDeResultado(mensagem);
    }

    // Método para exibir uma mensagem de erro em uma caixa de diálogo
    private void exibirMensagemDeErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    // Método para exibir uma mensagem de resultado em uma caixa de diálogo
    private void exibirMensagemDeResultado(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para limpar o campo de valor
    private void limparCampoValor() {
        campoValor.setText("");
    }

    // Ponto de entrada para executar o aplicativo
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConversorDeTemperatura();
        });
    }
}
