package edunote.configuration;

import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "edunote.repositorios",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager")
@EnableTransactionManagement
@SuppressWarnings("unused")
public class JpaConfiguration {

	@Autowired
	private Environment environment;

	@Value("${datasource.sampleapp.maxPoolSize:10}")
	private int maxPoolSize;

	/*
	 * El object DataSourceProperties de SpringBoot esta basaddo en prefix
	 * directamente desde application.yml. 
	 * Gracias a .yml, Los datos jerarquicos son mapeados fuera de la caja con 
	 * las propiedades de matching-name del Objecto DataSourceProperties ].
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}

	/*
	 * Configurar el DataSource HikariCP pooled.
	 */
	@Bean
	public DataSource dataSource() {
		DataSourceProperties ds = dataSourceProperties();
		
			return DataSourceBuilder
					.create(ds.getClassLoader())
					.driverClassName(ds.getDataPassword())
					.url(ds.getUrl())
					.username(ds.getUsername())
					.password(ds.getPassword())
//					.url("jdbc:postgresql://ec2-54-243-203-93.compute-1.amazonaws.com:5432/d76hmjao034ql8?sslmode=require")
//					.username("pgkfagpxlkjrqg")
//					.password("kYjNj5RR6RRg0joaC5IzRmx9nr")
					.build();
//			return dataSource;
	}

	/*
	 * Entity Manager Factory setup.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(new String[] { "edunote.pojos" });
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}

	/*
	 * Proveedor de Adaptador especifico
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}
	/*
	 * Aqui puedes especificar cualquier proveedor de propiedades especificas.
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		if(StringUtils.isNotEmpty("")){
			properties.put("hibernate.default_schema", null);
		}
		return properties;
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

}
