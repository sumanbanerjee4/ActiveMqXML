package activeMq.serviceProvider;

import javax.jms.JMSException;
import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
class Consumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

private String correlationId;
	@Autowired
	private JmsTemplate jmsTemplate;



	@JmsListener(destination = "Q2")
	public void receive(Message message) throws JmsException, JMSException {
		LOGGER.info("-------------------------------Consumer---------------------------------");
		LOGGER.info("Listening on Q2");
		LOGGER.info("received message='{}'", message);
		System.out.println(message.getJMSCorrelationID());
		correlationId=message.getJMSCorrelationID();
	
		
		String scanResult="<?xml version=\"1.0\"?>\r\n" + "<Scanresponse>pass</Scanresponse>";
		
		LOGGER.info("Sending message to Q3");

		LOGGER.info("sending message='{}'", scanResult + "    CorrelationID : " + correlationId);

		jmsTemplate.convertAndSend("Q3", scanResult , m -> {

			LOGGER.info("setting standard JMS headers before sending");
			m.setJMSCorrelationID(correlationId);
			return m;
		});

	}
}
