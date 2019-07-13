package com.maxmall.common.config.properties;

import lombok.Data;

/**
 * The class Async task properties.
 *
 * @author maxmall.net @gmail.com
 */
@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "无痕";

	private String contactUrl = "http://maxmall.net";

	private String contactEmail = "maxmall.net@gmail.com";
}
