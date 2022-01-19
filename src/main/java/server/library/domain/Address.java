package server.library.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private Integer numberOfHouse;

    @OneToOne(mappedBy = "address",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "library_id")
    private Library library;
}
