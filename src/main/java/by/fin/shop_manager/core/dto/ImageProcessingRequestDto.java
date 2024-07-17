package by.fin.shop_manager.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageProcessingRequestDto {
    @JsonProperty("image_file_b64")
    private String imageFileB64 = "";

    @JsonProperty("image_url")
    private String imageUrl = "";

    @JsonProperty("image_file")
    private String imageFile = "";

    @JsonProperty(defaultValue = "preview")
    private String size = "preview";

    @JsonProperty(defaultValue = "auto")
    private String type = "auto";

    @JsonProperty("type_level")
    private String typeLevel = "1";

    @JsonProperty(defaultValue = "auto")
    private String format = "auto";

    @JsonProperty(defaultValue = "0% 0% 100% 100%")
    private String roi = "0% 0% 100% 100%";

    @JsonProperty(defaultValue = "false")
    private boolean crop = false;

    @JsonProperty("crop_margin")
    private String cropMargin = "0";

    @JsonProperty(defaultValue = "original")
    private String scale = "original";

    @JsonProperty(defaultValue = "original")
    private String position = "original";

    @JsonProperty(defaultValue = "rgba")
    private String channels = "rgba";

    @JsonProperty("add_shadow")
    private boolean addShadow = true;

    @JsonProperty("shadow_type")
    private String shadowType = "";

    @JsonProperty("shadow_opacity")
    private String shadowOpacity = "string";

    @JsonProperty(defaultValue = "true")
    private boolean semitransparency = true;

    @JsonProperty("bg_color")
    private String bgColor = "";

    @JsonProperty("bg_image_url")
    private String bgImageUrl = "";

    public String getImageFileB64() {
        return imageFileB64;
    }

    public void setImageFileB64(String imageFileB64) {
        this.imageFileB64 = imageFileB64;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(String type_level) {
        this.typeLevel = typeLevel;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public boolean isCrop() {
        return crop;
    }

    public void setCrop(boolean crop) {
        this.crop = crop;
    }

    public String getCropMargin() {
        return cropMargin;
    }

    public void setCropMargin(String cropMargin) {
        this.cropMargin = cropMargin;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public boolean isAddShadow() {
        return addShadow;
    }

    public void setAddShadow(boolean addShadow) {
        this.addShadow = addShadow;
    }

    public String getShadowType() {
        return shadowType;
    }

    public void setShadowType(String shadowType) {
        this.shadowType = shadowType;
    }

    public String getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(String shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public boolean isSemitransparency() {
        return semitransparency;
    }

    public void setSemitransparency(boolean semitransparency) {
        this.semitransparency = semitransparency;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getBgImageUrl() {
        return bgImageUrl;
    }

    public void setBgImageUrl(String bgImageUrl) {
        this.bgImageUrl = bgImageUrl;
    }

}
