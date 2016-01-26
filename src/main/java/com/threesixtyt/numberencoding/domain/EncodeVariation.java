package com.threesixtyt.numberencoding.domain;

import java.util.List;

public class EncodeVariation {
	private String phoneNumber;
	private List<String> encodedList;
	public EncodeVariation(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public List<String> getEncodedList() {
		return encodedList;
	}
	public void setEncodedList(List<String> encodedList) {
		this.encodedList = encodedList;
	}
}
