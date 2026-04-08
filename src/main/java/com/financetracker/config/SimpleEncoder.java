package com.financetracker.config;

public class SimpleEncoder {

	public String encode(String password) {
		// Simple reversible encoding, e.g., Base64
		return java.util.Base64.getEncoder().encodeToString(password.getBytes());
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}
}