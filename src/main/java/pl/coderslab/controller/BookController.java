package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.service.MemoryBookService;

import java.util.List;

@RestController
//rest controler to nie trzeba używać respons body, zawsze da jejsona
@RequestMapping("/books")
public class BookController {
    @Autowired
    private MemoryBookService memoryBookService;

    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book(1L,"9788324631766","Thinking in Java",
                "Bruce Eckel","Helion","programming");
    }
    @RequestMapping("/")
    public List<Book> getAll(){
        return memoryBookService.getList();
    }

    @RequestMapping("/{id}")
    public Book getById(@PathVariable long id){
        return memoryBookService.getByID(id);
    }
    //możemy użyć RequestBody bo kożystamy z jesona
    @PostMapping("/")
    public void addById(@RequestBody Book book){
        memoryBookService.addBook(book);
    }
    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book book){
        book.setId(id);
        memoryBookService.update(book);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        memoryBookService.delete(id);
    }


}
