package sep.secondbank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sep.secondbank.dtos.CardInfoDTO;
import sep.secondbank.dtos.MerchantCredentialsDTO;
import sep.secondbank.dtos.PCCRequestDTO;
import sep.secondbank.dtos.PCCResponseDTO;
import sep.secondbank.exceptions.*;
import sep.secondbank.model.Account;
import sep.secondbank.model.Invoice;
import sep.secondbank.service.AccountService;
import sep.secondbank.service.InvoiceService;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final InvoiceService invoiceService;

    public AccountController(AccountService accountService, InvoiceService invoiceService) {
        this.accountService = accountService;
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            Account account = accountService.getById(id);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (AccountNotFoundException e){
            return ResponseEntity.badRequest().body("Account does not exist.");
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@Valid @RequestBody CardInfoDTO dto){
        try {
            return ResponseEntity.ok(accountService.register(dto));
        } catch (CreditCardNotFoundException | AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (CreditCardInfoNotValidException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "payment/{invoiceId}")
    public ResponseEntity<?> pay(@Valid @RequestBody CardInfoDTO dto, @PathVariable long invoiceId){
        try {
            Invoice invoice = accountService.getInvoice(invoiceId);
            try {
                invoice = accountService.pay(dto, invoice);
                return ResponseEntity.ok(invoiceService.notifySuccess(invoice, ""));
            } catch (InvoiceAlreadyPaidException | NoMoneyException | ExternalTransferException e) {
                return ResponseEntity.ok(invoiceService.notifyFailure(invoice, e.getMessage()));
            } catch (CreditCardInfoNotValidException | CreditCardNotFoundException e) {
                return ResponseEntity.ok(invoiceService.notifyError(invoice, e.getMessage()));
            }
        } catch (InvoiceNotFoundException | CurrencyUnsupportedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("receiveRequestFromPCC")
    public PCCResponseDTO receivePccRequest(@RequestBody PCCRequestDTO dto) {
        return accountService.receiveRequestFromPcc(dto);
    }

    @PostMapping("validate")
    public void validate(@RequestBody MerchantCredentialsDTO dto) throws AccountNotFoundException {
        accountService.validate(dto);
    }

}
