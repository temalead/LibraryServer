package server.library.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
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


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", numberOfHouse=" + numberOfHouse +
                ", library=" + library.getId() +
                '}';
    }
}
