package com.microsoft.azure.java.samples.moviedb.api.vault;

import com.microsoft.azure.keyvault.KeyVaultClient;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;


public class KeyVaultPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static final String azureKeyVaultBaseUri = "azure.keyvault.uri";
    public static final String azureClientId = "azure.clientid";
    public static final String azureClientKey = "azure.clientkey";

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        ConfigurableEnvironment env = ctx.getEnvironment();

        String clientId = env.getProperty(azureClientId);
        String clientKey = env.getProperty(azureClientKey);
        String baseUri = env.getProperty(azureKeyVaultBaseUri);

        KeyVaultClient kvClient = new KeyVaultClient(new AzureKeyVaultCredential(clientId, clientKey));

        try {
            MutablePropertySources sources = env.getPropertySources();
            sources.addFirst(new KeyVaultPropertySource(new KeyVaultOperation(kvClient, baseUri)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}