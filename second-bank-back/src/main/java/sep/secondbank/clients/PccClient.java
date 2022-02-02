package sep.secondbank.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sep.secondbank.dtos.PCCRequestDTO;
import sep.secondbank.dtos.PCCResponseDTO;

import java.net.URI;

@FeignClient(name="b", url="b")
public interface PccClient {

    @PostMapping
    PCCResponseDTO bankPaymentResponse(URI baseUri, @RequestBody PCCRequestDTO dto);
}
