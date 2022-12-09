package com.moodle.project.repository;

import com.moodle.project.entity.Tarea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TareaRepositoryTest {
    @Autowired
    private TareaRepository repository;

    @Test
    public void testCrearTarea() {
        Tarea t = repository.save(new Tarea());
        assertThat(t.getId()).isGreaterThan(0);
    }
}
