package com.silvionetto.mq;

import java.io.FileWriter;
import java.io.IOException;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener {

    private static int count = 1000;

    public void onMessage(Message message) {
	try {
	    System.out.println("Message: " + ((TextMessage) message).getText());
	    stringToFile(((TextMessage) message).getText());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void stringToFile(String message) {
	try {
	    FileWriter fw = new FileWriter("Reply" + count++ + ".xml");
	    fw.write(message);
	    fw.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
