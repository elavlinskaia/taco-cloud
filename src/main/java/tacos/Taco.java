package tacos;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Data
@Entity
public class Taco {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // бд создает уникальные значения id
  private Long id;

  private Date createdAt = new Date();

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  @Size(min=1, message="You must choose at least 1 ingredient")
  @ManyToMany() // взаимосвязь между тако и списком ингредиентов
  private List<Ingredient> ingredients = new ArrayList<>();

  public void addIngredient(Ingredient ingredient) {
      this.ingredients.add(ingredient);
  }
}