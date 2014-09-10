package com.pytech.hrm.util.constants.enums;

public enum ImageType {
	JPG("jpg"),
	BMP("bmp"),
	PNG("png"),
	TGA("tga"),
	TIFF("tif"),
	EXR("exr");
	
    private final String value;
    private ImageType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
	
}
