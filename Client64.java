package aplicacao.client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client64 {
	public Client64() throws IOException{
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
			String valor = (String) cb.getSelectedItem();
			if(valor.contains("Escolha")) {
				JOptionPane.showMessageDialog(null, "Selecione uma opção");
			}else {
				ThreadClient tc = new ThreadClient(cb, label, label2);
				tc.run();
				try {
					continuar(valor);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void continuar(String valor) throws IOException {
		String aplicativo = valor;
		String user = System.getProperty("user.name");
		String caminhoLink = "C:\\Users\\" + user + "\\Desktop\\";
		String caminhoPrg;
		File file;
		File file2;
		if(aplicativo.equalsIgnoreCase("Apoio")){
			file = new File(caminhoLink + "Apoio.exe.lnk");
			file2 = new File(caminhoLink + "Apoio.exe - Atalho.lnk");
			caminhoPrg = "C:\\Program Files (x86)\\SABI\\Apoio\\";
		}else if(aplicativo.equalsIgnoreCase("Atendimento")) {
			file = new File(caminhoLink + "Atendimento.exe.lnk");
			file2 = new File(caminhoLink + "Atendimento.exe - Atalho.lnk");
			caminhoPrg = "C:\\Program Files (x86)\\SABI\\Atendimento ao Cliente\\";
		}else if(aplicativo.equalsIgnoreCase("Clinica")) {
			file = new File(caminhoLink + "Clinica.exe.lnk");
			file2 = new File(caminhoLink + "Clinica.exe - Atalho.lnk");
			caminhoPrg = "C:\\Program Files (x86)\\SABI\\Atendimento Medico\\";
		}else if(aplicativo.equalsIgnoreCase("Controle")) {
			file = new File(caminhoLink + "Controle.exe.lnk");
			file2 = new File(caminhoLink + "Controle.exe - Atalho.lnk");
			caminhoPrg = "C:\\Program Files (x86)\\SABI\\Controle Operacional\\";
		}else if(aplicativo.equalsIgnoreCase("Sads")) {
			file = new File(caminhoLink + "Sads.exe.lnk");
			file2 = new File(caminhoLink + "Sads.exe - Atalho.lnk");
			caminhoPrg = "C:\\Program Files (x86)\\SABI\\Administracao de Seguranca\\";
		}else {
			file = null;
			file2 = null;
			caminhoPrg = null;
		}
		file.delete();
		file2.delete();
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(caminhoPrg + aplicativo + ".exe"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(caminhoLink + aplicativo + ".exe"));
		int dados;
		while((dados = bis.read()) != -1) {
			bos.write(dados);
		}
		bis.close();
		bos.close();
	}
}