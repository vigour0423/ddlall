package com.ddl.learn.service;


import com.ddl.learn.dao.BookDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class BookService {

    @Qualifier("bookDao")
    //@Autowired(required=false)
    //@Resource(name="bookDao2")
    @Inject
    private BookDao bookDaoqq;

    public void print() {
        System.out.println(bookDaoqq);
    }

    @Override
    public String toString() {
        return "BookService [bookDao=" + bookDaoqq + "]";
    }


}
