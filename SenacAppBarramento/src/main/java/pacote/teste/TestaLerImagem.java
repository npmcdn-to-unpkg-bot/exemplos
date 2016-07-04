package pacote.teste;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import pacote.modelo.Imagem;

public class TestaLerImagem {
	public static void main(String[] args) {
		WebTarget url;
		Client client = ClientBuilder.newClient();
		url = client.target("http://localhost:8080/Fluxo/Post/Imagem");
		
		Response response = url.request().get();
		Imagem imagem = response.readEntity(Imagem.class);
		
		System.out.println("Tamanho no cliente: " + imagem.getImagem64().length());
		
		Base64.Decoder decoder = Base64.getMimeDecoder();
		byte[] decodedByteArray = decoder.decode(imagem.getImagem64());
		
		File file = new File(imagem.getNomeImagem() + ".png");
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(decodedByteArray));
			JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));
			
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(decodedByteArray);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
