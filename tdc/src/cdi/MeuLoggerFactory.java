package cdi;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import tdc.MeuLogger;

public class MeuLoggerFactory {

	@Produces
	public MeuLogger criaMeuLogger(InjectionPoint point) {
		// InjectionPoint point consigo dados de quem esta querendo que o CDI
		// injete esse obj
		Class<?> classeComInjecao = point.getMember().getDeclaringClass();
		return new MeuLogger(classeComInjecao);
	}
}
