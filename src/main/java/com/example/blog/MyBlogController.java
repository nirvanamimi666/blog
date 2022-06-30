package com.example.blog;

import com.example.blog.Rep.PostRep;
import com.example.blog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MyBlogController {
    @Autowired
    private PostRep postRep;

    @GetMapping("/myBlog")
    public String myBlog(Model model) {
        model.addAttribute("title", "Блок сайта");
        Iterable<Post> post = postRep.findAll();
        model.addAttribute("post", post);
        return "myBlog";
    }

    @GetMapping("/myBlog/add")
    public String myBlogAdd(Model model) {
        return "myBlogAdd";
    }

    @PostMapping("/myBlog/add")
    public String myBlogAddPost(@RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        Post post = new Post(title, anons, fullText);
        postRep.save(post);
        return "redirect:/myBlog";
    }

    @GetMapping("/myBlog/{id}")
    public String myBlogId(@PathVariable(value = "id") long id, Model model) {
        if (!postRep.existsById(id)) {
            return "redirect:/myBlog";
        }
        Optional<Post> post = postRep.findById(id);
        ArrayList<Post> postArrayList = new ArrayList<>();
        post.ifPresent(postArrayList::add);
        model.addAttribute("post", postArrayList);
        return "myBlogContin";
    }

    @GetMapping("/myBlog/{id}/edit")
    public String myBlogIdEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRep.existsById(id)) {
            return "redirect:/myBlog";
        }
        Optional<Post> post = postRep.findById(id);
        ArrayList<Post> postArrayList = new ArrayList<>();
        post.ifPresent(postArrayList::add);
        model.addAttribute("post", postArrayList);
        return "myBlogEdit";
    }

    @PostMapping("/myBlog/{id}/edit")
    public String myBlogAddPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        Post post = postRep.findById(id).orElseThrow();
        post.setTitle(title);
        post.setTitle(anons);
        post.setTitle(fullText);
        postRep.save(post);
        return "redirect:/myBlog";
    }

    @PostMapping("/myBlog/{id}/remove")
    public String myBlogAddPostRemove(@PathVariable(value = "id") long id,  Model model) {
        Post post = postRep.findById(id).orElseThrow();
        postRep.delete(post);
        return "redirect:/myBlog";
    }
}
