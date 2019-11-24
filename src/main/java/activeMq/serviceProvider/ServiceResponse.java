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

import activeMq.Repo.QModel;
import activeMq.Repo.QRepo;
import activeMq.Repo.ScanRequestModel;
import activeMq.Repo.ScanRequestRepo;

@Component
public class ServiceResponse {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceResponse.class);

	@Autowired
	ScanRequestRepo scanRequestRepo;

	@Autowired
	ScanRequestModel ScanRequestModel;

	@Autowired
	QModel QModel;

	@Autowired
	QRepo QRepo;

	@Autowired
	private JmsTemplate jmsTemplate;

	@JmsListener(destination = "Q3")
	public void receive(Message message) throws JmsException, JMSException {
		
		LOGGER.info("-------------------------------Response Flow---------------------------------");
		LOGGER.info("Listening on Q3");
		LOGGER.info("Received Q3 message='{}'", message);
		LOGGER.info("CorrelationID of Request : ",message.getJMSCorrelationID());
		int transRef = Integer.parseInt(message.getJMSCorrelationID());
		ScanRequestModel = scanRequestRepo.findByTrnsRef(transRef);
		LOGGER.info("ProfileId of the Requested System : ",ScanRequestModel.getProfileId());
		String profileId = ScanRequestModel.getProfileId();
		QModel = QRepo.findByProfileId(profileId.trim());
		
		LOGGER.info("Sending Data to designated Queue");
		LOGGER.info(QModel.getqName().trim());
		jmsTemplate.convertAndSend(QModel.getqName().trim(), message);

	}

}
