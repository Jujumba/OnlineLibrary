package farid.guliev.onlinelibrary.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @NotEmpty(message = "Name shouldn't be empty!")
    @Size(min = 2, max = 30, message= "Your name is too short or long")
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    @Min(value = 0, message="Age should be greater than zero")
    private int age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int
            id;
    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }
}
