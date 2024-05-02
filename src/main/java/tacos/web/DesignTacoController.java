package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.Taco;
import tacos.TacoOrder;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j // для логирования
@Controller
@RequestMapping("/design") // будет обрабатывать запросы, пути в которых начинаются с design
@SessionAttributes("tacoOrder") // объект tacoOrder должен поддерживаться на уровне сеанса
public class DesignTacoController {
	private final IngredientRepository ingredientRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		Iterable<Ingredient> ingredients = ingredientRepo.findAll();
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}

	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm() {
		// возвращает имя представления для отображения модели в браузере
		return "design";
	}

	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		if (errors.hasErrors()) {
			return "design";
		}
		
		tacoOrder.addTaco(taco);
		log.info("Processing taco: {}", taco);

		return "redirect:/orders/current"; // перенаправление на другую страницу
	}
  

	private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
		return StreamSupport.stream(ingredients.spliterator(), false)
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

}