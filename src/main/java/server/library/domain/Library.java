package server.library.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;


@Entity
@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "library",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Book> books;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", books=" + books.size() +
                ", address=" + address.getId() +
                '}';
    }
}
