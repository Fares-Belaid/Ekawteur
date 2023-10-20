package com.ekwateur.testTechnique.service.Impl;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.Client;
import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;
import com.ekwateur.testTechnique.dao.repository.ClientRepository;
import com.ekwateur.testTechnique.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private final Random random = new Random();

    @Override
    public void saveParticulierClient(ParticulierClient particularClient) {
        particularClient.setClientReference(generateClientReference("EKW"));
        if (clientAlreadyExists(particularClient)) {
            throw new IllegalArgumentException("Client already exists");
        }
        clientRepository.save(particularClient);
    }

    @Override
    public void saveProClient(ProClient proClient) {
        proClient.setClientReference(generateClientReference("EKW"));
        if (clientAlreadyExists(proClient)) {
            throw new IllegalArgumentException("Client already exists");
        }
        clientRepository.save(proClient);
    }

    private String generateClientReference(String prefix) {
        return prefix + String.format("%08d", random.nextInt(100000000));
    }

    private boolean clientAlreadyExists(Client client) {
        return clientRepository.findByClientReference(client.getClientReference()) != null;
    }

}
