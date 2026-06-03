package dev.sws.pokepedia.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper

@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `should create pokemon and return base response`() {
        val requestJson = """
            {
                "name": "Charmander",
                "type": "Fire",
                "description": "The flame on its tail indicates its life force."
            }
        """.trimIndent()

        mockMvc.perform(
            post("/api/v1/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("OK"))
            .andExpect(jsonPath("$.data.name").value("Charmander"))
            .andExpect(jsonPath("$.data.types[0]").value("Fire"))
            .andExpect(jsonPath("$.data.description").value("The flame on its tail indicates its life force."))
    }

}