package com.hf.auth.config;

import com.hf.core.utils.SpringBeanContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringBeanContext.class)
public class SpringBeanContextConfig {
}
