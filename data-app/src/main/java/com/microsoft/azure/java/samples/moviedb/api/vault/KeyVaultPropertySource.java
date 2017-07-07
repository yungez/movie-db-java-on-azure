package com.microsoft.azure.java.samples.moviedb.api.vault;

import org.springframework.core.env.EnumerablePropertySource;

public class KeyVaultPropertySource extends EnumerablePropertySource {

    private final KeyVaultOperation operations;

    public KeyVaultPropertySource(KeyVaultOperation operations) {
        super("azurekv", operations);
        this.operations = operations;
    }


    public String[] getPropertyNames() {
        String[] list = this.operations.list();
        return list;
    }


    public Object getProperty(String name) {
        Object property = operations.get(name);
        return property;
    }
}