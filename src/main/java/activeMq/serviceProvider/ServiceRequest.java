package activeMq.serviceProvider;

import java.io.StringReader;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import activeMq.Repo.ScanRequestModel;
import activeMq.Repo.ScanRequestRepo;

import activeMq.scanRequest.HotScan;

public class ServiceRequest {
	@Autowired
	ScanRequestRepo scanRequestRepo;
	@Autowired
	private JmsTemplate jmsTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequest.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@JmsListener(destination = "Q1")
	public void receive(String message) {

		LOGGER.info("-------------------------------Request Flow---------------------------------");
		LOGGER.info("Listening on Q1");
		LOGGER.info("Request Recieved from System ", message);
		latch.countDown();

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(HotScan.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			HotScan hotScan = (HotScan) jaxbUnmarshaller.unmarshal(new StringReader(message));
			
			LOGGER.info("Persisting Request data in DB ");

			scanRequestRepo.save(new ScanRequestModel(hotScan.getTransRef(), hotScan.getProfileID(),
					new Timestamp(System.currentTimeMillis())));

			String msg = "<?xml version=\"1.0\"?>\r\n" + "<ProfileId>" + hotScan.getProfileID() + "</ProfileId>";
			
			
			LOGGER.info("Sending message to Q2='{}'", msg + "    TransRef :  " + hotScan.getTransRef());

			jmsTemplate.convertAndSend("Q2", msg, m -> {

				LOGGER.info("setting standard JMS headers before sending");
				m.setJMSCorrelationID(String.valueOf(hotScan.getTransRef()));
				return m;
			});

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
