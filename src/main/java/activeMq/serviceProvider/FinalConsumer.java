package activeMq.serviceProvider;

import javax.jms.JMSException;

import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class FinalConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(FinalConsumer.class);

	


		@JmsListener(destination = "Q4")
		public void receive(Message message) throws JmsException, JMSException {
			LOGGER.info("-------------------------------FinalConsumer---------------------------------");
			LOGGER.info("Listening on Q4");
			LOGGER.info("Received message='{}'", message);
			
		}
}
