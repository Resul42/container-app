package com.example.containerapp;

import com.example.containerapp.model.Book;
import com.example.containerapp.model.Container;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonDeserializationBean {

    @PostConstruct
    public void init() throws IOException {
        File xmlFileBook = new File("src/main/resources/books.xml");

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Container.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Container book = (Container) jaxbUnmarshaller.unmarshal(new ClassPathResource("Container_68465468.xml").getInputStream());

            System.out.println(book);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
