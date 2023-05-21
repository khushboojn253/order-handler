package demo.src.test.java.com.example.demo.controller;

import demo.src.main.java.com.example.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "")
public class OrderControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private OrderService orderService;

  @Test
  public void givenOrders_whenGetOrdersPerCountry_thenStatus200()
      throws Exception {

    mvc.perform(get("/api/country/orders")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }


  @Test
  public void givenOrders_whenGetWeightPerCountry_thenStatus200()
      throws Exception {

    mvc.perform(get("/api/country/weight")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }


}
