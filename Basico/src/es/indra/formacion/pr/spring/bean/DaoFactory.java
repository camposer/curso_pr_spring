package es.indra.formacion.pr.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DaoFactory implements ApplicationContextAware {
	private ApplicationContext context;
	
	public Dao createDao() {
		return context.getBean("dao", Dao.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
	}
}
