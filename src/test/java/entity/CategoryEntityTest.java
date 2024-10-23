package entity;

import MSI.example.Framework_Module.FrameworkModuleApplication;
import MSI.example.Framework_Module.controller.CategoryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = FrameworkModuleApplication.class)
public class CategoryEntityTest {

    @Autowired
    private CategoryController categoryController;

    @Test
    void contextLoads() {
        assertThat(categoryController).isNotNull();
    }

}
