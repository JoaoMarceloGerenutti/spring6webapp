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
        Author firstAuthorSaved = authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Domain Driven Design");
        book.setIsbn("123456");
        Book firstBookSaved = bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Arqueiro");
        publisher.setAddress("Madame Toussauds 234 West 42nd Street");
        publisher.setCity("Winter Haven");
        publisher.setState("FL");
        publisher.setZip("33882");
        Publisher firstPublisherSaved = publisherRepository.save(publisher);

        author = new Author();
        author.setFirstName("Rod");
        author.setLastName("Johnson");
        Author secondAuthorSaved = authorRepository.save(author);

        book = new Book();
        book.setTitle("J2EE Development without EJB");
        book.setIsbn("54757585");
        Book secondBookSaved = bookRepository.save(book);

        firstBookSaved.setPublisher(firstPublisherSaved);
        secondBookSaved.setPublisher(firstPublisherSaved);

        firstAuthorSaved.getBooks().add(firstBookSaved);
        secondAuthorSaved.getBooks().add(secondBookSaved);

        authorRepository.save(firstAuthorSaved);
        authorRepository.save(secondAuthorSaved);

        bookRepository.save(firstBookSaved);
        bookRepository.save(secondBookSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
