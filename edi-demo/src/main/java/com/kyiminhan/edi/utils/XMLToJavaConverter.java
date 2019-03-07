package com.kyiminhan.edi.utils;

import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import org.milyn.Smooks;
import org.milyn.payload.JavaResult;
import org.xml.sax.SAXException;

import com.kyiminhan.edi.bean.Order;

public class XMLToJavaConverter {

	private static volatile XMLToJavaConverter instance;

	private XMLToJavaConverter() {

	}

	public static XMLToJavaConverter getInstance() {
		if (null == instance) {
			synchronized (XMLToJavaConverter.class) {
				instance = new XMLToJavaConverter();
			}
		}
		return instance;
	}

	public Order converOrderXMLToOrderObject(String path) throws IOException, SAXException {

		Smooks smooks = new Smooks(this.getClass().getResourceAsStream("/smooks-mapping.xml"));
		try {
			JavaResult javaResult = new JavaResult();
			smooks.filterSource(new StreamSource(this.getClass().getResourceAsStream(path)), javaResult);
			return (Order) javaResult.getBean("order");
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			smooks.close();
		}
		return null;
	}
}