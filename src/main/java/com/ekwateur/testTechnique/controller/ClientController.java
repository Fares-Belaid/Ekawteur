package com.ekwateur.testTechnique.controller;

import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;
import com.ekwateur.testTechnique.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/pro")
    public ResponseEntity<Void> addProClient(@RequestBody ProClient proClient) {
        clientService.saveProClient(proClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/particulier")
    public ResponseEntity<Void> addParticulierClient(@RequestBody ParticulierClient particulierClient) {
        clientService.saveParticulierClient(particulierClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
