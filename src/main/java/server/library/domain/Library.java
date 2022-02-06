package server.library.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
