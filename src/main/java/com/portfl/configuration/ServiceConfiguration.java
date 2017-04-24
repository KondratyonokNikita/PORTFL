package com.portfl.configuration;

import com.portfl.constants.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vlad on 20.03.17.
 */

@Configuration
@ComponentScan(Constants.Packages.SERVICES)
public class ServiceConfiguration {
}
