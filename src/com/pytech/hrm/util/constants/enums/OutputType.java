package com.pytech.hrm.util.constants.enums;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OutputType {
	jpg(ImageType.JPG),bmp(ImageType.BMP),png(ImageType.PNG),
	tga(ImageType.TGA),tiff(ImageType.TIFF),
	exr(ImageType.EXR),animation(ImageType.PNG),SCENE_DEFINE(null);	
	private ImageType imageType;
	private OutputType(ImageType imageType) {
		this.imageType = imageType;
	}
	public ImageType getComeOutputType() {
		return this.imageType;
	}
}
