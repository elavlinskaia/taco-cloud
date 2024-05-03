package tacos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Data
@Entity // объявляем класс сущностью JPA
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true) // JPA требует конструктор без параметров
public class Ingredient {
  
  @Id
  private final String id;
  private final String name;
  private final Type type;
  
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}