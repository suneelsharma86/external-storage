package com.boomi.flow.external.storage;

import com.boomi.flow.external.storage.guice.JdbiProvider;
import com.boomi.flow.external.storage.guice.StateRepositoryProvider;
import com.boomi.flow.external.storage.keys.KeyRepository;
import com.boomi.flow.external.storage.keys.KeyRepositoryProvider;
import com.boomi.flow.external.storage.states.StateRepository;
import com.google.inject.AbstractModule;
import com.manywho.sdk.services.servers.Servlet3Server;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;

public class ApplicationTest extends Servlet3Server {

    public ApplicationTest() {

        this.addModule(new AbstractModule() {
            @Override
            protected void configure() {
                bind(HikariDataSource.class).toProvider(DataSourceTestProvider.class).in(Singleton.class);
                bind(Jdbi.class).toProvider(JdbiProvider.class).in(Singleton.class);
                bind(KeyRepository.class).toProvider(KeyRepositoryProvider.class).in(Singleton.class);
                bind(StateRepository.class).toProvider(StateRepositoryProvider.class).in(Singleton.class);
            }
        });

        this.setApplication(ApplicationTest.class);
        this.start();
    }
}
