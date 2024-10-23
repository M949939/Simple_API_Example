package entity;

import MSI.example.Framework_Module.FrameworkModuleApplication;
import MSI.example.Framework_Module.controller.FilmController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FrameworkModuleApplication.class)
public class FilmEntityTest {

    @Autowired
    private FilmController filmController;

    @Test
    void contextLoads() {
        assertThat(filmController).isNotNull();
    }

}
