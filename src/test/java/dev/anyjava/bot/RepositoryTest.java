package dev.anyjava.bot;

import dev.anyjava.bot.support.TestSupport;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryTest extends TestSupport {

    @Autowired
    private BarRepository barRepository;

    @Test
    public void testJpa() {
        assertThat(barRepository).isNotNull();
        barRepository.save(Bar.builder().build());
    }
}

@Builder
@Entity
class Bar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

interface BarRepository extends JpaRepository<Bar, Long> {
}