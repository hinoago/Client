package aplicacao.client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client32 {
	String valor;
	
	public Client32() throws IOException{
		JFrame jf = new JFrame("Atualizador SABI");
		jf.setLayout(new GridLayout(3, 1));
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JPanel panelLabel = new JPanel();
		panelLabel.setLayout(new GridLayout(3, 1));
		JPanel container1 = new JPanel(new FlowLayout());
		panelLabel.add(container1);
		JPanel container2 = new JPanel(new FlowLayout());
		panelLabel.add(container2);
		JPanel aviso = new JPanel();
		aviso.setLayout(new FlowLayout());
		JLabel av = new JLabel("Atencao. E necessario ter o 7-Zip instalado.");
		String[] a = {"Escolha a opcao","Apoio", "Atendimento","Clinica", "Controle", "Sads"};
		JComboBox<String> cb = new JComboBox<>(a);
		JButton button = new JButton("Atualizar");
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		jf.setSize(400, 300);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(cb);
		panel.add(button);
		container1.add(label);
		container2.add(label2);
		jf.add(panel);
		jf.add(panelLabel);
		aviso.add(av);
		jf.add(aviso);
		button.addActionListener(e -> {
			valor = (String) cb.getSelectedItem();
			if(valor.contains("Escolha")) {
				JOptionPane.showMessageDialog(null, "Selecione uma opção");
			}else {
				ThreadClient tc = new ThreadClient(cb, label, label2);
				tc.run();
			}
		});
	}
}