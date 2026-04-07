package com.tusharydv.jpaTutorial.jpaTutorial;

import com.tusharydv.jpaTutorial.jpaTutorial.entities.ProductEntity;
import com.tusharydv.jpaTutorial.jpaTutorial.repositiories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(23.45))
				.quantity(4)
				.build();
		ProductEntity savedProductEntity=productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository(){
//		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
//				LocalDateTime.of(2026,1,1,0,0,0));
		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanAndPriceLessThan(4,BigDecimal.valueOf(23.45 ));
		System.out.println(entities);
	}

	@Test
	void getSingleFromRepository(){
		Optional<ProductEntity> productEntity = productRepository
				.findByTitleAndPrice("Pepsi", BigDecimal.valueOf(14.4));
		productEntity.ifPresent(System.out::println);
	}

}
