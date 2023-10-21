# Ekawteur

## Clarification:
L'énoncé est générique et un peu ambigu, c'est pour cela j'ai préparé 2 solutions à travers plusieurs APIs:
  - Une première solution où j'ai supposé que l'utilisateur fait rentrer la référence de clients et les infos de facture y compris la quantité consommée à travers le frontend et que chaque client à une seule facture par mois et année à générer (ex : mars 2023).
- Une deuxième solution où j'ai supposé que les quantités existent dans une table de base de données, les montants sont calculés à la demande de l'utilisateur et enregistrés dans la table facture déjà créée. L'utilisateur envoie la référence client et la date à travers le frontend, le calcul sera fait et enregistré dans la table facture, ensuite on fait le retour de facture par l'API.
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
