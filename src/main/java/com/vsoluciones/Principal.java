package com.vsoluciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {

    /*public static String generarXmlBaja(Empresa empresa, Venta venta, String identificador) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter idFormatter = DateTimeFormatter.ofPattern("MMdd");

        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<VoidedDocuments xmlns=\"urn:sunat:names:specification:ubl:peru:schema:xsd:VoidedDocuments-1\">\n" +
                "  <ext:UBLExtensions>\n" +
                "    <ext:UBLExtension>\n" +
                "      <ext:ExtensionContent/>\n" +
                "    </ext:UBLExtension>\n" +
                "  </ext:UBLExtensions>\n" +
                "  <cbc:UBLVersionID>2.0</cbc:UBLVersionID>\n" +
                "  <cbc:CustomizationID>1.0</cbc:CustomizationID>\n" +
                "  <cbc:ID>RA-" + LocalDate.now().format(idFormatter) + "-" + identificador + "</cbc:ID>\n" +
                "  <cbc:ReferenceDate>" + venta.getFechaEmision() + "</cbc:ReferenceDate>\n" +
                "  <cbc:IssueDate>" + LocalDate.now().format(dateFormatter) + "</cbc:IssueDate>\n" +
                "  <cac:Signature>\n" +
                "    <cbc:ID>" + empresa.getRuc() + "</cbc:ID>\n" +
                "    <cac:SignatoryParty>\n" +
                "      <cac:PartyIdentification>\n" +
                "        <cbc:ID>" + empresa.getRuc() + "</cbc:ID>\n" +
                "      </cac:PartyIdentification>\n" +
                "      <cac:PartyName>\n" +
                "        <cbc:Name>" + empresa.getNombre() + "</cbc:Name>\n" +
                "      </cac:PartyName>\n" +
                "    </cac:SignatoryParty>\n" +
                "    <cac:DigitalSignatureAttachment>\n" +
                "      <cac:ExternalReference>\n" +
                "        <cbc:URI>" + empresa.getRuc() + "</cbc:URI>\n" +
                "      </cac:ExternalReference>\n" +
                "    </cac:DigitalSignatureAttachment>\n" +
                "  </cac:Signature>\n" +
                "  <cac:AccountingSupplierParty>\n" +
                "    <cbc:CustomerAssignedAccountID>" + empresa.getRuc() + "</cbc:CustomerAssignedAccountID>\n" +
                "    <cac:AdditionalAccountID/>\n" +
                "  </cac:AccountingSupplierParty>\n" +
                "</VoidedDocuments>";

        return xml;
    }*/

    // Clases ejemplo de Empresa y Venta


    public static String generarXmlFactura() {
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>\n" +
                "<Invoice xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2\" " +
                "xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\" " +
                "xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\" " +
                "xmlns:ccts=\"urn:un:unece:uncefact:documentation:2\" " +
                "xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" " +
                "xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\" " +
                "xmlns:qdt=\"urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2\" " +
                "xmlns:udt=\"urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2\" " +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <ext:UBLExtensions>\n" +
                "        <ext:UBLExtension>\n" +
                "            <ext:ExtensionContent>\n" +
                "                <ds:Signature Id=\"FacturacionIntegralSign\">\n" +
                "                    <ds:SignedInfo>\n" +
                "                        <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
                "                        <ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>\n" +
                "                        <ds:Reference URI=\"\">\n" +
                "                            <ds:Transforms>\n" +
                "                                <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
                "                            </ds:Transforms>\n" +
                "                            <ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n" +
                "                            <ds:DigestValue>J5aFaGRI0/AHzrssPH4jPmvvSwg=</ds:DigestValue>\n" +
                "                        </ds:Reference>\n" +
                "                    </ds:SignedInfo>\n" +
                "                    <ds:SignatureValue>a9sm+IRFCaGKXWetYY06FIC6ixvSmUc3g1/9qCDHonP5UqVa+CBLE2zBKSvALd2QsIm3ubv/LrQiEUVSBZrBY+wc/orU2lrShEjzQtX2FMRMeMn4TIPQlwAPR1DzzVa7qtT+Ja9JLDtEbC/dwez2MO3ZJJ3yXOimz+V9J41KQMpegbWcTyjKIzUYWy4bcd3jOdIWwMHrEZYV0ivhBFqBBXdgqjPQvmaCeDtsI9MFH3CoPIRpDkWUwvZ+XTWufdp6oLz4Sko0rOnt4ebOjQkuEOBYEBOVYcyBKLS/sVz9RiJ9z+V0LXoEs8fEDSqce4Z0nRbZkgdjXQlbMPaSj8hkTQ==</ds:SignatureValue>\n" +
                "                    <ds:KeyInfo>\n" +
                "                        <ds:X509Data>\n" +
                "                            <ds:X509Certificate>MIIFCDCCA/CgAwIBAgIJAKfweUaeVfBrMA0GCSqGSIb3DQEBCwUAMIIBDTEbMBkGCgmSJomT8ixkARkWC0xMQU1BLlBFIFNBMQswCQYDVQQGEwJQRTENMAsGA1UECAwETElNQTENMAsGA1UEBwwETElNQTEYMBYGA1UECgwPVFUgRU1QUkVTQSBTLkEuMUUwQwYDVQQLDDxETkkgOTk5OTk5OSBSVUMgMjA2MDQwNTE5ODQgLSBDRVJUSUZJQ0FETyBQQVJBIERFTU9TVFJBQ0nDk04xRDBCBgNVBAMMO05PTUJSRSBSRVBSRVNFTlRBTlRFIExFR0FMIC0gQ0VSVElGSUNBRE8gUEFSQSBERU1PU1RSQUNJw5NOMRwwGgYJKoZIhvcNAQkBFg1kZW1vQGxsYW1hLnBlMB4XDTIyMDUwMTAzMTM1OFoXDTI0MDQzMDAzMTM1OFowggENMRswGQYKCZImiZPyLGQBGRYLTExBTUEuUEUgU0ExCzAJBgNVBAYTAlBFMQ0wCwYDVQQIDARMSU1BMQ0wCwYDVQQHDARMSU1BMRgwFgYDVQQKDA9UVSBFTVBSRVNBIFMuQS4xRTBDBgNVBAsMPEROSSA5OTk5OTk5IFJVQyAyMDYwNDA1MTk4NCAtIENFUlRJRklDQURPIFBBUkEgREVNT1NUUkFDScOTTjFEMEIGA1UEAww7Tk9NQlJFIFJFUFJFU0VOVEFOVEUgTEVHQUwgLSBDRVJUSUZJQ0FETyBQQVJBIERFTU9TVFJBQ0nDk04xHDAaBgkqhkiG9w0BCQEWDWRlbW9AbGxhbWEucGUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC1WpGOLZayBuCCZBLIDIaNjeprnDTJOcdj+KmzetJsI14EHfsUPov8lv9nUIIUJbJkcMNBTZs6RzJGQ3ghfM9fKpun5RtPbKE7MJmADvZ5PkAZnWWkMsU/9oV9yRf62pCh5Et6oueXOo4sgomZcVK7YisZabOMfiX64Q1ytDlMfLXEPeAI2qqD2fmZ0e3yqSiTlGLCHq4gy2A6XjhwejShAWS/VbOLoOotx8L8eQoZcCuVlz/sDNhGGNxg/34Yw3hZgN3lebhSUGQKFPVekyIrZeQfhbcHefAo+RBW0IcsVPB/w9bT5S1si1zBvAQHbv/zRy3o1oExXU5owg+KNAfXAgMBAAGjZzBlMB0GA1UdDgQWBBSutRFcyJrUkG8WA9hRC+lIJdlgFDAfBgNVHSMEGDAWgBSutRFcyJrUkG8WA9hRC+lIJdlgFDATBgNVHSUEDDAKBggrBgEFBQcDATAOBgNVHQ8BAf8EBAMCB4AwDQYJKoZIhvcNAQELBQADggEBAGuqeW50XOYgJlXj7XAsF+ViaroOSEEdqclEGBAZ45jnorKUOA7Dks9RRmPkPM/ZSge5m53Q5krfNp7jpHiU6zGcC70nHSk51tUyrLcs5YTV7/TD6w4+IfNrXLzEOjaH0nOajWLCcb7qhhuq4jDYtksqKiLp8cCaHHk/eM7N/h74fCp7ochrb6ZgKi3awuivOAIP5HzSZ6vb0F4zgoS746z8kRwfUGXXiqLxOH/RggyUrakoiVBuL1K8/rI85mm179KnbyZFuQH8ue+nkaCWvJI1PdiRVcrjHaFpXrd22QQc88HRG2ZoMmcpksYSQNWi1EQiIMJQSlpeITKUDBymweE=</ds:X509Certificate>\n" +
                "                        </ds:X509Data>\n" +
                "                    </ds:KeyInfo>\n" +
                "                </ds:Signature>\n" +
                "            </ext:ExtensionContent>\n" +
                "        </ext:UBLExtension>\n" +
                "    </ext:UBLExtensions>\n" +
                "    <cbc:UBLVersionID>2.1</cbc:UBLVersionID>\n" +
                "    <cbc:CustomizationID>2.0</cbc:CustomizationID>\n" +
                "    <cbc:ID>F001-00000003</cbc:ID>\n" +
                "    <cbc:IssueDate>2019-11-06</cbc:IssueDate>\n" +
                "    <cbc:IssueTime>19:02:05</cbc:IssueTime>\n" +
                "    <cbc:InvoiceTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Tipo de Documento\" " +
                "listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01\" " +
                "listID=\"0101\">01</cbc:InvoiceTypeCode>\n" +
                "    <cbc:Note languageLocaleID=\"1000\">Operación sujeta a detracción</cbc:Note>\n" +
                "    <cbc:DocumentCurrencyCode listID=\"ISO 4217 Alpha\" listName=\"Currency\" " +
                "listAgencyName=\"United Nations Economic Commission for Europe\">PEN</cbc:DocumentCurrencyCode>\n" +
                "    <cbc:LineCountNumeric>1</cbc:LineCountNumeric>\n" +
                "    <cac:Signature>\n" +
                "        <cbc:ID>FacturacionIntegralSign</cbc:ID>\n" +
                "        <cac:SignatoryParty>\n" +
                "            <cac:PartyIdentification>\n" +
                "                <cbc:ID>20600695771</cbc:ID>\n" +
                "            </cac:PartyIdentification>\n" +
                "            <cac:PartyName>\n" +
                "                <cbc:Name>Test Data Business S.A.</cbc:Name>\n" +
                "            </cac:PartyName>\n" +
                "        </cac:SignatoryParty>\n" +
                "        <cac:DigitalSignatureAttachment>\n" +
                "            <cac:ExternalReference>\n" +
                "                <cbc:URI>#FacturacionIntegralSign</cbc:URI>\n" +
                "            </cac:ExternalReference>\n" +
                "        </cac:DigitalSignatureAttachment>\n" +
                "    </cac:Signature>\n" +
                "    <cac:AccountingSupplierParty>\n" +
                "        <cac:Party>\n" +
                "            <cac:PartyIdentification>\n" +
                "                <cbc:ID schemeID=\"6\">20600695771</cbc:ID>\n" +
                "            </cac:PartyIdentification>\n" +
                "            <cac:PartyLegalEntity>\n" +
                "                <cbc:RegistrationName>Test Data Business S.A.</cbc:RegistrationName>\n" +
                "                <cbc:CompanyID>20600695771</cbc:CompanyID>\n" +
                "            </cac:PartyLegalEntity>\n" +
                "        </cac:Party>\n" +
                "    </cac:AccountingSupplierParty>\n" +
                "    <cac:AccountingCustomerParty>\n" +
                "        <cac:Party>\n" +
                "            <cac:PartyIdentification>\n" +
                "                <cbc:ID schemeID=\"1\">99999999</cbc:ID>\n" +
                "            </cac:PartyIdentification>\n" +
                "            <cac:PartyLegalEntity>\n" +
                "                <cbc:RegistrationName>Cliente de prueba</cbc:RegistrationName>\n" +
                "                <cbc:CompanyID>99999999</cbc:CompanyID>\n" +
                "            </cac:PartyLegalEntity>\n" +
                "        </cac:Party>\n" +
                "    </cac:AccountingCustomerParty>\n" +
                "    <cac:PaymentTerms>\n" +
                "        <cbc:ID>FormaPago</cbc:ID>\n" +
                "        <cbc:PaymentMeansID>Contado</cbc:PaymentMeansID>\n" +
                "        <cbc:Amount currencyID=\"PEN\">900.00</cbc:Amount>\n" +
                "    </cac:PaymentTerms>\n" +
                "    <cac:TaxTotal>\n" +
                "        <cbc:TaxAmount currencyID=\"PEN\">162.00</cbc:TaxAmount>\n" +
                "        <cac:TaxSubtotal>\n" +
                "            <cbc:TaxableAmount currencyID=\"PEN\">900.00</cbc:TaxableAmount>\n" +
                "            <cbc:TaxAmount currencyID=\"PEN\">162.00</cbc:TaxAmount>\n" +
                "            <cac:TaxCategory>\n" +
                "                <cac:TaxScheme>\n" +
                "                    <cbc:ID>1000</cbc:ID>\n" +
                "                    <cbc:Name>IGV</cbc:Name>\n" +
                "                    <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n" +
                "                </cac:TaxScheme>\n" +
                "            </cac:TaxCategory>\n" +
                "        </cac:TaxSubtotal>\n" +
                "    </cac:TaxTotal>\n" +
                "    <cac:LegalMonetaryTotal>\n" +
                "        <cbc:LineExtensionAmount currencyID=\"PEN\">900.00</cbc:LineExtensionAmount>\n" +
                "        <cbc:TaxInclusiveAmount currencyID=\"PEN\">1062.00</cbc:TaxInclusiveAmount>\n" +
                "        <cbc:PayableAmount currencyID=\"PEN\">1062.00</cbc:PayableAmount>\n" +
                "    </cac:LegalMonetaryTotal>\n" +
                "    <cac:InvoiceLine>\n" +
                "        <cbc:ID>1</cbc:ID>\n" +
                "        <cbc:InvoicedQuantity unitCode=\"NIU\">1.00</cbc:InvoicedQuantity>\n" +
                "        <cbc:LineExtensionAmount currencyID=\"PEN\">900.00</cbc:LineExtensionAmount>\n" +
                "        <cac:PricingReference>\n" +
                "            <cac:AlternativeConditionPrice>\n" +
                "                <cbc:PriceAmount currencyID=\"PEN\">1062.00</cbc:PriceAmount>\n" +
                "                <cbc:PriceTypeCode>01</cbc:PriceTypeCode>\n" +
                "            </cac:AlternativeConditionPrice>\n" +
                "        </cac:PricingReference>\n" +
                "        <cac:TaxTotal>\n" +
                "            <cbc:TaxAmount currencyID=\"PEN\">162.00</cbc:TaxAmount>\n" +
                "            <cac:TaxSubtotal>\n" +
                "                <cbc:TaxableAmount currencyID=\"PEN\">900.00</cbc:TaxableAmount>\n" +
                "                <cbc:TaxAmount currencyID=\"PEN\">162.00</cbc:TaxAmount>\n" +
                "                <cac:TaxCategory>\n" +
                "                    <cac:TaxScheme>\n" +
                "                        <cbc:ID>1000</cbc:ID>\n" +
                "                        <cbc:Name>IGV</cbc:Name>\n" +
                "                        <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n" +
                "                    </cac:TaxScheme>\n" +
                "                </cac:TaxCategory>\n" +
                "            </cac:TaxSubtotal>\n" +
                "        </cac:TaxTotal>\n" +
                "        <cac:Item>\n" +
                "            <cbc:Description>Servicio de Prueba</cbc:Description>\n" +
                "        </cac:Item>\n" +
                "        <cac:Price>\n" +
                "            <cbc:PriceAmount currencyID=\"PEN\">900.00</cbc:PriceAmount>\n" +
                "        </cac:Price>\n" +
                "    </cac:InvoiceLine>\n" +
                "</Invoice>";

        return xml;
    }


    public static void main(String[] args) {
        //XmlGenerator generator = new XmlGenerator();
        Empresa empresa = new Empresa();
        empresa.setRuc("12345678901");
        empresa.setNombre("Mi Empresa SAC");

        Venta venta = new Venta();
        venta.setFechaEmision("2023-08-24");

        String identificador = "001";

        //String xml = generarXmlBaja(empresa, venta, identificador);
        String xml = generarXmlFactura();
        System.out.println(xml);

        String ruta = "C:\\VSPERU\\rutadocs\\factura_" + identificador + ".xml";

        // Guardar el archivo en la ruta especificada
        try {
            // Opción 1: Usar FileWriter
            FileWriter fileWriter = new FileWriter(new File(ruta));
            fileWriter.write(xml);
            fileWriter.close();

            // Opción 2: Usar Files.write
            // Files.write(Paths.get(ruta), xml.getBytes());

            System.out.println("Archivo guardado en: " + ruta);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }

    }
}
