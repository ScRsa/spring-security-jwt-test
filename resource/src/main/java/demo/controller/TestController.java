package demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAuthority('ROLE_TEST') OR hasRole('ADMIN')")
    @RequestMapping(path = "/resource", method = RequestMethod.GET)
    public Message home() {
        String user = SecurityContextHolder.getContext().getAuthentication().toString();
        return new Message(user);
    }

    @RequestMapping(path = "/foo", method = RequestMethod.GET)
    public Message readFoo() {
        return new Message("read foo");
    }

    @RequestMapping(path = "/foo", method = RequestMethod.POST)
    public Message writeFoo() {
        return new Message("write foo");
    }

}
