package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author();
        author.setFirstName("Eric");
        author.setLastName("Evans");

        Book book = new Book();
        book.setTitle("Domain Driven Design");
        book.setIsbn("123456");

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Arqueiro");
        publisher.setAddress("Madame Toussauds 234 West 42nd Street");
        publisher.setCity("Winter Haven");
        publisher.setState("FL");
        publisher.setZip("33882");

        Author firstAuthorSaved = authorRepository.save(author);
        Publisher firstPublisherSaved = publisherRepository.save(publisher);
        Book firstBookSaved = bookRepository.save(book);

        author = new Author();
        author.setFirstName("Rod");
        author.setLastName("Johnson");

        book = new Book();
        book.setTitle("J2EE Development without EJB");
        book.setIsbn("54757585");

        publisher = new Publisher();
        publisher.setPublisherName("Pipoca");
        publisher.setAddress("Toussauds Park 153 North 2nd Street");
        publisher.setCity("Tarzan");
        publisher.setState("TX");
        publisher.setZip("79783");

        Author secondAuthorSaved = authorRepository.save(author);
        Publisher secondPublisherSaved = publisherRepository.save(publisher);
        Book secondBookSaved = bookRepository.save(book);

        firstAuthorSaved.getBooks().add(firstBookSaved);
        firstPublisherSaved.getBooks().add(firstBookSaved);
        secondAuthorSaved.getBooks().add(secondBookSaved);
        secondPublisherSaved.getBooks().add(secondBookSaved);

        authorRepository.save(firstAuthorSaved);
        authorRepository.save(secondAuthorSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
    }
}
