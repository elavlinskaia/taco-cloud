package tacos.data;

import tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

// наследуемся от Spring Data CrudRepository<тип объекта, тип поля идентификатора объекта>
// в нем сразу определены findAll, findById, save
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
