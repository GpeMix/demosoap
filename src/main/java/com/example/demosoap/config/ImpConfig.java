package com.example.demosoap.config;

import com.example.demosoap.client.soap.ImpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;

@Configuration
@EnableCaching
public class ImpConfig {
    /** The url. */
    private String urlImpresion = "http://pruebas.gnpventamasiva.com.mx/WSAUTos2015-7/cotizadorgnp.asmx";

    /** The url. */
    private Integer timeSoap = 40000;

    /**
     * Http components message sender.
     *
     * @return the client http request message sender
     */
    @Bean
    public ClientHttpRequestMessageSender httpComponentsMessageSender() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(timeSoap);
        requestFactory.setReadTimeout(timeSoap);
        return new ClientHttpRequestMessageSender(requestFactory);
    }

    /**
     * Marshaller.
     *
     * @return the jaxb 2 marshaller
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.demosoap.wsdl");
        return marshaller;
    }

    @Bean
    public ImpClient getImpresionWS(final Jaxb2Marshaller marshaller) {
        ImpClient client = new ImpClient();
        client.setDefaultUri(urlImpresion);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setMessageSender(httpComponentsMessageSender());
        return client;
    }
}
