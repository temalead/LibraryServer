package server.library.domain;


import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
public class Library {
    @Id
    private Long id;

    @OneToMany(mappedBy = "library",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Book> book;
}
