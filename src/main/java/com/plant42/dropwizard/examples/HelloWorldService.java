package com.plant42.dropwizard.examples;

import com.plant42.dropwizard.auth.ExampleAuthenticator;
import com.plant42.dropwizard.auth.User;
import com.plant42.dropwizard.auth.provider.CookieAuthProvider;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;


public class HelloWorldService extends Service<HelloWorldConfiguration> {


    public static void main(String[] args) throws Exception {
        new HelloWorldService().run(args);
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.setName("Hello World");
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getTemplate();

        environment.addProvider(new CookieAuthProvider<User>(new ExampleAuthenticator(),"authcookie"));
        environment.addResource( new HelloWorldResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
    }
}
