package es.indra.formacion.pr.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.indra.formacion.pr.spring.bean.Dato;
import es.indra.formacion.pr.spring.bean.IHolaMundo;

public class Test {
	public static void main(String[] args) {
		System.out.println("/***** CONTEXTO (CONTENEDOR IoC) *****/");
		System.out.println("Contexto creando...");
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("es/indra/formacion/pr/spring/config/applicationContext.xml");
		System.out.println("Contexto creado...");
		
		System.out.println("/***** SINGLETON *****/");
		IHolaMundo hm1 = context.getBean("holaMundo1", IHolaMundo.class); // No hay new!
		System.out.println("HolaMundo solicitado...");
		hm1.imprimir(); // => Hola Mundo
		
		IHolaMundo hm2 = context.getBean("holaMundo1", IHolaMundo.class);
		hm2.setDato(new Dato("a Todos"));
		
		hm1.imprimir(); // => Hola a Todos

		hm2 = context.getBean("holaMundo2", IHolaMundo.class);
		hm2.imprimir(); // => Hello Life

		System.out.println("/***** PROTOTYPE *****/");
		IHolaMundo hm3 = context.getBean("holaMundo3", IHolaMundo.class);
		hm3.imprimir(); // => Hola Uno
		hm3.setDato(new Dato("Dos"));
		hm3.imprimir(); // => Hola Dos
		
		IHolaMundo hm4 = context.getBean("holaMundo3", IHolaMundo.class); // Devuelve una nueva instancia, porque es prototype!!!
		hm4.imprimir(); // => Hola Uno
		
		System.out.println("/***** REFERCENCIAS PROTOTYPE A SINGLETON *****/");
		IHolaMundo hm5 = context.getBean("holaMundo4", IHolaMundo.class);
		hm5.getDato().setContenido("Cambiado");
		hm5.imprimir(); // => Hola Cambiado
		
		IHolaMundo hm6 = context.getBean("holaMundo4", IHolaMundo.class);
		hm6.imprimir(); // => Hola Cambiado
		
		System.out.println("/***** REFERCENCIAS PROTOTYPE A PROTOTYPE *****/");
		IHolaMundo hm7 = context.getBean("holaMundo5", IHolaMundo.class);
		hm7.getDato().setContenido("Cambiado");
		hm7.imprimir(); // => Hola Cambiado
		
		IHolaMundo hm8 = context.getBean("holaMundo5", IHolaMundo.class);
		hm8.imprimir(); // => Hola Mundo
	}
}
