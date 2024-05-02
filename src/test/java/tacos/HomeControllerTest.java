package tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ModelAttribute;

import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;

/*
 * Тест для контроллера домашней страницы
 */

// тест для HomeController (регистрирует класс в Spring MVC, 
// чтобы можно было отправлять к нему запросы)
@ExtendWith(SpringExtension.class)
@WebMvcTest() 
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mockMvc; // внедрить MockMvc, чтобы тест мог управлять фиктивным объектом

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/")) // выполнить запрос GET/
            .andExpect(status().isOk())
            .andExpect(view().name("home"))
            .andExpect(content().string(containsString("Welcome to...")));
    }
}
