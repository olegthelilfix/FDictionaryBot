package dev.olegthelilfix.telegram.dictionary.conf

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:settings.properties")
class AppConfiguration
