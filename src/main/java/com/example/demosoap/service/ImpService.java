package com.example.demosoap.service;

import com.example.demosoap.client.soap.ImpClient;
import com.example.demosoap.model.ImpresionReq;
import com.example.demosoap.model.ImpresionResp;
import com.example.demosoap.wsdl.Imprimir;
import com.example.demosoap.wsdl.ImprimirResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

@RestController
public class ImpService {

    @Autowired
    private ImpClient impc;

    @PostMapping(path = "/impresion",consumes = {MediaType.APPLICATION_JSON},produces = {MediaType.APPLICATION_XML})
    public final ImprimirResponse getImp(@RequestBody final ImpresionReq iReq) {
        String cadenaxml="";

        cadenaxml = iReq.getCadena();
        cadenaxml = "<![CDATA["+cadenaxml+"]]>";
        Imprimir imp = new Imprimir();
        imp.setMixml(cadenaxml);

        ImprimirResponse impResp = new ImprimirResponse();
        impResp = impc.getImpresion(imp);

        /*ImprimirResponse.ImprimirResult impResult = new ImprimirResponse.ImprimirResult();

        impResult = impResp.getImprimirResult();

        ImpresionResp objResp = new ImpresionResp();
        objResp.setUrl(impResult.toString());*/

        return impResp;
    }
}
