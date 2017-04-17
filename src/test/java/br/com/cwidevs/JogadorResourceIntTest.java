package br.com.cwidevs;

import br.com.cwidevs.domain.Jogador;
import br.com.cwidevs.repository.JogadorRepository;
import br.com.cwidevs.resource.JogadorResource;
import java.util.List;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import org.junit.Ignore;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JogadorResource REST controller.
 *
 * @see JogadorResource
 * @author murillo.goulart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoleadorApplication.class)
public class JogadorResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    
    private static final Boolean DEFAULT_ATIVO = Boolean.TRUE;

    @Autowired
    private JogadorRepository jogadorRepository;

    private MockMvc restMockMvc;

    private Jogador jogador;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JogadorResource jogadorResource = new JogadorResource();
        ReflectionTestUtils.setField(jogadorResource, "jogadorRepository", jogadorRepository);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(jogadorResource).build();
    }   

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Jogador createEntity() {
        Jogador jogador = new Jogador();
        jogador.setNome(DEFAULT_NOME);
        jogador.setAtivo(DEFAULT_ATIVO);
        return jogador;
    }

    @Before
    public void initTest() {
        jogador = createEntity();
    }
    
    @Test
    @Transactional
    public void createOrUpdate() throws Exception {
        int databaseSizeBeforeCreate = jogadorRepository.findAll().size();

        restMockMvc.perform(post("/api/jogadores")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jogador)))
                .andExpect(status().isOk());

        // Validate in the database
        List<Jogador> jogadores = jogadorRepository.findAll();
        assertThat(jogadores).hasSize(databaseSizeBeforeCreate + 1);
        Jogador testJogador = jogadores.get(jogadores.size() - 1);
        assertThat(testJogador.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testJogador.getAtivo()).isEqualTo(DEFAULT_ATIVO);        
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeCreate = jogadorRepository.findAll().size();
        
        jogador.setNome(null);
        
        restMockMvc.perform(post("/api/jogadores")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jogador)))
                .andExpect(status().isBadRequest());

        // Validate in the database
        List<Jogador> jogadores = jogadorRepository.findAll();
        assertThat(jogadores).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomeAlreadyExists() throws Exception {
        // Initialize the database
        jogadorRepository.saveAndFlush(jogador);
        
        int databaseSizeBeforeCreate = jogadorRepository.findAll().size();

        Jogador testJogador = createEntity();

        restMockMvc.perform(post("/api/jogadores")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(testJogador)))
                .andExpect(status().isBadRequest());

        // Validate in the database
        List<Jogador> jogadores = jogadorRepository.findAll();
        assertThat(jogadores).hasSize(databaseSizeBeforeCreate);
        
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        // Initialize the database
        jogadorRepository.saveAndFlush(jogador);

        // Get all the list
        restMockMvc.perform(get("/api/jogadores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jogador.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].ativo").value(hasItem(DEFAULT_ATIVO.booleanValue())));
    }    
    

    @Test
    @Transactional
    public void getOne() throws Exception {
        // Initialize the database
        jogadorRepository.saveAndFlush(jogador);

        // Get the entity
        restMockMvc.perform(get("/api/jogadores/{id}", jogador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jogador.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.ativo").value(DEFAULT_ATIVO.toString()));
    }    

    @Test
    @Transactional
    public void getOneNonExisting() throws Exception {
        // Get the entity
        restMockMvc.perform(get("/api/jogadores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void deleteOne() throws Exception {
        // Initialize the database
        jogadorRepository.save(jogador);

        int databaseSizeBeforeDelete = jogadorRepository.findAll().size();

        // Get the categoria
        restMockMvc.perform(delete("/api/jogadores/{id}", jogador.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean categoriaExistsInEs = jogadorRepository.exists(jogador.getId());
        assertThat(categoriaExistsInEs).isFalse();

        // Validate the database is empty
        List<Jogador> jogadores = jogadorRepository.findAll();
        assertThat(jogadores).hasSize(databaseSizeBeforeDelete - 1);
    }

}
