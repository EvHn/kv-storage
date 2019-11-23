package khannanov.kvstorage.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(EntryController.class)
@ComponentScan("khannanov")
public class EntryControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void testCreateState() throws Exception {
        mock.perform(get("/new/entry"))
                .andExpect(status().isOk())
                .andExpect(view().name("entry"));
    }
}