package pacote.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("")
public class GeralResourceConfig extends ResourceConfig {

	public GeralResourceConfig() {
		packages("pacote.resources");
		// Reistra filtro que altera o CHARSET
		register(CharsetResponseFilter.class);
	}
}
