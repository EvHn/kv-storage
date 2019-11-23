package khannanov.kvstorage.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(EntryHistoryController.class)
@ComponentScan("khannanov")
public class EntryHistoryControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void testGetHistory() throws Exception {
        mock.perform(post("/new")
                .param("key", "kek")
                .param("value", "lala"));
        mock.perform(get("/history/kek"))
                .andExpect(status().isOk())
                .andExpect(view().name("entryHistory"));
    }
}