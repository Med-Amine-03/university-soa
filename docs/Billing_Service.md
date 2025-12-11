# Manuel d'utilisation -- APIs SOAP et REST

## 📌 BillingService (API SOAP)

### 1. Prérequis

-   Le projet SOAP doit être démarré et écouter sur :\
    **http://localhost:5120/BillingService.svc**
-   Installer **Thunder Client** (VS Code) ou **Postman**.
-   Méthodes disponibles :

  ---------------------------------------------------------------------------
  Méthode               Paramètres                Description
  --------------------- ------------------------- ---------------------------
  `CalculateBill`       `studentId` (int),        Calcule la facture
                        `amount` (int)            

  `GetInvoice`          `studentId` (int)         Retourne la facture en
                                                  texte

  `GetStudentBalance`   `studentId` (int)         Retourne le solde

  `PayInvoice`          `studentId` (int),        Marque la facture comme
                        `amount` (decimal)        payée
  ---------------------------------------------------------------------------

------------------------------------------------------------------------

### 2. Tester avec Thunder Client / Postman

#### ✔️ URL

`POST http://localhost:8085/BillingService.svc`

#### ✔️ Headers

    Content-Type: text/xml; charset=utf-8
    SOAPAction: "http://billing.example.com/IBillingService/NomDeLaMéthode"

#### ✔️ Exemples de Requêtes SOAP XML

------------------------------------------------------------------------

### 🟦 CalculateBill

``` xml
<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">
  <s:Body>
    <CalculateBill xmlns="http://billing.example.com/">
      <studentId>1</studentId>
      <amount>100</amount>
    </CalculateBill>
  </s:Body>
</s:Envelope>
```

------------------------------------------------------------------------

### 🟦 GetInvoice

``` xml
<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">
  <s:Body>
    <GetInvoice xmlns="http://billing.example.com/">
      <studentId>1</studentId>
    </GetInvoice>
  </s:Body>
</s:Envelope>
```

------------------------------------------------------------------------

### 🟦 GetStudentBalance

``` xml
<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">
  <s:Body>
    <GetStudentBalance xmlns="http://billing.example.com/">
      <studentId>1</studentId>
    </GetStudentBalance>
  </s:Body>
</s:Envelope>
```

------------------------------------------------------------------------

### 🟦 PayInvoice

``` xml
<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">
  <s:Body>
    <PayInvoice xmlns="http://billing.example.com/">
      <studentId>1</studentId>
      <amount>100.50</amount>
    </PayInvoice>
  </s:Body>
</s:Envelope>
```

------------------------------------------------------------------------

### ✔️ Exemple de Réponse SOAP

``` xml
<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">
  <s:Body>
    <CalculateBillResponse xmlns="http://billing.example.com/">
      <CalculateBillResult>200</CalculateBillResult>
    </CalculateBillResponse>
  </s:Body>
</s:Envelope>
```

