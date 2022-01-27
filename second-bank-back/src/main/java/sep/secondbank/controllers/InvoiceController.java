package sep.secondbank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sep.secondbank.dtos.InvoiceCustomerInfoDTO;
import sep.secondbank.dtos.InvoiceDTO;
import sep.secondbank.dtos.InvoiceResponseDTO;
import sep.secondbank.exceptions.CurrencyUnsupportedException;
import sep.secondbank.exceptions.InvoiceNotFoundException;
import sep.secondbank.service.InvoiceService;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("generate")
    public InvoiceResponseDTO generate(@RequestBody InvoiceDTO dto) throws AccountNotFoundException, CurrencyUnsupportedException {
        return invoiceService.generateResponse(dto);
    }

    @GetMapping("{id}")
    public InvoiceCustomerInfoDTO get(@PathVariable long id) {
        try {
            return invoiceService.get(id);
        } catch (InvoiceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invoice not found");
        }
    }
}
