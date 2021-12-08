package eu.senla.library.controller;

import eu.senla.library.WebApplicationTest;
import eu.senla.library.api.repository.AuthorRepository;
import eu.senla.library.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorControllerTest extends WebApplicationTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorShouldBeAdded() throws Exception {

        String authorDto = "{\"firstName\":\"Vanya\",\"surname\":\"Markovkin\"}";

        mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(authorDto))
                .andExpect(status().is2xxSuccessful());

        String name = (authorRepository.findAll()).get(0).getFirstName();

        assertEquals(name, "Vanya");
    }

    @Test
    @Transactional
    public void authorShouldBeGetById() throws Exception {
        final Author author = authorRepository.add(Author.builder().firstName("yan").build());

        mockMvc.perform(get("/authors/" + author.getId())).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    public void authorShouldBeGetAll() throws Exception {
        mockMvc.perform(get("/authors/")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    @Transactional
    public void authorShouldBeUpdate() throws Exception {
        final Author author = authorRepository.add(Author.builder().firstName("Yan").build());

        String authorDto = "{\"id\":\"1\",\"firstName\":\"Vanya\"}";

        mockMvc.perform(put("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(authorDto))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).
                andExpect(jsonPath("$.id").value(author.getId())).
                andExpect(jsonPath("$.firstName").value("Vanya"));

        final Author tempAuthor = authorRepository.getByName("Vanya");
        assertEquals(tempAuthor.getId(), author.getId());
    }

    @Test
    @Transactional
    public void authorShouldBeDeleteById() throws Exception {
        final Author author = authorRepository.add(Author.builder().firstName("test").build());

        mockMvc.perform(delete("/authors/" + author.getId())).
                andExpect(status().is2xxSuccessful());

        final Author tempAuthor = authorRepository.findById(author.getId());

        assertNull(tempAuthor);
    }
}