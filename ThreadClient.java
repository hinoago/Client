package aplicacao.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ThreadClient extends Thread{
	JComboBox<String> cb;
	JLabel label;
	JLabel label2;
	
	public ThreadClient(JComboBox<String> cb, JLabel label, JLabel label2) {
		this.cb = cb;
		this.label = label;
		this.label2 = label2;
	}
	@Override
	public void run() {
		try {
			Socket s = new Socket("10.120.28.120", 12028);
			String nome = (String) cb.getSelectedItem();
			String caminho = "";
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(nome);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String versao = dis.readUTF();
			BufferedInputStream bis = new BufferedInputStream(dis);
			int dados;
			if(nome.contains("Apoio")) {
				caminho = "\"C:\\Program Files (x86)\\SABI\\Apoio\"";
			}else if(nome.contains("Atendimento")) {
				caminho = "\"C:\\Program Files (x86)\\SABI\\Atendimento ao Cliente\"";
			}else if(nome.contains("Clinica")) {
				caminho = "\"C:\\Program Files (x86)\\SABI\\Atendimento Medico\"";
			}else if(nome.contains("Controle")) {
				caminho = "\"C:\\Program Files (x86)\\SABI\\Controle Operacional\"";
			}else if(nome.contains("Sads")) {
				caminho = "\"C:\\Program Files (x86)\\SABI\\Administracao de Seguranca\"";
			}
			File file2 = new File(System.getProperty("user.home") + "\\" + nome + ".exe");
			file2.deleteOnExit();
			FileOutputStream fos = new FileOutputStream(file2);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			while((dados = bis.read()) != -1) {
				bos.write(dados);
			}
			bos.close();
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "SET PATH=%PATH%;c:\\Program Files\\7-Zip\\", "/c", "7z x -y -o" + caminho + " " + "\"" + file2.getAbsolutePath() + "\"");
			pb.start();
			label.setText("Atualizacao do aplicativo " + nome + " finalizada");
			label2.setText("Versão:" + versao);
			s.close();
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
