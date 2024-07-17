package by.fin.shop_manager.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageProcessingResponseDto {
    private Data data;

    public static class Data {
        @JsonProperty("result_b64")
        private String resultB64;

        @JsonProperty("foreground_top")
        private int foregroundTop;

        @JsonProperty("foreground_left")
        private int foregroundLeft;

        @JsonProperty("foreground_width")
        private int foregroundWidth;

        @JsonProperty("foreground_height")
        private int foregroundHeight;

        public String getResultB64() {
            return resultB64;
        }

        public Data setResultB64(String resultB64) {
            this.resultB64 = resultB64;
            return this;
        }

        public int getForegroundTop() {
            return foregroundTop;
        }

        public Data setForegroundTop(int foregroundTop) {
            this.foregroundTop = foregroundTop;
            return this;
        }

        public int getForegroundLeft() {
            return foregroundLeft;
        }

        public Data setForegroundLeft(int foregroundLeft) {
            this.foregroundLeft = foregroundLeft;
            return this;
        }

        public int getForegroundWidth() {
            return foregroundWidth;
        }

        public Data setForegroundWidth(int foregroundWidth) {
            this.foregroundWidth = foregroundWidth;
            return this;
        }

        public int getForegroundHeight() {
            return foregroundHeight;
        }

        public Data setForegroundHeight(int foregroundHeight) {
            this.foregroundHeight = foregroundHeight;
            return this;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getImageData() {
        return data.getResultB64();
    }
}
