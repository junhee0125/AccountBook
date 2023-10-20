package accountbook;

import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@ToString@Builder
public class AccountBookDto {
    private  int ano;
    private String acontent;
    private int acost;

    private String cdate;
    private String udate;

    public AccountBookEntity toEntity(){
        return AccountBookEntity.builder()
                .acontent(this.acontent)
                .acost(this.acost)
                .build();
    }
}
