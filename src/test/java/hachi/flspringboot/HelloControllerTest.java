package hachi.flspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        String expected = "hello";

        mockMvc.perform(
                    get("/hello")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));

    }

    @Test
    public void helloDto응답결과테스트() throws Exception {
        String name = "이어진";
        int amount = 6000;

        mockMvc.perform(
                    get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    @Test
    public void helloDtoSimple응답결과테스트() throws Exception {
        String name = "hachi";
        int amount = 12;

        mockMvc.perform(
                get("/hello/dto/simple")
                    .param("name", name)
                    .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    @Test
    public void helloDtoModel응답결과테스트() throws Exception {
        String name = "이희진";
        int amount = 8;

        mockMvc.perform(
                    get("/hello/dto/model")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    @Test
    public void helloDtoModelWrapper응답결과테스트() throws Exception {
        String name = "어진이";
        int amount = 19;

        mockMvc.perform(
                    get("/hello/dto/model/wrapper")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}