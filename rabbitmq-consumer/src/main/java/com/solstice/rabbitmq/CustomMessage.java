package com.solstice.rabbitmq;

import java.io.Serializable;

public class CustomMessage implements Serializable {
	private String a;
	private String b;

	public CustomMessage() {

	}

	public CustomMessage(String a, String b) {
		this.a = a;
		this.b = b;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}
