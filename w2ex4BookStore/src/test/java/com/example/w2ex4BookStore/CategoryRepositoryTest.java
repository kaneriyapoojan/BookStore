package com.example.w2ex4BookStore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.w2ex4BookStore.domain.Category;
import com.example.w2ex4BookStore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository catRepository;

	@Test
    public void createNewCategory() {
    	Category category = new Category("Test");
    	catRepository.save(category);
    	assertThat(category.getCategoryid()).isNotNull();
    }
	
	@Test
    public void deleteCategory() {
		//We create a book that we will delete just after
    	Category category= new Category("Test1");
    	catRepository.save(category);
    	
    	//We delete this book
    	catRepository.deleteById(category.getCategoryid());
    	
    	//We test that it is really deleted
    	assertThat(catRepository.findById(category.getCategoryid())).isEmpty();
    }
	
	@Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories = catRepository.findByName("education");
        
        assertThat(categories).hasSize(1);
    }
}
