package com.example.shopquanao.enums;

public enum SettingKeyEnum {
	SITE_NAME("SITE_NAME"), 
    SITE_DESCRIPTION("SITE_DESCRIPTION"), 
    SITE_LOGO("SITE_LOGO"), 
    SITE_FAVICON("SITE_FAVICON"), 
    SITE_SLOGAN("SITE_SLOGAN"), 
    SITE_KEYWORDS("SITE_KEYWORDS"), 
    SITE_URL("SITE_URL"), 
    CONTACT_NAME("CONTACT_NAME"), 
    CONTACT_EMAIL("CONTACT_EMAIL"), 
    CONTACT_EMAIL_CUSTOMER_SERVICE("CONTACT_EMAIL_CUSTOMER_SERVICE"), 
    CONTACT_PHONE("CONTACT_PHONE"), 
    CONTACT_PHONE_CUSTOMER_SERVICE("CONTACT_PHONE_CUSTOMER_SERVICE"), 
    CONTACT_ADDRESS("CONTACT_ADDRESS"), 
    SOCIAL_FACEBOOK("SOCIAL_FACEBOOK"), 
    SOCIAL_ZALO("SOCIAL_ZALO"), 
    SOCIAL_TWITTER("SOCIAL_TWITTER"), 
    SOCIAL_INSTAGRAM("SOCIAL_INSTAGRAM"), 
    SOCIAL_YOUTUBE("SOCIAL_YOUTUBE"), 
    LEGAL_PRIVACY_POLICY("LEGAL_PRIVACY_POLICY"),
    LEGAL_TERMS_OF_SERVICE("LEGAL_TERMS_OF_SERVICE"),
    LEGAL_REFUND_POLICY("LEGAL_REFUND_POLICY"),
    LEGAL_INVESTOR_RELATIONS("LEGAL_INVESTOR_RELATIONS");
	private final String value;

	SettingKeyEnum(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
	
}
