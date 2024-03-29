package com.silvionetto.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQReceive {

    private static final String QUEUE_DESTINATION = "QL.CALYPSO.TRADEBUS.BONDS.REPLY";

    private static String brokerURL = "tcp://localhost:61616";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient Session session;

    public MQReceive() throws JMSException {
	factory = new ActiveMQConnectionFactory(brokerURL);
	connection = factory.createConnection();
	connection.start();
	session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public void close() throws JMSException {
	if (connection != null) {
	    connection.close();
	}
    }

    public static void main(String[] args) throws JMSException {
	MQReceive consumer = new MQReceive();
	Destination destination = consumer.getSession().createQueue(
		QUEUE_DESTINATION);
	MessageConsumer messageConsumer = consumer.getSession().createConsumer(
		destination);
	messageConsumer.setMessageListener(new Listener());
    }

    public Session getSession() {
	return session;
    }
}
