package activeMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import activeMq.scanRequest.SimulateScanRequest;
@EnableConfigurationProperties
@SpringBootApplication
public class ActiveMqApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(ActiveMqApplication.class);
	
	@Autowired
	SimulateScanRequest s;
	public static void main(String[] args) {
		SpringApplication.run(ActiveMqApplication.class, args);
		logger.info("Aplication started");
	}

	@Override
	public void run(String... args) throws Exception {
		
		s.send(	"<?xml version=\"1.0\"?>\n" + 
				"<HotScan>\n" + 
				"<ProfileID>\n" + 
				"     AA\n" + 
				" </ProfileID>\n" + 
				"<TransRef>\n" + 
				"     0001\n" + 
				" </TransRef>\n" + 
				"<ScanData>AnyData</ScanData>\n" + 
				"</HotScan>");
		
	}

}
