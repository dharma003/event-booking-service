package com.org.event.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.event.book.controller.BookingController;
import com.org.event.book.entity.Booking;
import com.org.event.book.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    void shouldBookTicketSuccessfully() throws Exception {

        Booking booking = new Booking(
                1L, 100L, 10L, 5L, "key", "CONFIRMED"
        );

        when(bookingService.bookSeat(5L,100L,10L,"key"))
                .thenReturn(booking);

        mockMvc.perform(post("/booking")
                        .header("Authorization", "Bearer test")
                        .header("Idempotency-Key", "key")
                        .param("userId","5")
                        .param("eventId","100")
                        .param("seatId","10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }
}
