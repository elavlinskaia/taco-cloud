package tacos.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Контроллер домашней страницы
 */

@Controller // Spring создаст экземпляр HomeController как bean-компонент в контексте приложения
public class HomeController {
    
    @GetMapping("/") // обрабатывает запросы с корневым путем /
    public String home() {
        return "home"; // возвращает имя представления
    }
    
}
