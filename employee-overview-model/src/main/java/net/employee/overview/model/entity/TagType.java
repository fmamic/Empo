package net.employee.overview.model.entity;

import net.employee.overview.model.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "EMP_TAG_TYPE")
@SequenceGenerator(name = "TGT_SEQ", sequenceName = "TGT_SEQ", allocationSize = 1)
@AttributeOverride(name = "version", column = @Column(name = "TGT_VERSION"))
public class TagType extends Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TGT_SEQ")
    @Column(name = "TGT_ID")
    private Long id;

    @Column(name = "TGT_NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
