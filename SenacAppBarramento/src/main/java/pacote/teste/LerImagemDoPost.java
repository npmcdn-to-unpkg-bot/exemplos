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

import pacote.modelo.Post;

public class LerImagemDoPost {
	public static void main(String[] args) {
		WebTarget url;
		Client client = ClientBuilder.newClient();
		url = client.target("http://localhost:8080/Fluxo/Post/1");

		Response response = url.request().get();
		Post post = response.readEntity(Post.class);
		
		Base64.Decoder decoder = Base64.getMimeDecoder();
		byte[] decodedByteArray = decoder.decode(post.getImagem());
		
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(decodedByteArray));
			JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
