package br.com.cwidevs;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.domain.Partida;
import br.com.cwidevs.dto.Goleador;
import br.com.cwidevs.repository.PartidaRepository;
import br.com.cwidevs.resource.GoleadorResource;
import br.com.cwidevs.resource.vm.PartidaJogadorVM;
import br.com.cwidevs.resource.vm.PartidaVM;
import br.com.cwidevs.service.PartidaService;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasItem;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Test class for the GoleadorResource REST controller.
 *
 * @author murillo.goulart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoleadorApplication.class)
public class GoleadorResourceIntTest {

    @Autowired
    private PartidaRepository partidaRepository;

    private MockMvc restMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GoleadorResource goleadorResource = new GoleadorResource();
        ReflectionTestUtils.setField(goleadorResource, "partidaRepository", partidaRepository);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(goleadorResource).build();
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        // Get all the list based on liquibase inserts
        restMockMvc.perform(get("/api/goleadores"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].jogador").value(hasItem(1)))
            .andExpect(jsonPath("$.[*].totalGols").value(hasItem(11)));
    }
    
}
