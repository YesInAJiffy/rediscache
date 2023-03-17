package com.redisworld.redis;

import com.redisworld.redis.dto.Book;
import com.redisworld.redis.dto.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final BookRepository bookRepository;


	@Autowired
	public RedisApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Populating embedded database here
		LOG.info("Saving books. Current book count is {}.", bookRepository.count());
		Book davidCopperfiled = new Book("David Copperfield", 250);
		Book chanakaya = new Book("Chankya Neeti", 300);
		Book panchtantra = new Book("Panchtantra", 550);
		Book open = new Book("Open", 275);

		bookRepository.save(davidCopperfiled);
		bookRepository.save(chanakaya);
		bookRepository.save(panchtantra);
		bookRepository.save(open);
		LOG.info("Done saving books. Data: {}.", bookRepository.findAll());

	}
}
