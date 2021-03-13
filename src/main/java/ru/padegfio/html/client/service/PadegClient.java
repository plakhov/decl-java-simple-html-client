package ru.padegfio.html.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.padegfio.html.client.dto.PadegResponseDto;

@FeignClient(name = "padeg", url = "${padeg.url}")
public interface PadegClient {

    @RequestMapping(method = RequestMethod.GET, value = "/GetFIOPadegFSAS/{fio}/{padegNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    PadegResponseDto getFioDecl(@PathVariable("fio") String fio, @PathVariable("padegNumber") int padegNumber);

    @RequestMapping(method = RequestMethod.GET, value = "/GetAppointmentPadeg/{prof}/{padegNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    PadegResponseDto getProfessionDecl(@PathVariable("prof") String prof, @PathVariable("padegNumber") int padegNumber);

    @RequestMapping(method = RequestMethod.GET, value = "/GetOfficePadeg/{office}/{padegNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    PadegResponseDto getOfficeDecl(@PathVariable("office") String office, @PathVariable("padegNumber") int padegNumber);

}
