package com.diti5.hopital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    //DEFAULT
    registry.addViewController("/")
        .setViewName("forward:/admin/dashbord.xhtml");
    registry.addViewController("/admin")
        .setViewName("forward:/admin/dashbord.xhtml");
    registry.addViewController("/medecin")
        .setViewName("forward:/medecin/dashbord.xhtml");
    registry.addViewController("/secretaire")
        .setViewName("forward:/secretaire/dashbord.xhtml");

    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
  }
}
