package io.github.carlosthe19916.core.keys.component.utils;

import io.github.carlosthe19916.core.keys.component.ComponentFactory;
import io.github.carlosthe19916.core.keys.qualifiers.RsaKeyType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Optional;

@ApplicationScoped
public class DefaultComponentUtil implements ComponentUtil {

    @Inject
    @Any
    private Instance<ComponentFactory> componentFactories;

    @Override
    public ComponentFactory getComponentFactory(String providerType, String providerId) {
        try {
            Class<?> aClass = Class.forName(providerType);

            Optional<RsaKeyType> op = RsaKeyType.findByProviderId(providerId);
            if (!op.isPresent()) {
                return null;
            }

            Annotation componentProviderLiteral = new ComponentProviderLiteral(aClass);
            Annotation rsaKeyProviderLiteral = new RsaKeyProviderLiteral(op.get());

            return componentFactories.select(ComponentFactory.class, componentProviderLiteral, rsaKeyProviderLiteral).get();
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Invalid factory", e);
        }
    }


}
