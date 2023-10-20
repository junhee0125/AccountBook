package accountbook;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "accountbook")
@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@ToString@Builder
public class AccountBookEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int ano;

    @Column
    private String acontent;
    @Column
    private int acost;

    public AccountBookDto toDto(){
        return AccountBookDto.builder()
                .ano(this.ano)
                .acontent(this.acontent)
                .acost(this.acost)
                .build();
    }
}
