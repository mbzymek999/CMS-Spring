//package cms.api.user;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class LoginControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void authenticateUser() throws Exception {
//
//        MvcResult login = mockMvc.perform(post("/api/auth/signin")
//                        .content("{\"username\": \"superuser\", \"password\": \"superuser\"}")
//                )
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andReturn();
//        String token = login.getResponse().getHeader("Authorization");
//
//
//        mockMvc.perform(get("/api/auth/secured")
//                        .header("Authorization", token)
//                )
//                .andDo(print())
//                .andExpect(status().is(200))
//                .andExpect(content().string("secured"));
//
//        mockMvc.perform(get("/api/auth/secured"))
//                .andDo(print())
//                .andExpect(status().is(401));
//    }
//}