package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "hello" })
@EnableJpaRepositories(basePackages = { "hello" })
@EnableTransactionManagement
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
    @Autowired
	CustomerRepository repository;
    
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
    Model model) {
    model.addAttribute("name", name);
    log.info("In Greeting");
    repository.save(new Customer("Jack", "Bauer"));
	repository.save(new Customer("Chloe", "O'Brian"));
	repository.save(new Customer("Kim", "Bauer"));
	repository.save(new Customer("David", "Palmer"));
	repository.save(new Customer("Michelle", "Dessler"));
	repository.save(new Customer("Sreekanth", "Akula"));
	repository.save(new Customer("Kiran", "Akula"));
	repository.save(new Customer("Komala", "Akula"));
	
	// fetch all customers
	log.info("Customers found with findAll():");
	log.info("-------------------------------");
	for (Customer customer : repository.findAll()) {
		log.info(customer.toString());
	}
				
    return "greeting";
    }
   

	/*@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
			repository.save(new Customer("Sreekanth", "Akula"));
			repository.save(new Customer("Kiran", "Akula"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
			log.info("Customer found with findByLastName('Akula'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Akula")) {
				log.info(bauer.toString());
			}
			log.info("");
			log.info("Customer found with findByFristName('Sreekanth'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByFirstName("Sreekanth")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}*/

}
