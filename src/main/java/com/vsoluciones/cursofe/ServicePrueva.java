package com.vsoluciones.cursofe;

import com.vsoluciones.cursofe.model.Factura;
import com.vsoluciones.cursofe.model.Item;

import java.text.DecimalFormat;

public class ServicePrueva {


    public static String generateXmlFromFactura2(Factura factura) {
        // Implementación de generación XML similar a la proporcionada anteriormente
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>\n")
                .append("<Invoice xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2\"\n")
                .append("    xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\"\n")
                .append("    xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\"\n")
                .append("    xmlns:ccts=\"urn:un:unece:uncefact:documentation:2\"\n")
                .append("    xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\n")
                .append("    xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\"\n")
                .append("    xmlns:qdt=\"urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2\"\n")
                .append("    xmlns:udt=\"urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2\"\n")
                .append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n")
                .append("    <ext:UBLExtensions>\n")
                .append("        <ext:UBLExtension>\n")
                .append("            <ext:ExtensionContent>\n")
                .append("                <ds:Signature Id=\"FacturacionIntegralSign\">\n")
                .append("                    <ds:SignedInfo>\n")
                .append("                        <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n")
                .append("                        <ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>\n")
                .append("                        <ds:Reference URI=\"\">\n")
                .append("                            <ds:Transforms>\n")
                .append("                                <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n")
                .append("                            </ds:Transforms>\n")
                .append("                            <ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n")
                .append("                            <ds:DigestValue>J5aFaGRI0/AHzrssPH4jPmvvSwg=</ds:DigestValue>\n")
                .append("                        </ds:Reference>\n")
                .append("                    </ds:SignedInfo>\n")
                .append("                    <ds:SignatureValue></ds:SignatureValue>\n")
                .append("                    <ds:KeyInfo>\n")
                .append("                        <ds:X509Data>\n")
                .append("                            <ds:X509Certificate></ds:X509Certificate>\n")
                .append("                        </ds:X509Data>\n")
                .append("                    </ds:KeyInfo>\n")
                .append("                </ds:Signature>\n")
                .append("            </ext:ExtensionContent>\n")
                .append("        </ext:UBLExtension>\n")
                .append("    </ext:UBLExtensions>\n")
                .append("    <cbc:UBLVersionID>2.1</cbc:UBLVersionID>\n")
                .append("    <cbc:CustomizationID>2.0</cbc:CustomizationID>\n")

                .append("    <cbc:ID>").append(factura.getVenta().getSerie() + "-" + factura.getVenta().getNumero()).append("</cbc:ID>\n")
                .append("    <cbc:IssueDate>").append(factura.getVenta().getFecha_emision()).append("</cbc:IssueDate>\n")
                .append("    <cbc:IssueTime>").append(factura.getVenta().getHora_emision()).append("</cbc:IssueTime>\n")

                .append("    <cbc:InvoiceTypeCode listID=\"0101\" listAgencyName=\"PE:SUNAT\" listName=\"Tipo de Documento\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01\" name=\"Tipo de Operacion\" listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\">03</cbc:InvoiceTypeCode>\n")
                .append("    <cbc:Note languageLocaleID=\"1000\">").append(factura.getVenta().getNota()).append("</cbc:Note>\n")
                .append("    <cbc:DocumentCurrencyCode listID=\"ISO 4217 Alpha\" listName=\"Currency\" listAgencyName=\"United Nations Economic Commission for Europe\">").append(UtilMoneda.obtenerMonedaPorCodigo(factura.getVenta().getMoneda_id())).append("</cbc:DocumentCurrencyCode>\n")
                .append("    <cac:Signature>\n")
                .append("        <cbc:ID>").append(factura.getEmpresa().getRuc()).append("</cbc:ID>\n")
                .append("        <cac:SignatoryParty>\n")
                .append("            <cac:PartyIdentification>\n")
                .append("                <cbc:ID>").append(factura.getEmpresa().getRuc()).append("</cbc:ID>\n")
                .append("            </cac:PartyIdentification>\n")
                .append("            <cac:PartyName>\n")
                .append("                <cbc:Name><![CDATA[").append(factura.getEmpresa().getRazon_social()).append("]]></cbc:Name>\n")
                .append("            </cac:PartyName>\n")
                .append("        </cac:SignatoryParty>\n")
                .append("        <cac:DigitalSignatureAttachment>\n")
                .append("            <cac:ExternalReference>\n")
                .append("                <cbc:URI>#signer").append(factura.getEmpresa().getRuc()).append("</cbc:URI>\n")
                .append("            </cac:ExternalReference>\n")
                .append("        </cac:DigitalSignatureAttachment>\n")
                .append("    </cac:Signature>\n")
                .append("    <cac:AccountingSupplierParty>\n")
                .append("        <cac:Party>\n")
                .append("            <cac:PartyIdentification>\n")
                .append("                <cbc:ID schemeID=\"6\">").append(factura.getEmpresa().getRuc()).append("</cbc:ID>\n")
                .append("            </cac:PartyIdentification>\n")
                .append("            <cac:PartyName>\n")
                .append("                <cbc:Name><![CDATA[").append(factura.getEmpresa().getNombre_comercial()).append("]]></cbc:Name>\n")
                .append("            </cac:PartyName>\n")
                .append("            <cac:PartyLegalEntity>\n")
                .append("                <cbc:RegistrationName><![CDATA[").append(factura.getEmpresa().getRazon_social()).append("]]></cbc:RegistrationName>\n")
                .append("                <cac:RegistrationAddress>\n")
                .append("                    <cbc:ID schemeName=\"Ubigeos\" schemeAgencyName=\"PE:INEI\">").append(factura.getEmpresa().getUbigeo()).append("</cbc:ID>\n")
                .append("                    <cbc:AddressTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Establecimientos anexos\">0000</cbc:AddressTypeCode>\n")
                .append("                    <cbc:CityName>").append(factura.getEmpresa().getProvincia()).append("</cbc:CityName>\n")
                .append("                    <cbc:CountrySubentity>").append(factura.getEmpresa().getDepartamento()).append("</cbc:CountrySubentity>\n")
                .append("                    <cbc:District>").append(factura.getEmpresa().getDistrito()).append("</cbc:District>\n")
                .append("                    <cac:AddressLine>\n")
                .append("                        <cbc:Line><![CDATA[").append(factura.getEmpresa().getDomicilio_fiscal()).append("]]></cbc:Line>\n")
                .append("                    </cac:AddressLine>\n")
                .append("                    <cac:Country>\n")
                .append("                        <cbc:IdentificationCode listID=\"ISO 3166-1\" listAgencyName=\"United Nations Economic Commission for Europe\" listName=\"Country\">PE</cbc:IdentificationCode>\n")
                .append("                    </cac:Country>\n")
                .append("                </cac:RegistrationAddress>\n")
                .append("            </cac:PartyLegalEntity>\n")
                .append("        </cac:Party>\n")
                .append("    </cac:AccountingSupplierParty>\n")

                .append("    <cac:AccountingCustomerParty>\n")
                .append("        <cac:Party>\n")
                .append("            <cac:PartyIdentification>\n")
                .append("                <cbc:ID schemeID=\"").append(factura.getCliente().getCodigo_tipo_entidad()).append("\" schemeName=\"Documento de Identidad\" schemeAgencyName=\"PE:SUNAT\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo06\">").append(factura.getCliente().getNumero_documento()).append("</cbc:ID>\n")
                .append("            </cac:PartyIdentification>\n")
                .append("            <cac:PartyLegalEntity>\n")
                .append("                <cbc:RegistrationName><![CDATA[").append(factura.getCliente().getRazon_social_nombres()).append("]]></cbc:RegistrationName>\n")
                .append("            </cac:PartyLegalEntity>\n")
                .append("        </cac:Party>\n")
                .append("    </cac:AccountingCustomerParty>\n")

                .append(generateTaxTotal(factura/*currencyCode, totalIgv, totalGravada, totalExonerada, totalInafecta*/))

                .append(generateLegalMonetaryTotal(factura));

        int index = 0;
        String moneda = UtilMoneda.obtenerMonedaPorCodigo(factura.getVenta().getMoneda_id());
        for (Item item : factura.getItems()) {
            xml.append("    <cac:InvoiceLine>\n")
                    .append("        <cbc:ID>").append(++index).append("</cbc:ID>\n")
                    .append("        <cbc:InvoicedQuantity unitCode=\"").append(item.getCodigo_unidad()).append("\">").append(item.getCantidad()).append("</cbc:InvoicedQuantity>\n")
                    .append("        <cbc:LineExtensionAmount currencyID=\"").append(moneda).append("\">").append(UtilMoneda.multiplicarPrecioYCantidad(item.getPrecio_base(), item.getCantidad())).append("</cbc:LineExtensionAmount>\n")

                    .append("        <cac:PricingReference>\n")
                    .append("            <cac:AlternativeConditionPrice>\n")
                    .append("                <cbc:PriceAmount currencyID=\"").append(moneda).append("\">").append(UtilMoneda.multiplicarPrecio(item.getPrecio_base())).append("</cbc:PriceAmount>\n")
                    .append("                <cbc:PriceTypeCode listID=\"0101\" listAgencyName=\"PE:SUNAT\">01</cbc:PriceTypeCode>\n")
                    .append("            </cac:AlternativeConditionPrice>\n")
                    .append("        </cac:PricingReference>\n")

                    .append("        <cac:TaxTotal>\n")
                    .append("            <cbc:TaxAmount currencyID=\"").append(moneda).append("\">").append(UtilMoneda.obtenerIgvPrecioUnitPorCantidad(item.getPrecio_base(), item.getCantidad())).append("</cbc:TaxAmount>\n")
                    .append("            <cac:TaxSubtotal>\n")
                    .append("                <cbc:TaxAmount currencyID=\"").append(moneda).append("\">").append(UtilMoneda.multiplicarPrecioYCantidad(item.getPrecio_base(), item.getCantidad())).append("</cbc:TaxAmount>\n")
                    .append("                <cac:TaxCategory>\n")
                    .append("                    <cbc:ID schemeID=\"VAT\">S</cbc:ID>\n")
                    .append("                    <cbc:Percent>18.00</cbc:Percent>\n")
                    .append("                    <cac:TaxScheme>\n")
                    .append("                        <cbc:ID>1000</cbc:ID>\n")
                    .append("                        <cbc:Name>IGV</cbc:Name>\n")
                    .append("                        <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n")
                    .append("                    </cac:TaxScheme>\n")
                    .append("                </cac:TaxCategory>\n")
                    .append("            </cac:TaxSubtotal>\n")
                    .append("        </cac:TaxTotal>\n")
                    .append("        <cac:Item>\n")
                    .append("            <cbc:Description><![CDATA[").append(item.getProducto()).append("]]></cbc:Description>\n")
                    .append("            <cac:SellersItemIdentification>\n")
                    .append("                <cbc:ID>").append(item.getCodigo_producto()).append("</cbc:ID>\n")
                    .append("            </cac:SellersItemIdentification>\n")
                    .append("            <cac:CommodityClassification>\n")
                    .append("                <cbc:ItemClassificationCode>").append(item.getCodigo_sunat()).append("</cbc:ItemClassificationCode>\n")
                    .append("            </cac:CommodityClassification>\n")
                    .append("        </cac:Item>\n")
                    .append("    </cac:InvoiceLine>\n");
        }


        xml.append("</Invoice>");


        return xml.toString();
    }


