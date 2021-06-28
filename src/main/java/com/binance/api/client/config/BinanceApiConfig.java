package com.binance.api.client.config;

/**
 * Configuration used for Binance operations.
 */
public class BinanceApiConfig {

	/**
	 * Base domain for URLs.
	 */
	private static String BASE_DOMAIN = "binance.com";
	// sinhlt start
	private static String TESTNET_DOMAIN = "binancefuture.com";
	// sinhlt end

	/**
	 * Set the URL base domain name (e.g., binance.com).
	 *
	 * @param baseDomain Base domain name
	 */
	public static void setBaseDomain(final String baseDomain) {
		BASE_DOMAIN = baseDomain;
	}

	/**
	 * Get the URL base domain name (e.g., binance.com).
	 *
	 * @return The base domain for URLs
	 */
	public static String getBaseDomain() {
		return BASE_DOMAIN;
	}

	/**
	 * REST API base URL.
	 */
	public static String getApiBaseUrl() {
		return String.format("https://api.%s", getBaseDomain());
	}

	// sinhlt start
	/**
	 * Future REST API base URL.
	 */
	public static String getFutureApiBaseUrl() {
		return String.format("https://fapi.%s", getBaseDomain());
	}

	public static String getFutureStreamApiBaseUrl() {
		return String.format("wss://fstream.%s/ws", getBaseDomain());
	}
	public static String getTestnetFutureApiBaseUrl() {
		return String.format("https://testnet.%s", getBaseDomain());
	}

	public static String getTestnetFutureStreamApiBaseUrl() {
		return String.format("wss://stream.%s/ws", getBaseDomain());
	}
	// sinhlt end

	/**
	 * Streaming API base URL.
	 */
	public static String getStreamApiBaseUrl() {
		return String.format("wss://stream.%s:9443/ws", getBaseDomain());
	}

	/**
	 * Asset info base URL.
	 */
	public static String getAssetInfoApiBaseUrl() {
		return String.format("https://%s/", getBaseDomain());
	}

}
