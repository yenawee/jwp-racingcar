package racingcar.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcGameInsertDao implements GameInsertDao {

    private final SimpleJdbcInsert insertGameActor;

    public JdbcGameInsertDao(DataSource dataSource) {
        this.insertGameActor = new SimpleJdbcInsert(dataSource)
                .withTableName("GAME")
                .usingGeneratedKeyColumns("game_id")
                .usingColumns("winners", "trial_count");
    }

    @Override
    public int insertGame(String winners, Integer count) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("winners", winners);
        parameters.put("trial_count", count);
        return insertGameActor.executeAndReturnKey(parameters).intValue();
    }
}
