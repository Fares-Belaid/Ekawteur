# Ekawteur

## Clarification:
L'énoncé est générique et un peu ambigu, c'est pour cela j'ai préparé 2 solutions concernant les factures:
  - une première solution où j'ai supposé que le frontend va entrer la référence de clients et les infos de facture y compris la quantité de consommation et que le client à une seule facture pour un mois et     année (exp en mars 2023).
- Une deuxième solution où j'ai supposé que facture est déjà créée dans la base de données mais manque le calcul de montant, dans ce cas le frontend entre la référence de client et la date de facture.
## NB: - J'ai préparé une collection Postman que sera envoyé dans l'email.
       
## L'énoncé:
Le product owner te demande de developper un programme qui permet de calculer le montant à facturer à un client d'Ekwateur pour un mois calendaire.

Ce programme devra gérer 2 types de clients :

A) Les clients Pro, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- N° SIRET
- Raison Sociale
- CA

B) Les particuliers, qui ont les propriétés suivantes :
- Reference Client (EKW + 8 caractères numériques)
- Civilité
- Nom
- Prénom

Un client peut consommer deux types d'énergies :
- Electricité
- Gaz

Chaque énergie est facturée au kWh.
- Pour les particuliers, le prix du kWh est de 0,121 € pour l'électricité et 0,115€ pour le gaz
- Pour les pro, ayant un CA supérieur à 1 000 000 €, le prix du kWh est de 0,114 € pour l'électricité et 0,111€ pour le gaz
- Pour les pro, ayant un CA inférieur à 1 000 000 €, le prix du kWh est de 0,118 € pour l'électricité et 0,113€ pour le gaz

## Contraintes techniques
La seule contrainte technique est l'utilisation du langage Java dans sa version 8 au minimum.