    private static String generateLegalMonetaryTotal(Factura factura) {
        double totalIgv = UtilMoneda.isNullOrTrimmedEmpty(factura.getVenta().getTotal_igv()) ? 0.0 : Double.parseDouble(factura.getVenta().getTotal_igv());
        double totalGravada = UtilMoneda.isNullOrTrimmedEmpty(factura.getVenta().getTotal_gravada()) ? 0.0 : Double.parseDouble(factura.getVenta().getTotal_gravada());
        double totalExonerada = UtilMoneda.isNullOrTrimmedEmpty(factura.getVenta().getTotal_exonerada()) ? 0.0 : Double.parseDouble(factura.getVenta().getTotal_exonerada());
        double totalInafecta = UtilMoneda.isNullOrTrimmedEmpty(factura.getVenta().getTotal_inafecta()) ? 0.0 : Double.parseDouble(factura.getVenta().getTotal_inafecta());
        String moneda = UtilMoneda.obtenerMonedaPorCodigo(factura.getVenta().getMoneda_id());
        // Calcular el total a pagar

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        double lineExtensionAmount = totalGravada + totalExonerada + totalInafecta;
        double taxInclusiveAmount = lineExtensionAmount + totalIgv;
        double payableAmount = taxInclusiveAmount;

        StringBuilder legalMonetaryTotalBuilder = new StringBuilder();
        legalMonetaryTotalBuilder
                .append("    <cac:LegalMonetaryTotal>\n")
                .append("        <cbc:LineExtensionAmount currencyID=\"").append(moneda).append("\">").append(decimalFormat.format(lineExtensionAmount)).append("</cbc:LineExtensionAmount>\n")
                .append("        <cbc:TaxInclusiveAmount currencyID=\"").append(moneda).append("\">").append(decimalFormat.format(taxInclusiveAmount)).append("</cbc:TaxInclusiveAmount>\n")
                .append("        <cbc:AllowanceTotalAmount currencyID=\"").append(moneda).append("\">0.00</cbc:AllowanceTotalAmount>\n")
                .append("        <cbc:ChargeTotalAmount currencyID=\"").append(moneda).append("\">0.00</cbc:ChargeTotalAmount>\n")
                .append("        <cbc:PrepaidAmount currencyID=\"").append(moneda).append("\">0.00</cbc:PrepaidAmount>\n")
                .append("        <cbc:PayableAmount currencyID=\"").append(moneda).append("\">").append(decimalFormat.format(payableAmount)).append("</cbc:PayableAmount>\n")
                .append("    </cac:LegalMonetaryTotal>\n");

        return legalMonetaryTotalBuilder.toString();
    }


