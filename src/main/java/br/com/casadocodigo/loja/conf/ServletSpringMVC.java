package br.com.casadocodigo.loja.conf;

import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	private Logger logger = Logger.getLogger(ServletSpringMVC.class);

	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.warn("DB_URL " + System.getProperty("DATABASE_URL") + "====");
		logger.warn("AMbiente " + System.getProperty("SPRING_PROFILES_ACTIVE")
				+ "====");
		System.out.println("DB_URL " + System.getProperty("DATABASE_URL") + "====");
		System.out.println("AMbiente " + System.getProperty("SPRING_PROFILES_ACTIVE")
				+ "====");
		return new Class[] { SecurityConfiguration.class,
				AppWebConfiguration.class, JPAConfiguration.class,
				JPAProductionConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Tem que colocar aqui para ser adicionado no carregamento da servlet
		// base
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		super.customizeRegistration(registration);
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter(
				AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");

	}

	public static void main(String[] args) {
		System.out.println(System.getenv("DATABASE_URL"));
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new OpenEntityManagerInViewFilter() };
	}

}
