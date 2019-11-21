package activeMq.consumerProvider;

import java.io.StringReader;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.jms.annotation.JmsListener;

import activeMq.Repo.ScanRequestModel;
import activeMq.Repo.ScanRequestRepo;

import activeMq.scanRequest.HotScan;

public class ServiceProvider {
	@Autowired
	ScanRequestRepo scanRequestRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProvider.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	/*private static Document convertStringToXMLDocument(String xmlString) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;
		try {

			builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = (org.w3c.dom.Document) builder
					.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	@JmsListener(destination = "Q1")
	public void receive(String message) {
		LOGGER.info("received message='{}'", message);
		latch.countDown();
		//Document doc = convertStringToXMLDocument(message);

	
	JAXBContext jaxbContext;
	try
	{
	    jaxbContext = JAXBContext.newInstance(HotScan.class);              
	 
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	 
	    HotScan hotScan= (HotScan) jaxbUnmarshaller.unmarshal(new StringReader(message));
	     
	    System.out.println(hotScan);
	    
	  
	    scanRequestRepo.save(new ScanRequestModel(hotScan.getTransRef(),hotScan.getProfileID(),new Timestamp(System.currentTimeMillis())));
	}
	catch (JAXBException e) 
	{
	    e.printStackTrace();
	}


	}
	
	
	
	}
