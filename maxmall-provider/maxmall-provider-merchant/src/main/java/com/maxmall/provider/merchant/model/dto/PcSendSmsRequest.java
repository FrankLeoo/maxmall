
package com.maxmall.provider.merchant.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Pc send sms request.
 *
 * @author maxmall.net@gmail.com
 */
@Data
public class PcSendSmsRequest implements Serializable {

	private static final long serialVersionUID = 8391763073747981998L;

	private String phoneNumbers;
	private String templateCode;
	private String templateParam;
	private String outId;
}
