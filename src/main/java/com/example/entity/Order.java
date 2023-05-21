package demo.src.main.java.com.example.entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  @Column
  private String email;
  @Column(name = "number")
  private String number;


  @Column(name = "weight")
  private Double weight;
  @Column
  private String country;

}
