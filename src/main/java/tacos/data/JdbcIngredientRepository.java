package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

// репозиторий ингредиентов на основе JdbcTemplate
@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbcTemplate;

    // @Autowired не нужен, так как конструктор один
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query(
            "select id, name, type from Ingredient where id=?",
            this::mapRowToIngredient, // вместо явной реализации RowMapper используется ссылка на метод
            id);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    /*
     * // если вместо ссылки на метод нужна явная реализация RowMapper:
    @Override
    public Ingredient findById(String id) {
        return jdbcTemplate.queryForObject(
            "select id, name, type from Ingredient where id=?",
            new RowMapper<Ingredient>() {
                public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
            };
            }, id);
    }
     */

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
            "insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType().toString());
        return ingredient;
    }

    // реализация RowMapper из Spring для отображения каждой записи из набора результатов в объект
    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.Type.valueOf(row.getString("type")));
    }

    
}
