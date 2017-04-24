package com.portfl.configuration;

import com.portfl.constants.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Vlad on 20.03.17.
 */

@Configuration
@ComponentScan(Constants.Packages.DAO)
@EnableJpaRepositories(basePackages = Constants.Packages.DAO, entityManagerFactoryRef = "emf")
public class RepositoryConfiguration {
}
