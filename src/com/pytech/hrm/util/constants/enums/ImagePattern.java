package com.pytech.hrm.util.constants.enums;

public enum ImagePattern {
    NAME_EXT_INDEX("name.ext.index"),
    NAME_INDEX_EXT("name.index.ext");

    private String imgPattern;
    private ImagePattern(String imgPattern) {
        this.imgPattern = imgPattern;
    }
    
    @Override
    public String toString() {
        return this.imgPattern;
    }
}
