package pacote.configuracao;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("")
public class ConfigsJersey extends ResourceConfig{

	public ConfigsJersey() {
		packages("pacote.resources");
		register(CharsetResponseFilter.class);
	}
}
