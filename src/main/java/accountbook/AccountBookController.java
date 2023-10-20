package accountbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountbook")
public class AccountBookController {
    @Autowired
    private AccountBookService accountBookService;


    @GetMapping("/index")
    public Resource getIndex(){
        return new ClassPathResource("templates/index.html");
    }

    @PostMapping("")
    public boolean write(@RequestBody AccountBookDto accountBookDto  ){

        System.out.println(accountBookDto);
        return accountBookService.write(accountBookDto);
    }


    @GetMapping("")
    public List<AccountBookDto> getAll() {
        return accountBookService.getAll();
    }

    @GetMapping("findbyno")
    public AccountBookDto findByNo(int ano){
        return accountBookService.findByNo(ano);
    }

    @PutMapping("")
    public boolean update( @RequestBody AccountBookDto accountBookDto  ){
        return accountBookService.update(accountBookDto);
    }

    @DeleteMapping("")
    public boolean delete(@RequestParam int ano ){
        return accountBookService.delete(ano);
    }



}
