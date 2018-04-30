package com.vrtime.restclient.input;

import java.util.List;

import com.vrtime.restclient.shared.SSOSubAccount;

public interface InputReader {

	public List<SSOSubAccount> produceSubAccounts();

}
