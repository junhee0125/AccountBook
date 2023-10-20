package accountbook;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AccountBookService {

    @Autowired
    private AccountBookRepository accountBookRepository;

    @Transactional
    public boolean write(AccountBookDto accountBookDto  ){
        AccountBookEntity accountBookEntity =  accountBookRepository.save(accountBookDto.toEntity());
        if(accountBookEntity.getAno() >1){return true;}
        return false;
    }
    @Transactional
    public List<AccountBookDto> getAll() {
        List<AccountBookEntity> accountBookEntities = accountBookRepository.findAll();

        List<AccountBookDto> bookDtos = new ArrayList<>();
        accountBookEntities.forEach(a -> bookDtos.add(a.toDto()));
        return bookDtos;
    }
    @Transactional
    public AccountBookDto findByNo(int ano){
        //pno에 해당하는 엔티티 호출
        Optional<AccountBookEntity> phoneEntityOptional
                = accountBookRepository.findById(ano);

        if(phoneEntityOptional.isPresent()) {
            AccountBookEntity phoneEntity = phoneEntityOptional.get();


            return phoneEntity.toDto();
        }


        return null;
    }
    @Transactional
    public boolean update(AccountBookDto accountBookDto  ){
        Optional<AccountBookEntity> entityOption = accountBookRepository.findById(accountBookDto.getAno());
        if(entityOption.isPresent()){
            AccountBookEntity accountBookEntity = entityOption.get();
            accountBookEntity.setAcontent(accountBookDto.getAcontent());
            accountBookEntity.setAcost(accountBookDto.getAcost());
            return true;
         }
        return false;
    }
    @Transactional
    public boolean delete(@RequestParam int ano ){
        Optional<AccountBookEntity> entityOption = accountBookRepository.findById(ano);
        if(entityOption.isPresent()) {
            accountBookRepository.deleteById(ano);
            return true;
        }
        return true;
    }
}
