package entity;

@Entity
@Getter
@Setter
@Builder
public class Order {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  @Column
  private String email;

  @Column(name = "parcel_weight")
  private Double weight;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column
  private String country;

}
