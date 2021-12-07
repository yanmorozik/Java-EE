package eu.senla.library.controller;

import eu.senla.library.WebApplicationTest;
import eu.senla.library.api.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorControllerTest extends WebApplicationTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void productShouldBeCreated() throws Exception {
        assertEquals(0, authorRepository.findAll().size());

        final String authorDto =
                """  
                                {\\"id\\":0,\\"firstName\\":\\"Vanya\\",\\"surname\\":\\"Markovkin\\"}
                        """;
        mockMvc.perform(post("/authors/").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                content(authorDto)).
                andExpect(status().is2xxSuccessful()).
                andDo(print()).
                andExpect(jsonPath("$.id").exists());

        assertNotNull(authorRepository.getByName("Vanya"));
    }
}