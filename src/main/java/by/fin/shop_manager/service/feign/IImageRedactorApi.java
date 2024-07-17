package by.fin.shop_manager.service.feign;

import by.fin.shop_manager.core.dto.ImageProcessingRequestDto;
import by.fin.shop_manager.core.dto.ImageProcessingResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "removeBgClient", url = "https://api.remove.bg/v1.0")
public interface IImageRedactorApi {

    @PostMapping(value = "/removebg", consumes = "application/json", produces = "application/json")
    ImageProcessingResponseDto processImage(
            @RequestHeader("X-Api-Key") String apiKey,
            @RequestBody ImageProcessingRequestDto dto
    );
}
