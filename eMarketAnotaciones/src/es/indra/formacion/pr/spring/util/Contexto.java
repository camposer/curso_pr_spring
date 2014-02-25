package es.indra.formacion.pr.spring.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class Contexto {
	private static ApplicationContext context = 
			new ClassPathXmlApplicationContext("es/indra/formacion/pr/spring/config/applicationContext.xml");
	
	public static ApplicationContext getInstance() {
		return context;
	}
}
