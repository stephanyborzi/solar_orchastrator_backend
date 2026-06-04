package com.solarorchastrator.solarorchastrator;

import com.solarorchastrator.solarorchastrator.repository.SolarEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SolarorchastratorApplicationTests {

	@Autowired
	private SolarEventRepository repository;

	@Test
	void testDatabaseConnection() {
		long count = repository.count();
		assertThat(repository).isNotNull();
	}

}
