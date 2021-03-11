package ru.padegfio.html.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.padegfio.html.client.dto.PadegFioResponseDto;

@FeignClient(name = "padeg", url = "${padeg.url}")
public interface PadegClient {

    @RequestMapping(method = RequestMethod.GET, value = "/GetFIOPadegFSAS/{fio}/{padegNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    PadegFioResponseDto getDecl(@PathVariable("fio") String fio, @PathVariable("padegNumber") int padegNumber);

}
