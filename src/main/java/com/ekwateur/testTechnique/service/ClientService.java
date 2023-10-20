package com.ekwateur.testTechnique.service;

import com.ekwateur.testTechnique.dao.models.ParticulierClient;
import com.ekwateur.testTechnique.dao.models.ProClient;

public interface ClientService {
    void saveProClient(ProClient proClient);
    void saveParticulierClient(ParticulierClient particularClient);

}