    public static String generateTaxTotal(Factura factura) {
        StringBuilder xml = new StringBuilder();

        String moneda = UtilMoneda.obtenerMonedaPorCodigo(factura.getVenta().getMoneda_id());
        double totalIgv = factura.getVenta().getTotal_igv() != null ? Double.parseDouble(factura.getVenta().getTotal_igv()) : 0.0;


        xml.append("    <cac:TaxTotal>\n")
                .append("        <cbc:TaxAmount currencyID=\"").append(moneda).append("\">").append(totalIgv).append("</cbc:TaxAmount>\n");

        if (factura.getVenta().getTotal_gravada() != null) {
            xml.append("        <cac:TaxSubtotal>\n")
                    .append("            <cbc:TaxableAmount currencyID=\"").append(moneda).append("\">").append(factura.getVenta().getTotal_gravada()).append("</cbc:TaxableAmount>\n")
                    .append("            <cbc:TaxAmount currencyID=\"").append(moneda).append("\">").append(totalIgv).append("</cbc:TaxAmount>\n")
                    .append("            <cac:TaxCategory>\n")
                    .append("                <cac:TaxScheme>\n")
                    .append("                    <cbc:ID schemeName=\"Codigo de tributos\" schemeAgencyName=\"PE:SUNAT\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo05\">1000</cbc:ID>\n")
                    .append("                    <cbc:Name>IGV</cbc:Name>\n")
                    .append("                    <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n")
                    .append("                </cac:TaxScheme>\n")
                    .append("            </cac:TaxCategory>\n")
                    .append("        </cac:TaxSubtotal>\n");
        }

        if (factura.getVenta().getTotal_exonerada() != null) {
            xml.append("        <cac:TaxSubtotal>\n")
                    .append("            <cbc:TaxableAmount currencyID=\"").append(moneda).append("\">").append(factura.getVenta().getTotal_exonerada()).append("</cbc:TaxableAmount>\n")
                    .append("            <cbc:TaxAmount currencyID=\"").append(moneda).append("\">0.00</cbc:TaxAmount>\n")
                    .append("            <cac:TaxCategory>\n")
                    .append("                <cac:TaxScheme>\n")
                    .append("                    <cbc:ID schemeName=\"Codigo de tributos\" schemeAgencyName=\"PE:SUNAT\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo05\">9997</cbc:ID>\n")
                    .append("                    <cbc:Name>EXO</cbc:Name>\n")
                    .append("                    <cbc:TaxTypeCode>VAT</cbc:TaxTypeCode>\n")
                    .append("                </cac:TaxScheme>\n")
                    .append("            </cac:TaxCategory>\n")
                    .append("        </cac:TaxSubtotal>\n");
        }

        if (factura.getVenta().getTotal_inafecta() != null) {
            xml.append("        <cac:TaxSubtotal>\n")
                    .append("            <cbc:TaxableAmount currencyID=\"").append(moneda).append("\">").append(factura.getVenta().getTotal_inafecta()).append("</cbc:TaxableAmount>\n")
                    .append("            <cbc:TaxAmount currencyID=\"").append(moneda).append("\">0.00</cbc:TaxAmount>\n")
                    .append("            <cac:TaxCategory>\n")
                    .append("                <cac:TaxScheme>\n")
                    .append("                    <cbc:ID schemeName=\"Codigo de tributos\" schemeAgencyName=\"PE:SUNAT\" schemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo05\">9998</cbc:ID>\n")
                    .append("                    <cbc:Name>INA</cbc:Name>\n")
                    .append("                    <cbc:TaxTypeCode>FRE</cbc:TaxTypeCode>\n")
                    .append("                </cac:TaxScheme>\n")
                    .append("            </cac:TaxCategory>\n")
                    .append("        </cac:TaxSubtotal>\n");
        }

        xml.append("    </cac:TaxTotal>\n");

        return xml.toString();
    }


