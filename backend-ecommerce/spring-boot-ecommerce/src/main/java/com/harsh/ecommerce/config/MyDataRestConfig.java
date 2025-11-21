package com.harsh.ecommerce.config;

import com.harsh.ecommerce.entity.Product;
import com.harsh.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE,HttpMethod.PUT,HttpMethod.POST};

        // disable Http methods for Product
        config.getExposureConfiguration().forDomainType(Product.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions) ));
        // disable Http methods for ProductCategory
        config.getExposureConfiguration().forDomainType(ProductCategory.class).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))).withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions) ));

        // call an internal helper method
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids
        // get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClass = new ArrayList<>();

        // get the entity types for the entities
        for (EntityType tempEntities:entities){
            entityClass.add(tempEntities.getJavaType());
        }

        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClass.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }


}
