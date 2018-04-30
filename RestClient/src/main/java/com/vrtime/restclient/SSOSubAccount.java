package com.vrtime.restclient;

public class SSOSubAccount {

	private String correclationId;
	private String countryCode;
	private String source;
	private String target;
	private String customerId;

	public SSOSubAccount(String correclationId, String countryCode, String source, String target, String customerId) {
		super();
		this.correclationId = correclationId;
		this.countryCode = countryCode;
		this.source = source;
		this.target = target;
		this.customerId = customerId;
	}

	public String getCorreclationId() {
		return correclationId;
	}

	public void setCorreclationId(String correclationId) {
		this.correclationId = correclationId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "SSOSubAccount [correclationId=" + correclationId + ", countryCode=" + countryCode + ", source=" + source
				+ ", target=" + target + ", customerId=" + customerId + "]";
	}

}
