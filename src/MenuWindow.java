import javax.swing.*;
import java.awt.*;

public class MenuWindow extends JFrame {
    private static final String[] OPCOES = {"Conversor de Moedas", "Conversor de Temperatura"};
    private static final int LARGURA_JANELA = 300;
    private static final int ALTURA_JANELA = 150;

    private Menu menu = new Menu();

    public MenuWindow() {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Interface();
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void Interface() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Texto
        JLabel texto = new JLabel("Selecione uma opção:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(texto, constraints);

        // Lista suspensa
        JComboBox<String> comboBox = new JComboBox<>(OPCOES);
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(comboBox, constraints);

        //Botões
        JButton botaoOk = new JButton("Ok");
        JButton botaoCancelar = new JButton("Cancelar");
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 1;

        // Ação ao clicar
        botaoOk.addActionListener(e -> {
            String selectedOption = comboBox.getSelectedItem().toString();
            menu.aoClicarBotaoOk(selectedOption);

            //display de seleção
            if (selectedOption.equals(OPCOES[0])) {
                new ConversorDeMoedas();
            } else if (selectedOption.equals(OPCOES[1])) {
                new ConversorDeTemperatura();
            }
        });
        botaoCancelar.addActionListener(e -> menu.aoClicarBotaoCancelar());

        // Tamanho do Botão
        botaoOk.setPreferredSize(new Dimension(100, botaoOk.getPreferredSize().height));
        botaoCancelar.setPreferredSize(new Dimension(100, botaoCancelar.getPreferredSize().height));

        add(botaoOk, constraints);

        constraints.gridx = 1;
        add(botaoCancelar, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuWindow::new);
    }
}
