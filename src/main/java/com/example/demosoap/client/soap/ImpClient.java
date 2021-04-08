package com.example.demosoap.client.soap;

import com.example.demosoap.wsdl.Imprimir;
import com.example.demosoap.wsdl.ImprimirResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ImpClient extends WebServiceGatewaySupport {

    public ImprimirResponse getImpresion(final Imprimir objImp) {
        try {
            ImprimirResponse objImpResp = new ImprimirResponse();
                objImpResp = (ImprimirResponse) getWebServiceTemplate().marshalSendAndReceive(objImp,
                        new SoapActionCallback("https://elsegurazognp.com.mx/PruebasWSAutos/CotizadorGNP/Imprimir"));
                return objImpResp;
            } catch(Exception e) {
                throw e;
            }
        }
}
