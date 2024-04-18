package tacos.tacocloud;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/*
 * Тест для контроллера домашней страницы
 */

// тест для HomeController (регистрирует класс в Spring MVC, 
// чтобы можно было отправлять к нему запросы)
@WebMvcTest() 
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc; // внедрить MockMvc, чтобы тест мог управлять фиктивным объектом

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/")) // выполнить запрос GET/
            .andExpect(status().isOk())
            .andExpect(view().name("home"))
            .andExpect(content().string(containsString("Welcome to...")));
    }
}
