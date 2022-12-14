package org.personales.gatewayserver.filters.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {

    public EjemploGatewayFilterFactory() {
        super(Configuracion.class);
    }

    @Override
    public GatewayFilter apply(Configuracion config) {
        return (exchange, chain) -> {
            log.info("ejecutando pre-filtro factory: {} ", config.mensaje);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, cookie).build());
                });

                log.info("ejecutando post-filtro factory: {} ", config.mensaje);
            }));
        };
    }

    @Override
    public String name() {
        return "EjemploCookie";
    }

    @Data
    public static class Configuracion {
        private String mensaje;
        private String cookieValor;
        private String cookieNombre;

//        public String getMensaje() {
//            return mensaje;
//        }
//
//        public void setMensaje(String mensaje) {
//            this.mensaje = mensaje;
//        }
//
//        public String getCookieValor() {
//            return cookieValor;
//        }
//
//        public void setCookieValor(String cookieValor) {
//            this.cookieValor = cookieValor;
//        }
//
//        public String getCookieNombre() {
//            return cookieNombre;
//        }
//
//        public void setCookieNombre(String cookieNombre) {
//            this.cookieNombre = cookieNombre;
//        }
    }

}
