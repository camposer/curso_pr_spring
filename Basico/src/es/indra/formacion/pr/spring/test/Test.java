package es.indra.formacion.pr.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.indra.formacion.pr.spring.bean.HolaMundo;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("es/indra/formacion/pr/spring/config/applicationContext.xml");
		
		HolaMundo hm = context.getBean("holaMundo", HolaMundo.class); // No hay new!
		hm.imprimir();
	}
}
