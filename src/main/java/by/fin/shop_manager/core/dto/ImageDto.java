package by.fin.shop_manager.core.dto;

import by.fin.shop_manager.exception.CannotReadImageFileException;
import by.fin.shop_manager.exception.ImageFileNotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ImageDto {
    @JsonProperty("image_name")
    private String name;

    @JsonProperty("image_data")
    private String data;

    @JsonProperty("path_to_image")
    private String path;

    public ImageDto() {
    }

    public ImageDto(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    @JsonSetter
    public void setPath(String path) {
        this.path = path;

        if(data == null || data.isEmpty()) {
            this.data = readImageDataFromFile(path);
        }
    }

    private String readImageDataFromFile(String path) {
        File file = new File(path);
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (FileNotFoundException e) {
            throw new ImageFileNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new CannotReadImageFileException(e.getMessage());
        }
    }
}
