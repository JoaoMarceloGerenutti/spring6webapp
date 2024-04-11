package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author();
        author.setFirstName("Eric");
        author.setLastName("Evans");

        Book book = new Book();
        book.setTitle("Domain Driven Design");
        book.setIsbn("123456");

        Author firstAuthorSaved = authorRepository.save(author);
        Book firstBookSaved = bookRepository.save(book);

        author = new Author();
        author.setFirstName("Rod");
        author.setLastName("Johnson");

        book = new Book();
        book.setTitle("J2EE Development without EJB");
        book.setIsbn("54757585");

        Author secondAuthorSaved = authorRepository.save(author);
        Book secondBookSaved = bookRepository.save(book);

        firstAuthorSaved.getBooks().add(firstBookSaved);
        secondAuthorSaved.getBooks().add(secondBookSaved);

        authorRepository.save(firstAuthorSaved);
        authorRepository.save(secondAuthorSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
    }
}
