package com.example.containerapp;

import com.example.containerapp.model.Container;
import com.example.containerapp.repository.ContainerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class XMLDeserializationBean {
    private final ContainerRepository containerRepository;

    @PostConstruct
    public void init() throws IOException {

        try (final InputStream inputStream = new ClassPathResource("Container_68465468.xml").getInputStream()) {
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);

            ObjectMapper objectMapper = new XmlMapper(xmlModule);

            final Container container = objectMapper.readValue(inputStream, Container.class);
            containerRepository.save(container);
        }
    }
}
