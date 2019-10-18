package com.example.w2ex4BookStore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.w2ex4BookStore.domain.Book;
import com.example.w2ex4BookStore.domain.BookRepository;
import com.example.w2ex4BookStore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
    private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;

	@Test
    public void createNewBook() {
    	Book book = new Book("Harry Potter", "JK R.", "2002", 10.0, crepository.findByName("fiction").get(0));
    	repository.save(book);
    	assertThat(book.getIsbn()).isNotNull();
    }
	
	@Test
    public void DeleteBook() {
    	Book book = new Book("Harry Potter", "JK R.", "2002", 10.0, crepository.findByName("fiction").get(0));
    	repository.save(book);
    	
    	//We delete this book
    	repository.deleteById(book.getIsbn());
    	
    	//We test that it is really deleted
    	assertThat(repository.findById(book.getIsbn())).isEmpty();
    }
    
}
