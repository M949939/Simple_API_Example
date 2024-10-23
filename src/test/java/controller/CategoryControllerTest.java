package controller;

import MSI.example.Framework_Module.FrameworkModuleApplication;
import MSI.example.Framework_Module.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("controller_actor")
@SpringBootTest(classes = FrameworkModuleApplication.class)
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @DisplayName("Test GET /api/categories")
    @Test
    public void getAllActorsTest() throws Exception {
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk());
    }
}
