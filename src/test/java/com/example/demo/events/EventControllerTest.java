package com.example.demo.events;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import java.time.*;

import org.junit.jupiter.api.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.restdocs.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.*;

import com.example.demo.common.*;
import com.fasterxml.jackson.databind.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
public class EventControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	@DisplayName("정상적으로 이벤트를 생성하는 테스트")
	public void createEvent() throws Exception {
		EventDto event = EventDto.builder().name("Spring").description("REST API Development with Spring")
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
				.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
				.beginEventDateTime(LocalDateTime.of(2018, 11, 22, 14, 21))
				.endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21)).basePrice(100).maxPrice(200)
				.limitOfEnrollment(100).location("강남역 D2 스타트업 팩토리").build();

		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaTypes.HAL_JSON)
				.content(objectMapper.writeValueAsString(event)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("id").exists()).andExpect(header().exists(HttpHeaders.LOCATION))
				.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
				.andExpect(jsonPath("free").value(false))
				.andExpect(jsonPath("offline").value(true))
				.andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
				.andExpect(jsonPath("_links.self").exists())
				.andExpect(jsonPath("_links.query-events").exists())
				.andExpect(jsonPath("_links.update-event").exists())
				.andDo(document("create-event",
						links(
								linkWithRel("self").description("link to self"),
                                linkWithRel("query-events").description("link to query events"),
                                linkWithRel("update-event").description("link to update an existing event")
						/*),
						requestHeaders(
								headerWithName(HttpHeaders.ACCEPT).description("accept header"),
								headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
						),
						requestFields(
								fieldWithPath("name").description("Name of new event"),
								fieldWithPath("description").description("description of description"),
								fieldWithPath("beginEnrollmentDateTime").description("date time of begin of new enrollment"),
								fieldWithPath("closeEnrollmentDateTime").description("close time of begin of new enrollment"),
								fieldWithPath("beginEventDateTime").description("date time of begin of new event"),
								fieldWithPath("endEventDateTime").description("date time of end of new event"),
								fieldWithPath("location").description("location of new event"),
								fieldWithPath("basePrice").description("base price of new event"),
								fieldWithPath("maxPrice").description("max price of new event"),
								fieldWithPath("limitOfEnrollment").description("limit of Enrollemnt of new event")
						),
						responseHeaders(
								headerWithName(HttpHeaders.LOCATION).description("Location Header"),
								headerWithName(HttpHeaders.CONTENT_TYPE).description("Content Type")
						),
						relaxedResponseFields(
								fieldWithPath("id").description("identifier of new event"),
								fieldWithPath("name").description("Name of new event"),
								fieldWithPath("description").description("description of description"),
								fieldWithPath("beginEnrollmentDateTime").description("date time of begin of new enrollment"),
								fieldWithPath("closeEnrollmentDateTime").description("close time of begin of new enrollment"),
								fieldWithPath("beginEventDateTime").description("date time of begin of new event"),
								fieldWithPath("endEventDateTime").description("date time of end of new event"),
								fieldWithPath("location").description("location of new event"),
								fieldWithPath("basePrice").description("base price of new event"),
								fieldWithPath("maxPrice").description("max price of new event"),
								fieldWithPath("limitOfEnrollment").description("limit of Enrollemnt of new event"),
								fieldWithPath("free").description("this event free or not"),
								fieldWithPath("offline").description("this event offline or not"),
								fieldWithPath("eventStatus").description("event status")*/
						)
				))
				;
	}

}