    public static String generateInvoiceXml(
            String rucEmpresa, String razonSocialEmpresa, String domicilioFiscal, String ubigeo, String departamento,
            String provincia, String distrito, String tipoEntidadCliente, String numeroDocumentoCliente, String razonSocialCliente,
            String issueDate, String issueTime, String note, String currencyCode, double taxableAmount, double taxAmount,
            double lineExtensionAmount1, double priceAmount1, double taxAmount1, double lineExtensionAmount2, double priceAmount2, double taxAmount2) {

        StringBuilder xml = new StringBuilder();

        xml.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>\n")
                .append("<Invoice xmlns=\"urn:oasis:names:specification:ubl:schema:xsd:Invoice-2\"\n")
                .append("    xmlns:cac=\"urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2\"\n")
                .append("    xmlns:cbc=\"urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2\"\n")
                .append("    xmlns:ccts=\"urn:un:unece:uncefact:documentation:2\"\n")
                .append("    xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\"\n")
                .append("    xmlns:ext=\"urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2\"\n")
                .append("    xmlns:qdt=\"urn:oasis:names:specification:ubl:schema:xsd:QualifiedDatatypes-2\"\n")
                .append("    xmlns:udt=\"urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2\"\n")
                .append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n")
                .append("    <ext:UBLExtensions>\n")
                .append("        <ext:UBLExtension>\n")
                .append("            <ext:ExtensionContent>\n")
                .append("                <ds:Signature Id=\"FacturacionIntegralSign\">\n")
                .append("                    <ds:SignedInfo>\n")
                .append("                        <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n")
                .append("                        <ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>\n")
                .append("                        <ds:Reference URI=\"\">\n")
                .append("                            <ds:Transforms>\n")
                .append("                                <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n")
                .append("                            </ds:Transforms>\n")
                .append("                            <ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n")
                .append("                            <ds:DigestValue>J5aFaGRI0/AHzrssPH4jPmvvSwg=</ds:DigestValue>\n")
                .append("                        </ds:Reference>\n")
                .append("                    </ds:SignedInfo>\n")
                .append("                    <ds:SignatureValue></ds:SignatureValue>\n")
                .append("                    <ds:KeyInfo>\n")
                .append("                        <ds:X509Data>\n")
                .append("                            <ds:X509Certificate></ds:X509Certificate>\n")
                .append("                        </ds:X509Data>\n")
                .append("                    </ds:KeyInfo>\n")
                .append("                </ds:Signature>\n")
                .append("            </ext:ExtensionContent>\n")
                .append("        </ext:UBLExtension>\n")
                .append("    </ext:UBLExtensions>\n")
                .append("    <cbc:UBLVersionID>2.1</cbc:UBLVersionID>\n")
                .append("    <cbc:CustomizationID>2.0</cbc:CustomizationID>\n")
                .append("    <cbc:ID>F002-5</cbc:ID>\n")
                .append("    <cbc:IssueDate>").append(issueDate).append("</cbc:IssueDate>\n")
                .append("    <cbc:IssueTime>").append(issueTime).append("</cbc:IssueTime>\n")
                .append("    <cbc:InvoiceTypeCode listID=\"0101\" listAgencyName=\"PE:SUNAT\" listName=\"Tipo de Documento\" listURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo01\" name=\"Tipo de Operacion\" listSchemeURI=\"urn:pe:gob:sunat:cpe:see:gem:catalogos:catalogo51\">03</cbc:InvoiceTypeCode>\n")
                .append("    <cbc:Note languageLocaleID=\"1000\">").append(note).append("</cbc:Note>\n")
                .append("    <cbc:DocumentCurrencyCode listID=\"ISO 4217 Alpha\" listName=\"Currency\" listAgencyName=\"United Nations Economic Commission for Europe\">").append(currencyCode).append("</cbc:DocumentCurrencyCode>\n")
                .append("    <cac:Signature>\n")
                .append("        <cbc:ID>").append(rucEmpresa).append("</cbc:ID>\n")
                .append("        <cac:SignatoryParty>\n")
                .append("            <cac:PartyIdentification>\n")
                .append("                <cbc:ID>").append(rucEmpresa).append("</cbc:ID>\n")
                .append("            </cac:PartyIdentification>\n")
                .append("            <cac:PartyName>\n")
                .append("                <cbc:Name><![CDATA[").append(razonSocialEmpresa).append("]]></cbc:Name>\n")
                .append("            </cac:PartyName>\n")
                .append("        </cac:SignatoryParty>\n")
                .append("        <cac:DigitalSignatureAttachment>\n")
                .append("            <cac:ExternalReference>\n")
                .append("                <cbc:URI>").append(rucEmpresa).append("</cbc:URI>\n")
                .append("            </cac:ExternalReference>\n")
                .append("        </cac:DigitalSignatureAttachment>\n")
                .append("    </cac:Signature>\n")

                .append("    <cac:AccountingSupplierParty>\n")
                .append("        <cac:Party>\n")
                .append("            <cac:PartyIdentification>\n")
                .append("                <cbc:ID schemeID=\"6\">").append(rucEmpresa).append("</cbc:ID>\n")
                .append("            </cac:PartyIdentification>\n")
                .append("            <cac:PartyName>\n")
                .append("                <cbc:Name><![CDATA[").append(razonSocialEmpresa).append("]]></cbc:Name>\n")
                .append("            </cac:PartyName>\n")
                .append("            <cac:PartyLegalEntity>\n")
                .append("                <cbc:RegistrationName><![CDATA[").append(razonSocialEmpresa).append("]]></cbc:RegistrationName>\n")
                .append("                <cac:RegistrationAddress>\n")
                .append("                    <cbc:ID schemeName=\"Ubigeos\" schemeAgencyName=\"PE:INEI\">").append(ubigeo).append("</cbc:ID>\n")
                .append("                    <cbc:AddressTypeCode listAgencyName=\"PE:SUNAT\" listName=\"Establecimientos anexos\">0000</cbc:AddressTypeCode>\n")
                .append("                    <cbc:CityName>").append(provincia).append("</cbc:CityName>\n")
                .append("                    <cbc:CountrySubentity>").append(departamento).append("</cbc:CountrySubentity>\n")
                .append("                    <cbc:District>").append(distrito).append("</cbc:District>\n")
                .append("                    <cac:AddressLine>\n")
                .append("                        <cbc:Line><![CDATA[").append(domicilioFiscal).append("]]></cbc:Line>\n")
                .append("                    </cac:AddressLine>\n")
                .append("                    <cac:Country>\n")
                .append("                        <cbc:IdentificationCode listID=\"ISO 3166-1\" listAgencyName=\"United Nations Economic Commission for Europe\" listName=\"Country\">PE</cbc:IdentificationCode>\n")
                .append("                    </cac:Country>\n")
                .append("                </cac:RegistrationAddress>\n")
                .append("            </cac:PartyLegalEntity>\n")
                .append("        </cac:Party>\n")
                .append("    </cac:AccountingSupplierParty>\n")
                .append("    <cac:AccountingCustomerParty>\n")
                .append("        <cac:Party>\n")
                .append("            <cac:PartyIdentification>\n")
                .append("                <cbc:ID schemeID=\"6\" schemeName=\"Documento de Identidad\" schemeAgencyName=\"PE:SUNAT\">").append(numeroDocumentoCliente).append("</cbc:ID>\n")
                .append("            </cac:PartyIdentification>\n")
                .append("            <cac:PartyName>\n")
                .append("                <cbc:Name><![CDATA[").append(razonSocialCliente).append("]]></cbc:Name>\n")
                .append("            </cac:PartyName>\n")
                .append("        </cac:Party>\n")
                .append("    </cac:AccountingCustomerParty>\n")
                .append("    <cac:TaxTotal>\n")
                .append("        <cbc:TaxAmount currencyID=\"").append(currencyCode).append("\">").append(taxAmount).append("</cbc:TaxAmount>\n")
                .append("    </cac:TaxTotal>\n")
                .append("    <cac:LegalMonetaryTotal>\n")
                .append("        <cbc:LineExtensionAmount currencyID=\"").append(currencyCode).append("\">").append(taxableAmount).append("</cbc:LineExtensionAmount>\n")
                .append("        <cbc:TaxExclusiveAmount currencyID=\"").append(currencyCode).append("\">").append(taxableAmount).append("</cbc:TaxExclusiveAmount>\n")
                .append("        <cbc:TaxInclusiveAmount currencyID=\"").append(currencyCode).append("\">").append(taxableAmount + taxAmount).append("</cbc:TaxInclusiveAmount>\n")
                .append("        <cbc:PayableAmount currencyID=\"").append(currencyCode).append("\">").append(taxableAmount + taxAmount).append("</cbc:PayableAmount>\n")
                .append("    </cac:LegalMonetaryTotal>\n")
                .append("    <cac:InvoiceLine>\n")
                .append("        <cbc:ID>1</cbc:ID>\n")
                .append("        <cbc:InvoicedQuantity unitCode=\"NIU\" unitCodeListID=\"UN/ECE 5305\">1.0</cbc:InvoicedQuantity>\n")
                .append("        <cbc:LineExtensionAmount currencyID=\"").append(currencyCode).append("\">").append(lineExtensionAmount1).append("</cbc:LineExtensionAmount>\n")
                .append("        <cac:PricingReference>\n")
                .append("            <cac:AlternativeConditionPrice>\n")
                .append("                <cbc:PriceAmount currencyID=\"").append(currencyCode).append("\">").append(priceAmount1).append("</cbc:PriceAmount>\n")
                .append("                <cbc:PriceTypeCode listID=\"0101\" listAgencyName=\"PE:SUNAT\">01</cbc:PriceTypeCode>\n")
                .append("            </cac:AlternativeConditionPrice>\n")
                .append("        </cac:PricingReference>\n")
                .append("        <cac:TaxTotal>\n")
                .append("            <cbc:TaxAmount currencyID=\"").append(currencyCode).append("\">").append(taxAmount1).append("</cbc:TaxAmount>\n")
                .append("            <cac:TaxSubtotal>\n")
                .append("                <cbc:TaxAmount currencyID=\"").append(currencyCode).append("\">").append(taxAmount1).append("</cbc:TaxAmount>\n")
                .append("                <cac:TaxCategory>\n")
                .append("                    <cbc:ID schemeID=\"VAT\" schemeAgencyName=\"PE:SUNAT\">S</cbc:ID>\n")
                .append("                    <cbc:Percent>18.00</cbc:Percent>\n")
                .append("                    <cac:TaxScheme>\n")
                .append("                        <cbc:ID schemeID=\"UN/ECE 5153\" schemeAgencyName=\"United Nations Economic Commission for Europe\">VAT</cbc:ID>\n")
                .append("                        <cbc:Name>Value Added Tax</cbc:Name>\n")
                .append("                        <cbc:Code>VAT</cbc:Code>\n")
                .append("                    </cac:TaxScheme>\n")
                .append("                </cac:TaxCategory>\n")
                .append("            </cac:TaxSubtotal>\n")
                .append("        </cac:TaxTotal>\n")
                .append("    </cac:InvoiceLine>\n")
                .append("    <cac:InvoiceLine>\n")
                .append("        <cbc:ID>2</cbc:ID>\n")
                .append("        <cbc:InvoicedQuantity unitCode=\"NIU\" unitCodeListID=\"UN/ECE 5305\">1.0</cbc:InvoicedQuantity>\n")
                .append("        <cbc:LineExtensionAmount currencyID=\"").append(currencyCode).append("\">").append(lineExtensionAmount2).append("</cbc:LineExtensionAmount>\n")
                .append("        <cac:PricingReference>\n")
                .append("            <cac:AlternativeConditionPrice>\n")
                .append("                <cbc:PriceAmount currencyID=\"").append(currencyCode).append("\">").append(priceAmount2).append("</cbc:PriceAmount>\n")
                .append("                <cbc:PriceTypeCode listID=\"0101\" listAgencyName=\"PE:SUNAT\">01</cbc:PriceTypeCode>\n")
                .append("            </cac:AlternativeConditionPrice>\n")
                .append("        </cac:PricingReference>\n")
                .append("        <cac:TaxTotal>\n")
                .append("            <cbc:TaxAmount currencyID=\"").append(currencyCode).append("\">").append(taxAmount2).append("</cbc:TaxAmount>\n")
                .append("            <cac:TaxSubtotal>\n")
                .append("                <cbc:TaxAmount currencyID=\"").append(currencyCode).append("\">").append(taxAmount2).append("</cbc:TaxAmount>\n")
                .append("                <cac:TaxCategory>\n")
                .append("                    <cbc:ID schemeID=\"VAT\" schemeAgencyName=\"PE:SUNAT\">S</cbc:ID>\n")
                .append("                    <cbc:Percent>18.00</cbc:Percent>\n")
                .append("                    <cac:TaxScheme>\n")
                .append("                        <cbc:ID schemeID=\"UN/ECE 5153\" schemeAgencyName=\"United Nations Economic Commission for Europe\">VAT</cbc:ID>\n")
                .append("                        <cbc:Name>Value Added Tax</cbc:Name>\n")
                .append("                        <cbc:Code>VAT</cbc:Code>\n")
                .append("                    </cac:TaxScheme>\n")
                .append("                </cac:TaxCategory>\n")
                .append("            </cac:TaxSubtotal>\n")
                .append("        </cac:TaxTotal>\n")
                .append("    </cac:InvoiceLine>\n")
                .append("</Invoice>");

        return xml.toString();
    }

}
