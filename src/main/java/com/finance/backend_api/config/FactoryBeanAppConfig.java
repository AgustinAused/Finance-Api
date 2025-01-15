package com.finance.backend_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import java.nio.file.Path;

@Configuration
public class FactoryBeanAppConfig {

    /*
     * Factory bean que crea la instancia de com.datastax.oss.driver.api.core.CqlSession
     */
    @Bean
    public CqlSessionFactoryBean sessionFactoryBean() {
        CqlSessionFactoryBean factoryBean = new CqlSessionFactoryBean();

        // Configurar el contacto de Cassandra (IP y puerto)
        factoryBean.setContactPoints("127.0.0.1");
        factoryBean.setPort(9042);

        // Configurar el datacenter (debes asegurarte que coincide con el configurado en cassandra.yaml)
        factoryBean.setLocalDatacenter("datacenter1");

        // Configurar opcionalmente el archivo de claves para autenticación si es necesario
        factoryBean.setKeyspaceName("finance_keyspace");

        return factoryBean;
    }
}