package farid.guliev.onlinelibrary.controllers;

import farid.guliev.onlinelibrary.models.Book;
import farid.guliev.onlinelibrary.models.Person;
import farid.guliev.onlinelibrary.services.BooksService;
import farid.guliev.onlinelibrary.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }
    @GetMapping
    public String index(Model model,
                        @RequestParam(value = "sort-by-year",required = false) boolean sort,
                        @RequestParam(value = "page",required = false) Integer page) {
        if(page != null && sort) {
            model.addAttribute("books",booksService.getWithPagination(page,true));
        } else if (page != null) {
            model.addAttribute("books",booksService.getWithPagination(page,false));
        }else {
            model.addAttribute("books", booksService.findAll(sort));
        }
        return "books/index";
    }
    @GetMapping("{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("book",booksService.findOne(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String save(Model model,@ModelAttribute("book") Book book, @ModelAttribute("person") Person person) {
        model.addAttribute("people", peopleService.findAll());
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person) {
        book.setOwner(person);
        booksService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book",booksService.findOne(id));
        model.addAttribute("people", peopleService.findAll());
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book,
                         @ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        book.setOwner(person);
        book.setId(id);
        booksService.save(book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam(value = "query") String bookName) {
            model.addAttribute("books",booksService.findByTitle(bookName));
            return "books/search";
    }
}
