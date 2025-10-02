package br.com.SentinelTrack.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Table(name = "user_update")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Update {

    @Id
    @Getter @Setter
    private UUID id;

    @Getter @Setter
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Getter @Setter
    @JoinColumn(name = "motorcycle_id")
    @ManyToOne
    private Motorcycle motorcycle;

    @Getter @Setter
    @Column(length = 6) @NotNull
    private Date updateDate;

    @Getter @Setter
    @Column(length = 50) @NotNull
    private String updateMessage;
}
